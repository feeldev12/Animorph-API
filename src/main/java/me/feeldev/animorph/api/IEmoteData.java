package me.feeldev.animorph.api;

/**
 * Represents a registered emote/animation in the Animorph system.
 * <p>
 * Emotes are animations that can be played on a player's model or on specific layers.
 */
public interface IEmoteData {

    /**
     * Returns the unique identifier of this emote/animation.
     *
     * @return the animation ID (e.g. {@code "wave"}, {@code "dance"})
     */
    String animationId();
}
