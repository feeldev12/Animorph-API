package me.feeldev.animorph.api;

import java.util.List;

/**
 * Describes a single layer attached to an {@link AnimorphModelDefinition}.
 * <p>
 * Create one via {@link #texture(String, byte[])} or {@link #model(String, String)},
 * then chain the optional setters. Texture bytes are read by the parent
 * {@link AnimorphModelDefinition}'s own {@code readBytes(String)} — this class has no
 * classloader access of its own by design.
 */
public final class AnimorphLayerDefinition {

    private AnimorphLayerDefinition(String layerId, LayerType type) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Creates a texture-type layer: a PNG overlay on top of the base model geometry.
     * <p>
     * Its registration identity is always its {@code layerId} — there is no separate
     * texture-id concept, matching how the top-level model's own texture is always keyed
     * by its {@code modelId}.
     *
     * @param layerId      the unique layer ID within the model
     * @param textureBytes the raw PNG bytes (e.g. from {@code readBytes(path)} in your model definition)
     * @return a new layer definition
     */
    public static AnimorphLayerDefinition texture(String layerId, byte[] textureBytes) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Creates a model-type layer: another registered model (with {@code is_layer: true}) rendered on top.
     *
     * @param layerId the unique layer ID within the model
     * @param modelId the ID of an already-registered model with {@code is_layer: true}
     * @return a new layer definition
     */
    public static AnimorphLayerDefinition model(String layerId, String modelId) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Sets whether this layer is visible by default when the model is applied. Defaults to {@code false}.
     */
    public AnimorphLayerDefinition defaultEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Sets whether this layer renders in first person. Defaults to {@code false}.
     */
    public AnimorphLayerDefinition showFirstPerson(boolean show) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Sets which base-model bones to hide while this layer is active.
     */
    public AnimorphLayerDefinition hideBones(List<String> boneIds) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Sets the ARGB tint color. Defaults to {@code 0xFFFFFFFF} (no tint).
     */
    public AnimorphLayerDefinition color(int argb) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Sets the {@code .mcmeta} animation JSON for an animated texture layer. Texture layers only.
     */
    public AnimorphLayerDefinition textureAnimation(String mcmeta) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Sets the Minecraft RenderType used to render this layer, e.g. {@code "entity_cutout_no_cull"},
     * {@code "entity_translucent"}, {@code "entity_solid"}. Defaults to {@code ""}, matching the
     * disk-config default-handling for layers ({@code render_type} is optional per-layer).
     */
    public AnimorphLayerDefinition renderType(String renderType) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Sets the exclusive group id — see {@link ILayer#group()}. Any other currently-visible
     * layer on the same model sharing this same group gets hidden when this one is shown via
     * {@code applyExclusiveLayer}. Defaults to {@code null} (no group).
     */
    public AnimorphLayerDefinition group(String group) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    String layerId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    LayerType type() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    String modelId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    byte[] textureBytes() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    String textureAnimation() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    boolean defaultEnabled() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    boolean showFirstPerson() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    List<String> hideBones() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    int color() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    String renderType() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    String group() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
