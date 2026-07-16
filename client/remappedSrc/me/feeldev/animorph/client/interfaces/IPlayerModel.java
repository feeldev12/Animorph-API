package me.feeldev.animorph.client.interfaces;

/**
 * Represents the model assigned to a player on the client side.
 * <p>
 * Provides methods to query the model's animation capabilities.
 */
public interface IPlayerModel {

    /**
     * Checks if a specific animation exists in this model.
     *
     * @param animationId the animation ID to look for (e.g. {@code "pose.standing"}, {@code "animation.wings_flap"})
     * @return {@code true} if the animation is available in the model
     */
    boolean existAnimation(String animationId);
}
