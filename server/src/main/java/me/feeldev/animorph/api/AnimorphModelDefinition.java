package me.feeldev.animorph.api;

import me.feeldev.animorph.common.constants.EquipmentType;
import me.feeldev.animorph.common.constants.HitboxPoseType;
import java.util.List;
import java.util.Map;

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
 *
 * <h2>Properties</h2>
 * <p>Overriding {@link #getAnimationControllers()}, {@link #isLayer()}, {@link #getRenderType()},
 * {@link #isHideNametag()}, {@link #getFirstPersonProperty()}, {@link #getTextPlaceholders()},
 * {@link #getHitboxContents()}, {@link #getEquipmentContents()} and {@link #getItemEquipmentContents()}
 * is entirely optional — see each method's own javadoc.
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
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    // -----------------------------------------------------------------------
    // Simple case: override these to point at bundled classpath resources.
    // -----------------------------------------------------------------------
    /**
     * Classpath path to the default (non-slim) geometry JSON, or {@code null} if not available.
     */
    protected String modelResourcePath() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Classpath path to the slim (Alex) geometry JSON, or {@code null} to fall back to the default model for slim players too.
     */
    protected String slimModelResourcePath() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Classpath path to the animation JSON, or {@code null} if this model has no animations.
     */
    protected String animationResourcePath() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Classpath path to the texture PNG.
     */
    protected String textureResourcePath() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Classpath path to the texture's {@code .mcmeta} animation file, or {@code null} for a static texture.
     */
    protected String textureAnimationResourcePath() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    // -----------------------------------------------------------------------
    // Advanced case: override these directly for dynamically-generated content.
    // Defaults just read the *ResourcePath() above.
    // -----------------------------------------------------------------------
    /**
     * @return the default geometry JSON content.
     */
    public String getModelContent() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return the slim geometry JSON content, or {@code null}.
     */
    public String getSlimModelContent() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return the animation JSON content, or {@code null}.
     */
    public String getAnimationContent() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return the raw PNG texture bytes.
     */
    public byte[] getTextureBytes() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return the texture's {@code .mcmeta} JSON content, or {@code null} for a static texture.
     */
    public String getTextureAnimation() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    // -----------------------------------------------------------------------
    // By ref: the clients get the asset themselves, the server never reads or sends it. A ref is a
    // remote {@code https://...#sha256=<hex>[&size=<bytes>]} (downloaded and cached by each client)
    // or a {@code client:<id>}. When a ref is set, the matching content above is ignored — e.g. for
    // models whose files live on a CDN, or that are too big for a game packet.
    // -----------------------------------------------------------------------
    /**
     * @return the default geometry's ref, or {@code null} to send {@link #getModelContent()}.
     */
    public String getModelRef() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return the slim geometry's ref, or {@code null} to send {@link #getSlimModelContent()}.
     */
    public String getSlimModelRef() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return the animation's ref, or {@code null} to send {@link #getAnimationContent()}.
     */
    public String getAnimationRef() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return the texture's ref, or {@code null} to send {@link #getTextureBytes()}.
     */
    public String getTextureRef() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return the layers attached to this model; empty if none.
     */
    public List<AnimorphLayerDefinition> getLayers() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    // -----------------------------------------------------------------------
    // Properties — mirror the yml model's properties: block. All defaults below
    // reproduce exactly the same behavior as a yml model with no properties: section.
    // -----------------------------------------------------------------------
    /**
     * @return native/content-based animation controllers active for this model. Empty by default
     * (no controllers active — same as a yml model with no {@code properties.animation_controllers}).
     */
    public List<AnimorphControllerDefinition> getAnimationControllers() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return first-person animation controllers. Empty by default (same as a yml model with no
     * {@code properties.fp_animation_controllers}).
     */
    public List<AnimorphControllerDefinition> getFpAnimationControllers() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return {@code true} if this model is a layer (doesn't replace the player model). Default {@code false}.
     */
    public boolean isLayer() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return the Minecraft RenderType name. Default {@code "entity_cutout_no_cull"} (matches the yml default).
     */
    public String getRenderType() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return whether to hide the player's nametag while this model is active. Default {@code false}.
     */
    public boolean isHideNametag() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return first-person rendering overrides for this model, or {@code null} to use engine
     * defaults (same as a yml model with no {@code properties.first_person} section).
     */
    public IFirstPersonProperty getFirstPersonProperty() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return dynamic text placeholders (placeholder id -> initial value). Empty by default.
     */
    public Map<String, String> getTextPlaceholders() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return per-pose hitbox JSON content overrides (pose -> geometry JSON content), or empty to
     * use only the default STANDING hitbox — same as a yml model with no {@code hitboxes:} block.
     */
    public Map<HitboxPoseType, String> getHitboxContents() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return per-slot equipment geometry JSON content overrides (slot -> geometry JSON content).
     * Only {@code HEAD}/{@code CHEST}/{@code LEGS}/{@code FEET}/{@code CAPE}/{@code ELYTRA} are
     * meaningful here ({@code OTHER} is ignored). Empty by default — same as a yml model with no
     * {@code equipment:} block; every slot still resolves to an empty-content placeholder, never null.
     */
    public Map<EquipmentType, String> getEquipmentContents() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @return per-item equipment overrides (full item ID, e.g. {@code "minecraft:netherite_helmet"},
     * -> slot -> geometry JSON content). Item-specific overrides take priority over
     * {@link #getEquipmentContents()}'s slot defaults. Empty by default — same as a yml model with no
     * {@code equipment.items:} block.
     */
    public Map<String, Map<EquipmentType, String>> getItemEquipmentContents() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
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
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Reads a classpath resource bundled in this class's own jar as raw bytes.
     *
     * @param classpathPath an absolute classpath path (e.g. {@code "/assets/mymod/fox.png"}), or {@code null}
     * @return the resource bytes, or {@code null} if {@code classpathPath} is {@code null}
     * @throws IllegalStateException if the resource cannot be found or read
     */
    protected final byte[] readBytes(String classpathPath) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
