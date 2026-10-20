package me.feeldev.animorph.api;

import java.util.List;

/**
 * Per-player replacements for the {@code extended_pose} movement animations (idle, walk, sprint...),
 * so a player can move with animations other than their model's own — e.g. a walk from one pack
 * and a sprint from another.
 * <p>
 * Each {@link Source} is an animation file (a {@code client:<id>} or a remote
 * {@code https://...#sha256=...} ref, so the content never travels in a game packet) and the
 * Animorph states it replaces ({@code standing.walk}, {@code standing.walk.hold.sword}...): the
 * file's animation named {@code extended_pose.<state>} plays instead of the model's. A state a
 * source replaces also hides the model's held-item variants of it that the source doesn't have,
 * so one action never mixes two styles.
 *
 * @param modelId the model these apply to (the player's own avatar); {@code null} for any model.
 *                A player switched to another model (a Morph) keeps that model's animations.
 * @param sources the files and the states each one replaces; a state in two sources uses the first
 */
public record AnimationOverrides(String modelId, List<Source> sources) {

    public static final AnimationOverrides NONE = null;

    public AnimationOverrides {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Every file ref, e.g. to prefetch them before the player shows up.
     */
    public List<String> fileRefs() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * @param fileRef the animation file: {@code client:<id>} or {@code https://...#sha256=<hex>}
     * @param states  Animorph states without the {@code extended_pose.} prefix
     */
    public record Source(String fileRef, List<String> states) {

        public Source {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }
    }
}
