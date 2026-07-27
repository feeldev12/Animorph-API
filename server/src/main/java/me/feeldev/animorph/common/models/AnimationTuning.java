package me.feeldev.animorph.common.models;

/**
 * Per-animation tuning (transition time + override), keyed by animation name — used by
 * {@link me.feeldev.animorph.api.AnimorphControllerDefinition#animationTransition(String, int)}
 * and {@link me.feeldev.animorph.api.AnimorphControllerDefinition#animationOverride(String, boolean)}.
 * Bundles every per-animation knob in one place instead of a separate parallel map per property —
 * add new fields here as they come up rather than introducing another {@code Map<String, X>}
 * alongside this one.
 */
public record AnimationTuning(int transitionTime, boolean override) {

    /** Sentinel for "no transition override configured" — falls back to the controller's own transitionTime. */
    public static final int NO_TRANSITION = -1;

    public AnimationTuning(int transitionTime) {
        this(transitionTime, false);
    }
}
