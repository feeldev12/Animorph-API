package me.feeldev.animorph.client.interfaces;

/**
 * Represents the state of an emote playing on a player or layer.
 */
public interface IEmoteState {

    /**
     * Checks if an emote is currently playing.
     *
     * @return {@code true} if an emote is active
     */
    boolean hasEmoting();

    /**
     * Gets the ID of the currently playing emote.
     *
     * @return the emote ID, or {@code "empty"} if none
     */
    String getEmoteId();

    /**
     * Sets the current emote ID.
     *
     * @param emoteId the emote ID to set
     */
    void setEmoteId(String emoteId);

    /**
     * Gets the animation start time offset.
     *
     * @return the start time in ticks
     */
    double getStartTime();

    /**
     * Sets the animation start time offset.
     *
     * @param startTime the start time in ticks
     */
    void setStartTime(double startTime);

    /**
     * Sets whether the emote should be reset/stopped.
     *
     * @param resetEmote {@code true} to reset the emote
     */
    void setResetEmote(boolean resetEmote);

    /**
     * Checks if the emote is flagged for reset.
     *
     * @return {@code true} if the emote should be reset
     */
    boolean isResetEmote();

    /**
     * Gets the current animation playback tick.
     *
     * @return the current tick position
     */
    double getCurrentAnimationTick();

    /**
     * Sets the current animation playback tick.
     *
     * @param currentAnimationTick the tick to set
     */
    void setCurrentAnimationTick(double currentAnimationTick);
}
