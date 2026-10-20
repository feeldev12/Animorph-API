package me.feeldev.animorph.api;

import java.awt.*;
import java.util.List;
import java.util.Set;

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
     * Whether this layer should be automatically enabled when the base model is applied.
     *
     * @return {@code true} if the layer is active by default
     */
    boolean defaultEnabled();

    List<String> hideBones();

    /**
     * Bones that should NOT be overwritten by syncArmorAnimation when this layer is rendered.
     * Allows the layer to retain its own animation for specific bones instead of inheriting
     * the base model's transform.
     *
     * @return set of bone names to exclude from armor sync
     */
    default Set<String> syncExclusions() {
        return Set.of();
    }

    /**
     * Animation controller IDs that should be used when this layer is rendered in first-person view.
     * If empty, the regular controllers() (third-person) are used as fallback.
     *
     * @return set of first-person animation controller IDs
     */
    default Set<String> fpControllers() {
        return Set.of();
    }

    /**
     * ARGB packed color tint applied when rendering this layer.
     * {@code 0xFFFFFFFF} means no tint (full white = render as-is).
     * Can be overridden at runtime via the API on a per-player basis.
     *
     * @return ARGB packed int, defaults to {@code Color.WHITE}
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
