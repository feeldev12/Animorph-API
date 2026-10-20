package me.feeldev.animorph.common.models;

/**
 * Per-animation tuning for native (non-Blockbench) controllers, keyed by animation name in
 * {@code AnimationControllerData#animationTransitions()}. Bundles every per-animation knob in one
 * place instead of a separate parallel map per property — add new fields here as they come up
 * rather than introducing another {@code Map<String, X>} alongside this one.
 * <p>
 * {@code override} is a tri-state {@link Boolean} rather than a primitive: {@code null} means "not
 * configured for this animation" (inherit the controller's own static override default), while
 * {@code true}/{@code false} explicitly force it either way for this one animation — including
 * forcing it {@code false} to blend normally on a controller whose own default is {@code true}.
 * A primitive {@code boolean} can't express that last case, since "not configured" and
 * "explicitly false" would both collapse to the same value.
 */
public record AnimationTuning(int transitionTime, Boolean override, VariantMode mode) {

    /**
     * Sentinel for "no transition override configured" — falls back to the controller's own transitionTime.
     */
    public static final int NO_TRANSITION = -1;

    public AnimationTuning {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public AnimationTuning(int transitionTime, Boolean override) {
        this(transitionTime, override, VariantMode.RANDOM);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public AnimationTuning(int transitionTime) {
        this(transitionTime, null);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
