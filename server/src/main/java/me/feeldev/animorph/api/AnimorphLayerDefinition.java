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

    private final String layerId;
    private final LayerType type;
    private String modelId;
    private byte[] textureBytes;
    private String textureAnimation;
    private boolean defaultEnabled;
    private boolean showFirstPerson;
    private List<String> hideBones = List.of();
    private int color = 0xFFFFFFFF;
    private String renderType = "";

    private AnimorphLayerDefinition(String layerId, LayerType type) {
        this.layerId = layerId;
        this.type = type;
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
        AnimorphLayerDefinition layer = new AnimorphLayerDefinition(layerId, LayerType.TEXTURE);
        layer.textureBytes = textureBytes;
        return layer;
    }

    /**
     * Creates a model-type layer: another registered model (with {@code is_layer: true}) rendered on top.
     *
     * @param layerId the unique layer ID within the model
     * @param modelId the ID of an already-registered model with {@code is_layer: true}
     * @return a new layer definition
     */
    public static AnimorphLayerDefinition model(String layerId, String modelId) {
        AnimorphLayerDefinition layer = new AnimorphLayerDefinition(layerId, LayerType.MODEL);
        layer.modelId = modelId;
        return layer;
    }

    /** Sets whether this layer is visible by default when the model is applied. Defaults to {@code false}. */
    public AnimorphLayerDefinition defaultEnabled(boolean enabled) {
        this.defaultEnabled = enabled;
        return this;
    }

    /** Sets whether this layer renders in first person. Defaults to {@code false}. */
    public AnimorphLayerDefinition showFirstPerson(boolean show) {
        this.showFirstPerson = show;
        return this;
    }

    /** Sets which base-model bones to hide while this layer is active. */
    public AnimorphLayerDefinition hideBones(List<String> boneIds) {
        this.hideBones = boneIds != null ? boneIds : List.of();
        return this;
    }

    /** Sets the ARGB tint color. Defaults to {@code 0xFFFFFFFF} (no tint). */
    public AnimorphLayerDefinition color(int argb) {
        this.color = argb;
        return this;
    }

    /** Sets the {@code .mcmeta} animation JSON for an animated texture layer. Texture layers only. */
    public AnimorphLayerDefinition textureAnimation(String mcmeta) {
        this.textureAnimation = mcmeta;
        return this;
    }

    /**
     * Sets the Minecraft RenderType used to render this layer, e.g. {@code "entity_cutout_no_cull"},
     * {@code "entity_translucent"}, {@code "entity_solid"}. Defaults to {@code ""}, matching the
     * disk-config default-handling for layers ({@code render_type} is optional per-layer).
     */
    public AnimorphLayerDefinition renderType(String renderType) {
        this.renderType = renderType != null ? renderType : "";
        return this;
    }

    String layerId() { return layerId; }
    LayerType type() { return type; }
    String modelId() { return modelId; }
    byte[] textureBytes() { return textureBytes; }
    String textureAnimation() { return textureAnimation; }
    boolean defaultEnabled() { return defaultEnabled; }
    boolean showFirstPerson() { return showFirstPerson; }
    List<String> hideBones() { return hideBones; }
    int color() { return color; }
    String renderType() { return renderType; }
}
