package me.feeldev.animorph.common.models;

/**
 * Selection strategy for {@link AnimationTuning#mode()}, controlling how
 * {@code IAnimationController#animorph$resolveVariant(String)} picks among a requested
 * animation's numbered variants (e.g. {@code "standing.jump"}/{@code "standing.jump.2"}).
 */
public enum VariantMode {
    /** Pick a variant at random on every reroll. Default — matches the original behavior. */
    RANDOM,
    /**
     * Cycle through variants in ascending suffix order (base, {@code .2}, {@code .3}, ...,
     * base, ...), carrying the index across state re-entries so consecutive rerolls alternate
     * deterministically instead of risking a repeat.
     */
    SEQUENTIAL
}
