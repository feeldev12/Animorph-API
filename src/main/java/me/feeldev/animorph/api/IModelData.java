package me.feeldev.animorph.api;

/**
 * Represents a registered model in the Animorph system.
 * <p>
 * A model defines the visual appearance of a player, including its geometry,
 * textures, animations, and layers.
 */
public interface IModelData {

    /**
     * Returns the unique identifier of this model.
     *
     * @return the model ID (e.g. {@code "dragon"}, {@code "fairy"})
     */
    String modelId();

    /**
     * Finds a layer within this model by its ID.
     * <p>
     * Layers can be either model layers (additional geometry) or texture layers (texture overlays).
     *
     * @param layerId the layer ID to search for
     * @return the layer if found, or {@code null} if no layer with the given ID exists
     */
    ILayer getLayer(String layerId);
}
