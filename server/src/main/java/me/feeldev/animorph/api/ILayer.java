package me.feeldev.animorph.api;

import java.awt.*;

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

    /**
     * ARGB packed color tint applied when rendering this layer.
     * {@code 0xFFFFFFFF} means no tint (full white = render as-is).
     * Can be overridden at runtime via {@link IMorphAPI#applyLayerColor}.
     *
     * @return ARGB packed int, defaults to {@code Color.WHITE.getRGB()}
     */
    default int color() {
        return Color.WHITE.getRGB();
    }

    /**
     * Optional exclusive group id. When applying this layer via a group-aware API call (e.g.
     * {@code applyExclusiveLayer}), any other currently-visible layer on the same model sharing
     * this same non-null group gets hidden first — e.g. tag every weapon-model layer with the
     * same group so only one is ever shown at once. {@code null} (the default) means this layer
     * doesn't participate in any exclusive group.
     *
     * @return the exclusive group id, or {@code null} if none
     */
    default String group() {
        return null;
    }
}
