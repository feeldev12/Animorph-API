package me.feeldev.animorph.api;

/**
 * Represents a layer within a model.
 * <p>
 * Layers allow toggling additional visual elements on top of a base model.
 * There are two types:
 * <ul>
 *     <li>{@link LayerType#MODEL} - an additional geometry model rendered on top of the base model</li>
 *     <li>{@link LayerType#TEXTURE} - a texture overlay applied to the base model</li>
 * </ul>
 */
public interface ILayer {

    /**
     * Returns the unique identifier of this layer within its parent model.
     *
     * @return the layer ID
     */
    String id();

    /**
     * Returns the type of this layer.
     *
     * @return {@link LayerType#MODEL} or {@link LayerType#TEXTURE}
     */
    LayerType type();

    /**
     * Whether this layer should be visible in first-person view.
     *
     * @return {@code true} if the layer renders in first person
     */
    boolean showFirstPerson();
}
