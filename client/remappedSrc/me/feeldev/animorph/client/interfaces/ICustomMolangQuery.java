package me.feeldev.animorph.client.interfaces;

import software.bernie.geckolib.animation.AnimationState;

/**
 * Defines a custom Molang query that is evaluated every animation tick.
 * <p>
 * Implement this interface to set Molang variables based on the current
 * animation state and player data.
 *
 * @param <T> the player data type
 * @see me.feeldev.animorph.client.api.ClientMorphAPI#addMolangQuery(ICustomMolangQuery)
 */
public interface ICustomMolangQuery<T extends IPlayerData> {

    /**
     * Called every animation tick to update Molang variables.
     *
     * @param animationState the current animation state with access to the player data
     * @param animTime       the current animation time in ticks
     */
    void apply(AnimationState<T> animationState, double animTime);
}
