package me.feeldev.animorph.api;

import java.util.List;

/**
 * Fluent builder for constructing {@link IModelData} instances that can be registered
 * programmatically via {@link IMorphAPI#registerModel(IModelData)}.
 *
 * <p>Use this when your mod bundles model assets (geometry JSON, animation JSON, texture PNG)
 * inside its own jar instead of placing files in the server's {@code config/animorph/} directory.
 * Obtain a fresh builder from {@link IMorphAPI#modelBuilder()}.
 *
 * <h2>Usage example (raw bytes)</h2>
 * <pre>{@code
 * // Read bundled resources from your mod's classpath
 * byte[] texture = getClass().getResourceAsStream("/assets/mymod/fox.png").readAllBytes();
 * String geoJson  = new String(getClass().getResourceAsStream("/assets/mymod/fox.geo.json").readAllBytes());
 * String animJson = new String(getClass().getResourceAsStream("/assets/mymod/fox.animation.json").readAllBytes());
 *
 * IModelData foxModel = api.modelBuilder()
 *     .modelId("fox")
 *     .modelContent(geoJson)
 *     .animationContent(animJson)
 *     .texture(texture)
 *     .texturePath("mymod:fox.png")
 *     .displayName("&6Fox")
 *     .build();
 *
 * boolean registered = api.registerModel(foxModel);
 * }</pre>
 *
 * <h2>Usage example (classpath resource paths)</h2>
 * <pre>{@code
 * IModelData foxModel = api.modelBuilder()
 *     .modelId("fox")
 *     .source(MyMod.class)
 *     .modelResource("/assets/mymod/fox.geo.json")
 *     .animationResource("/assets/mymod/fox.animation.json")
 *     .textureResource("/assets/mymod/fox.png")
 *     .texturePath("mymod:fox.png")
 *     .build();
 * }</pre>
 *
 * <h2>Layers</h2>
 * <p>To attach layers to the model, call {@link #layer(String, LayerType)} for each layer
 * and configure it with the returned {@link ILayerBuilder} before calling {@link ILayerBuilder#add()}
 * to return to this builder:
 *
 * <pre>{@code
 * IModelData foxModel = api.modelBuilder()
 *     .modelId("fox")
 *     .modelContent(geoJson)
 *     .texture(texture)
 *     .texturePath("mymod:fox.png")
 *     .layer("scarf", LayerType.TEXTURE)
 *         .texture(scarfTexture)
 *         .texturePath("mymod:fox_scarf.png")
 *         .defaultEnabled(true)
 *         .add()
 *     .build();
 * }</pre>
 *
 * <h2>Thread safety</h2>
 * <p>Builder instances are NOT thread-safe. Create a new builder per model.
 * The built {@link IModelData} instance is immutable and safe for concurrent reads.
 *
 * @see IMorphAPI#modelBuilder()
 * @see IMorphAPI#registerModel(IModelData)
 */
public interface IModelDataBuilder {

    /**
     * Sets the unique identifier for this model.
     * <p>
     * The model ID is used to reference the model in commands and the API (e.g. {@code "fox"}, {@code "dragon"}).
     * Must be unique across all registered models. If a bundle model shares an ID with a disk-loaded model,
     * the bundle takes precedence after each reload.
     *
     * @param id the model ID; must not be {@code null} or empty
     * @return this builder
     */
    IModelDataBuilder modelId(String id);

    /**
     * Sets the display name shown in chat, the tab list, and the 3D nametag when this model is active.
     * <p>
     * Supports legacy color codes with {@code &} (e.g. {@code "&6Fox"}).
     * Pass {@code null} or an empty string to use the player's real username.
     *
     * @param displayName the display name, or {@code null} to unset
     * @return this builder
     */
    IModelDataBuilder displayName(String displayName);

    /**
     * Sets the geometry (Bedrock model) JSON content for the default (non-slim) player model.
     * <p>
     * Read this from your bundled {@code .geo.json} resource file.
     *
     * @param geoJson the geometry JSON string; pass {@code null} or empty if not available
     * @return this builder
     */
    IModelDataBuilder modelContent(String geoJson);

    /**
     * Sets the geometry JSON content for the slim (Alex) player model variant.
     * <p>
     * If not set, the default geometry ({@link #modelContent(String)}) is used for slim players too.
     *
     * @param geoJson the slim geometry JSON string; pass {@code null} to fall back to the default model
     * @return this builder
     */
    IModelDataBuilder slimModelContent(String geoJson);

    /**
     * Sets the animation JSON content for this model.
     * <p>
     * Read this from your bundled {@code .animation.json} resource file.
     *
     * @param animationJson the animation JSON string; pass {@code null} if the model has no animations
     * @return this builder
     */
    IModelDataBuilder animationContent(String animationJson);

    /**
     * Sets the PNG texture bytes for this model.
     * <p>
     * Read the raw bytes directly from your bundled PNG resource file:
     * <pre>{@code
     * byte[] texture = getClass().getResourceAsStream("/assets/mymod/fox.png").readAllBytes();
     * builder.texture(texture);
     * }</pre>
     *
     * @param png the raw PNG bytes; must not be {@code null} — pass an empty {@code byte[]} if no texture
     * @return this builder
     */
    IModelDataBuilder texture(byte[] png);

    /**
     * Sets the resource-path identifier used by the client to locate the texture.
     * <p>
     * Typically follows a namespaced format, e.g. {@code "mymod:fox.png"}.
     * Must be unique to avoid conflicts with other mods or server textures.
     *
     * @param path the texture path identifier
     * @return this builder
     */
    IModelDataBuilder texturePath(String path);

    /**
     * Sets the texture animation (mcmeta) JSON string for animated textures.
     * <p>
     * This is the content of the {@code .png.mcmeta} file that accompanies animated textures.
     * Pass {@code null} or empty string if the texture is not animated.
     *
     * @param mcmeta the mcmeta JSON content, or {@code null} for static textures
     * @return this builder
     */
    IModelDataBuilder textureAnimation(String mcmeta);

    /**
     * Sets the equipment model configuration for this model.
     * <p>
     * Equipment models define how held items and armor appear on the custom model geometry.
     * This is an advanced feature — pass {@code null} if your model does not need custom equipment rendering.
     *
     * @param equipment the equipment configuration, or {@code null} to use defaults
     * @return this builder
     */
    IModelDataBuilder equipment(Object equipment);

    /**
     * Sets the model property configuration (first-person settings, animation controllers, etc.).
     * <p>
     * This is an advanced field. Most external mods do not need to set this directly.
     * Pass {@code null} to use default model properties.
     *
     * @param properties the model property object, or {@code null} for defaults
     * @return this builder
     */
    IModelDataBuilder modelProperty(Object properties);

    // -----------------------------------------------------------------------
    // Classpath resource convenience methods
    // -----------------------------------------------------------------------

    /**
     * Sets the classloader context for subsequent {@code *Resource(String)} calls.
     * <p>
     * Pass the class whose classloader should be used to locate bundled resources
     * (typically {@code getClass()} or your mod's main class). This method MUST be
     * called before any {@code *Resource(String)} method; failing to do so causes
     * those methods to throw {@link IllegalStateException} immediately.
     *
     * <p>Calling {@code source()} does not affect raw-content methods such as
     * {@link #modelContent(String)}, {@link #texture(byte[])}, etc. Those work
     * independently of this setting.
     *
     * @param anchor the class whose {@link ClassLoader} is used for resource resolution;
     *               must not be {@code null}
     * @return this builder
     */
    IModelDataBuilder source(Class<?> anchor);

    /**
     * Loads the geometry (Bedrock model) JSON from the given absolute classpath path and
     * stores it identically to {@link #modelContent(String)}.
     *
     * <p>Resolution is eager: the resource is read immediately when this method is called,
     * not deferred to {@link #build()}. {@link #source(Class)} must be called first.
     *
     * <p>Last-call-wins: calling this after {@link #modelContent(String)} (or vice-versa)
     * replaces the previously set value.
     *
     * @param classpathPath absolute classpath path (e.g. {@code "/assets/mymod/fox.geo.json"})
     * @return this builder
     * @throws IllegalStateException if {@link #source(Class)} was not called first,
     *                               or if the resource cannot be found or read
     */
    IModelDataBuilder modelResource(String classpathPath);

    /**
     * Loads the slim-variant geometry JSON from the given absolute classpath path and
     * stores it identically to {@link #slimModelContent(String)}.
     *
     * <p>Same contract as {@link #modelResource(String)}.
     *
     * @param classpathPath absolute classpath path to the slim {@code .geo.json} file
     * @return this builder
     * @throws IllegalStateException if {@link #source(Class)} was not called first,
     *                               or if the resource cannot be found or read
     */
    IModelDataBuilder slimModelResource(String classpathPath);

    /**
     * Loads the animation JSON from the given absolute classpath path and
     * stores it identically to {@link #animationContent(String)}.
     *
     * <p>Same contract as {@link #modelResource(String)}.
     *
     * @param classpathPath absolute classpath path to the {@code .animation.json} file
     * @return this builder
     * @throws IllegalStateException if {@link #source(Class)} was not called first,
     *                               or if the resource cannot be found or read
     */
    IModelDataBuilder animationResource(String classpathPath);

    /**
     * Loads the PNG texture bytes from the given absolute classpath path and
     * stores them identically to {@link #texture(byte[])}.
     *
     * <p>Same contract as {@link #modelResource(String)}.
     *
     * @param classpathPath absolute classpath path to the {@code .png} file
     * @return this builder
     * @throws IllegalStateException if {@link #source(Class)} was not called first,
     *                               or if the resource cannot be found or read
     */
    IModelDataBuilder textureResource(String classpathPath);

    /**
     * Loads the texture animation (mcmeta) JSON from the given absolute classpath path and
     * stores it identically to {@link #textureAnimation(String)}.
     *
     * <p>Same contract as {@link #modelResource(String)}.
     *
     * @param classpathPath absolute classpath path to the {@code .png.mcmeta} file
     * @return this builder
     * @throws IllegalStateException if {@link #source(Class)} was not called first,
     *                               or if the resource cannot be found or read
     */
    IModelDataBuilder textureAnimationResource(String classpathPath);

    /**
     * Adds a layer to this model and returns a nested {@link ILayerBuilder} to configure it.
     * <p>
     * Call {@link ILayerBuilder#add()} on the returned builder to finalize the layer and return
     * to this model builder. Multiple layers can be added by chaining calls.
     *
     * <pre>{@code
     * builder
     *     .layer("scarf", LayerType.TEXTURE)
     *         .texture(scarfTexture)
     *         .texturePath("mymod:scarf.png")
     *         .defaultEnabled(true)
     *         .add()
     *     .layer("glasses", LayerType.TEXTURE)
     *         .texture(glassesTexture)
     *         .texturePath("mymod:glasses.png")
     *         .add()
     * }</pre>
     *
     * @param id   the unique layer ID within this model (e.g. {@code "scarf"}, {@code "armor"})
     * @param type the layer type — {@link LayerType#MODEL} for geometry layers, {@link LayerType#TEXTURE} for texture overlays
     * @return a nested {@link ILayerBuilder} to configure the layer
     */
    ILayerBuilder layer(String id, LayerType type);

    /**
     * Builds the {@link IModelData} instance from the current builder state.
     * <p>
     * At minimum, {@link #modelId(String)} and {@link #texture(byte[])} should be set before calling this.
     * Calling {@code build()} does not reset the builder — you can continue setting fields and call
     * {@code build()} again to produce a new instance.
     *
     * @return a new immutable {@link IModelData} instance
     * @throws IllegalStateException if required fields (e.g. {@code modelId}) are not set
     */
    IModelData build();

    /**
     * Nested builder for configuring an individual layer within a model.
     * <p>
     * Obtained via {@link IModelDataBuilder#layer(String, LayerType)}.
     * When configuration is complete, call {@link #add()} to return to the parent model builder.
     *
     * <h2>Texture layer example</h2>
     * <pre>{@code
     * builder.layer("scarf", LayerType.TEXTURE)
     *     .texture(scarfPngBytes)
     *     .texturePath("mymod:fox_scarf.png")
     *     .defaultEnabled(true)
     *     .showFirstPerson(false)
     *     .add()
     * }</pre>
     *
     * <h2>Model layer example</h2>
     * <pre>{@code
     * builder.layer("hat", LayerType.MODEL)
     *     .modelId("mymod_hat")   // must match a registered model with is_layer: true
     *     .defaultEnabled(false)
     *     .add()
     * }</pre>
     */
    interface ILayerBuilder {

        /**
         * For {@link LayerType#MODEL} layers: sets the model ID of the sub-model to render as this layer.
         * <p>
         * The referenced model must be registered separately and must have {@code properties.is_layer: true}.
         *
         * @param modelId the sub-model ID
         * @return this layer builder
         */
        ILayerBuilder modelId(String modelId);

        /**
         * For {@link LayerType#TEXTURE} layers: sets the raw PNG bytes of the texture overlay.
         *
         * @param png the raw PNG bytes of the layer texture
         * @return this layer builder
         */
        ILayerBuilder texture(byte[] png);

        /**
         * For {@link LayerType#TEXTURE} layers: sets the resource-path identifier for the layer texture.
         * <p>
         * Must be unique per layer, e.g. {@code "mymod:fox_scarf.png"}.
         *
         * @param path the texture path identifier
         * @return this layer builder
         */
        ILayerBuilder texturePath(String path);

        /**
         * For {@link LayerType#TEXTURE} layers: sets the mcmeta JSON for texture animation.
         * <p>
         * Pass {@code null} or empty if the layer texture is static.
         *
         * @param mcmeta the mcmeta JSON, or {@code null}
         * @return this layer builder
         */
        ILayerBuilder textureAnimation(String mcmeta);

        /**
         * Sets whether this layer is visible by default when the model is applied.
         * <p>
         * Defaults to {@code false} (layer starts hidden until toggled via {@link IMorphAPI#applyLayer}).
         *
         * @param enabled {@code true} to show the layer by default
         * @return this layer builder
         */
        ILayerBuilder defaultEnabled(boolean enabled);

        /**
         * Sets whether this layer is rendered in first-person view.
         * <p>
         * Defaults to {@code false}.
         *
         * @param show {@code true} to render the layer in first person
         * @return this layer builder
         */
        ILayerBuilder showFirstPerson(boolean show);

        /**
         * Sets the list of bone IDs to hide on the base model while this layer is active.
         * <p>
         * Useful for MODEL layers that replace specific geometry sections.
         *
         * @param boneIds the bone IDs to hide; pass an empty list to hide nothing
         * @return this layer builder
         */
        ILayerBuilder hideBones(List<String> boneIds);

        /**
         * Sets the ARGB color tint for this layer.
         * <p>
         * Default is {@code 0xFFFFFFFF} (white = no tint). Can be overridden at runtime
         * via {@link IMorphAPI#applyLayerColor}.
         *
         * @param argb the ARGB packed int color
         * @return this layer builder
         */
        ILayerBuilder color(int argb);

        /**
         * Finalizes this layer and returns to the parent {@link IModelDataBuilder}.
         *
         * @return the parent model builder
         */
        IModelDataBuilder add();
    }
}
