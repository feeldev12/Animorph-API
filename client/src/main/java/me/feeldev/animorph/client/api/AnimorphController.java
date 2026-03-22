package me.feeldev.animorph.client.api;

import me.feeldev.animorph.client.interfaces.IPlayerData;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;

/**
 * Base class for creating custom animation controllers.
 * <p>
 * Extend this class to implement your own animation logic. Override
 * {@link #createAnimationStateHandler(IPlayerData)} to define what animations
 * play and when.
 *
 * <pre>{@code
 * public class WingsController extends AnimorphController {
 *     public WingsController() {
 *         super("wings", 5);
 *     }
 *
 *     @Override
 *     protected AnimationController.AnimationStateHandler<IPlayerData>
 *     createAnimationStateHandler(IPlayerData entity) {
 *         return animationState -> {
 *             AbstractClientPlayerEntity player = entity.animorph$getPlayer();
 *             if (player.isFallFlying()) {
 *                 return animationState.setAndContinue(
 *                     RawAnimation.begin().thenLoop("animation.wings_flap")
 *                 );
 *             }
 *             return PlayState.STOP;
 *         };
 *     }
 * }
 * }</pre>
 *
 * Register your controller with {@link ClientMorphAPI#registerController(String, AnimorphController)}.
 */
public abstract class AnimorphController {
    protected final String controllerName;
    protected final int transitionTime;

    /**
     * Creates a new animation controller.
     *
     * @param controllerName the unique name for this controller (referenced by models)
     * @param transitionTime the transition time in ticks when switching animations (0 = instant)
     */
    public AnimorphController(String controllerName, int transitionTime) {
        this.controllerName = controllerName;
        this.transitionTime = transitionTime;
    }

    /**
     * Creates the GeckoLib animation controller instance for a player.
     * <p>
     * Override this only if you need full control over the controller creation.
     * For most cases, override {@link #createAnimationStateHandler(IPlayerData)} instead.
     *
     * @param entity the player data
     * @return the GeckoLib animation controller
     */
    public AnimationController<IPlayerData> createAnimationController(IPlayerData entity) {
        throw new NotImplementedException();
    }

    /**
     * Creates the animation state handler that defines the animation logic.
     * <p>
     * This is the main method to override. The handler is called every animation tick
     * and should return:
     * <ul>
     *     <li>{@link software.bernie.geckolib.animation.PlayState#CONTINUE} - to keep the current animation playing</li>
     *     <li>{@link software.bernie.geckolib.animation.PlayState#STOP} - to stop the animation</li>
     *     <li>{@code animationState.setAndContinue(animation)} - to play a specific animation</li>
     * </ul>
     *
     * @param entity the player data (access player via {@code entity.animorph$getPlayer()})
     * @return the animation state handler
     */
    protected AnimationController.AnimationStateHandler<IPlayerData> createAnimationStateHandler(IPlayerData entity) {
        throw new NotImplementedException();
    }

    /**
     * Called to check if this controller should be interrupted.
     * <p>
     * Override this to define conditions that stop your animation
     * (e.g. when an emote starts playing).
     *
     * @param animationState the current animation state
     * @return {@code true} to interrupt and stop the animation
     */
    protected boolean interrupt(AnimationState<IPlayerData> animationState) {
        throw new NotImplementedException();
    }

    /**
     * Returns the name of this controller.
     *
     * @return the controller name
     */
    public String getControllerName() {
        throw new NotImplementedException();
    }

    /**
     * Returns the transition time in ticks.
     *
     * @return the transition time
     */
    public float getTransitionTime() {
        throw new NotImplementedException();
    }
}
