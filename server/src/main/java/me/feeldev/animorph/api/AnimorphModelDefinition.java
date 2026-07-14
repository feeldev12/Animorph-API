package me.feeldev.animorph.api;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Base class for defining an Animorph model bundled inside your own mod/plugin jar.
 * <p>
 * Extend this class, override the {@code *ResourcePath()} methods to point at your bundled
 * assets (geometry JSON, animation JSON, texture PNG), and register an instance via
 * {@link IMorphAPI#registerModel(AnimorphModelDefinition)}. This mirrors the pattern GeckoLib
 * itself uses for {@code GeoModel} — no classloader plumbing required, since {@code getClass()}
 * inside this class is always your own subclass.
 *
 * <h2>Simple usage (static bundled assets)</h2>
 * <pre>{@code
 * public class FoxModel extends AnimorphModelDefinition {
 *     public String getModelId() { return "fox"; }
 *     protected String modelResourcePath() { return "/assets/mymod/fox.geo.json"; }
 *     protected String animationResourcePath() { return "/assets/mymod/fox.animation.json"; }
 *     protected String textureResourcePath() { return "/assets/mymod/fox.png"; }
 * }
 *
 * api.registerModel(new FoxModel());
 * }</pre>
 *
 * <h2>Dynamic content (e.g. procedurally generated)</h2>
 * <p>Override the {@code get*Content()} / {@code getTextureBytes()} methods directly instead
 * of the {@code *ResourcePath()} ones — their defaults just read whichever path you provided,
 * so overriding the content method skips file reading entirely.
 * <pre>{@code
 * public class ProceduralModel extends AnimorphModelDefinition {
 *     public String getModelId() { return "procedural"; }
 *     public byte[] getTextureBytes() { return generateTexture(); }
 *     public String getModelContent() { return generateGeometry(); }
 * }
 * }</pre>
 *
 * <h2>Layers</h2>
 * <p>Override {@link #getLayers()}; use the inherited {@link #readBytes(String)} to read
 * a layer's bundled texture from this same class's classpath.
 */
public abstract class AnimorphModelDefinition {

    /**
     * The unique identifier for this model, used to reference it in commands and the API.
     * Must be unique across all registered models.
     *
     * @return the model ID; must not be {@code null} or empty
     */
    public abstract String getModelId();

    /**
     * The display name shown in chat, the tab list, and the 3D nametag when this model is active.
     * Supports legacy color codes with {@code &}.
     *
     * @return the display name, or {@code null} to use the player's real username
     */
    public String getDisplayName() {
        return null;
    }

    // -----------------------------------------------------------------------
    // Simple case: override these to point at bundled classpath resources.
    // -----------------------------------------------------------------------

    /** Classpath path to the default (non-slim) geometry JSON, or {@code null} if not available. */
    protected String modelResourcePath() {
        return null;
    }

    /** Classpath path to the slim (Alex) geometry JSON, or {@code null} to fall back to the default model for slim players too. */
    protected String slimModelResourcePath() {
        return null;
    }

    /** Classpath path to the animation JSON, or {@code null} if this model has no animations. */
    protected String animationResourcePath() {
        return null;
    }

    /** Classpath path to the texture PNG. */
    protected String textureResourcePath() {
        return null;
    }

    /** Classpath path to the texture's {@code .mcmeta} animation file, or {@code null} for a static texture. */
    protected String textureAnimationResourcePath() {
        return null;
    }

    // -----------------------------------------------------------------------
    // Advanced case: override these directly for dynamically-generated content.
    // Defaults just read the *ResourcePath() above.
    // -----------------------------------------------------------------------

    /** @return the default geometry JSON content. */
    public String getModelContent() {
        return readText(modelResourcePath());
    }

    /** @return the slim geometry JSON content, or {@code null}. */
    public String getSlimModelContent() {
        return readText(slimModelResourcePath());
    }

    /** @return the animation JSON content, or {@code null}. */
    public String getAnimationContent() {
        return readText(animationResourcePath());
    }

    /** @return the raw PNG texture bytes. */
    public byte[] getTextureBytes() {
        return readBytes(textureResourcePath());
    }

    /** @return the texture's {@code .mcmeta} JSON content, or {@code null} for a static texture. */
    public String getTextureAnimation() {
        return readText(textureAnimationResourcePath());
    }

    /** @return the layers attached to this model; empty if none. */
    public List<AnimorphLayerDefinition> getLayers() {
        return List.of();
    }

    // -----------------------------------------------------------------------
    // Resource-reading helpers — resolve against this class's own classloader,
    // so no Class<?> handle ever needs to be passed to Animorph.
    // -----------------------------------------------------------------------

    /**
     * Reads a classpath resource bundled in this class's own jar as a UTF-8 string.
     *
     * @param classpathPath an absolute classpath path (e.g. {@code "/assets/mymod/fox.geo.json"}), or {@code null}
     * @return the resource content, or {@code null} if {@code classpathPath} is {@code null}
     * @throws IllegalStateException if the resource cannot be found or read
     */
    protected final String readText(String classpathPath) {
        byte[] bytes = readBytes(classpathPath);
        return bytes == null ? null : new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Reads a classpath resource bundled in this class's own jar as raw bytes.
     *
     * @param classpathPath an absolute classpath path (e.g. {@code "/assets/mymod/fox.png"}), or {@code null}
     * @return the resource bytes, or {@code null} if {@code classpathPath} is {@code null}
     * @throws IllegalStateException if the resource cannot be found or read
     */
    protected final byte[] readBytes(String classpathPath) {
        if (classpathPath == null) return null;
        String normalized = classpathPath.startsWith("/") ? classpathPath.substring(1) : classpathPath;
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(normalized)) {
            if (in == null) {
                throw new IllegalStateException(
                        "Classpath resource not found: " + classpathPath + " (class: " + getClass().getName() + ")");
            }
            return in.readAllBytes();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read classpath resource: " + classpathPath, e);
        }
    }
}
