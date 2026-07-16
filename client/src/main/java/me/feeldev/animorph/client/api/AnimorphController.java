package me.feeldev.animorph.client.api;

import me.feeldev.animorph.client.interfaces.IPlayerData;
import me.feeldev.animorph.client.models.CustomRawAnimation;
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
    protected final boolean override;
    protected final String linkedControllerId;
    protected final int variantRerollTicks;

    /**
     * Creates a new animation controller.
     *
     * @param controllerName the unique name for this controller (referenced by models)
     * @param transitionTime the transition time in ticks when switching animations (0 = instant)
     */
    public AnimorphController(String controllerName, int transitionTime) {
        this(controllerName, transitionTime, false, null, -1);
    }

    /**
     * Creates a new animation controller.
     *
     * @param controllerName the unique name for this controller (referenced by models)
     * @param transitionTime the transition time in ticks when switching animations (0 = instant)
     * @param override       if {@code true}, this controller's bones win over every other
     *                        (non-override) controller touching the same bones, instead of
     *                        stacking additively on top of them (e.g. a weapon controller that
     *                        shouldn't blend with the walk/run controller on the arms)
     */
    public AnimorphController(String controllerName, int transitionTime, boolean override) {
        this(controllerName, transitionTime, override, null, -1);
    }

    /**
     * Creates a new animation controller.
     *
     * @param controllerName     the unique name for this controller (referenced by models)
     * @param transitionTime     the transition time in ticks when switching animations (0 = instant)
     * @param override           if {@code true}, this controller's bones win over every other
     *                           (non-override) controller touching the same bones, instead of
     *                           stacking additively on top of them
     * @param linkedControllerId the id of the corresponding controller on the player's other
     *                           animatable (a 3rd-person controller links to a
     *                           {@code fp_animation_controllers} id, and vice versa). While this
     *                           controller is actively playing an animation, its elapsed tick is
     *                           continuously synced to the linked controller so that switching
     *                           perspective mid-animation resumes instead of restarting from 0.
     *                           Pass {@code null} for no link.
     */
    public AnimorphController(String controllerName, int transitionTime, boolean override, String linkedControllerId) {
        this(controllerName, transitionTime, override, linkedControllerId, -1);
    }

    /**
     * Creates a new animation controller.
     *
     * @param controllerName     the unique name for this controller (referenced by models)
     * @param transitionTime     the transition time in ticks when switching animations (0 = instant)
     * @param override           if {@code true}, this controller's bones win over every other
     *                           (non-override) controller touching the same bones, instead of
     *                           stacking additively on top of them
     * @param linkedControllerId the id of the corresponding controller on the player's other
     *                           animatable, or {@code null} for no link (see the 4-arg constructor)
     * @param variantRerollTicks how often (in ticks) to reroll animation variants while
     *                           continuously staying in the same requested state, on top of the
     *                           always-on reroll on state entry — see {@code
     *                           IAnimationController#animorph$resolveVariant}. {@code <= 0}
     *                           disables the periodic reroll (variants only picked on state entry).
     */
    public AnimorphController(String controllerName, int transitionTime, boolean override, String linkedControllerId, int variantRerollTicks) {
        this.controllerName = controllerName;
        this.transitionTime = transitionTime;
        this.override = override;
        this.linkedControllerId = linkedControllerId;
        this.variantRerollTicks = variantRerollTicks;
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
        return new AnimationController<>(entity, controllerName, transitionTime, createAnimationStateHandler(entity));
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
        return false;
    }

    /**
     * Returns the name of this controller.
     *
     * @return the controller name
     */
    public String getControllerName() {
        return controllerName;
    }

    /**
     * Returns the transition time in ticks.
     *
     * @return the transition time
     */
    public float getTransitionTime() {
        return transitionTime;
    }

    /**
     * Returns whether this controller overrides other controllers on the bones it animates.
     *
     * @return {@code true} if this controller's bones win over other controllers instead of blending with them
     */
    public boolean isOverride() {
        return override;
    }

    /**
     * Returns the id of the linked controller (on the player's other animatable), if any.
     *
     * @return the linked controller id, or {@code null} if this controller isn't linked
     */
    public String getLinkedControllerId() {
        return linkedControllerId;
    }

    /**
     * Returns how often (in ticks) this controller rerolls animation variants while continuously
     * staying in the same state.
     *
     * @return the reroll interval in ticks, or a value {@code <= 0} if periodic reroll is disabled
     */
    public int getVariantRerollTicks() {
        return variantRerollTicks;
    }

    /**
     * Resolves an animation name to one of its numbered variants, if any exist (e.g. requesting
     * {@code "gun.pistol.idle"} with {@code "gun.pistol.idle.2"} also present picks one of the
     * two at random). Call this from {@link #createAnimationStateHandler(IPlayerData)} instead of
     * building {@code RawAnimation.begin().thenPlay(name)} directly wherever you want variety —
     * falls back to the name unchanged if no numbered variants exist, so it's always safe to call.
     *
     * <pre>{@code
     * return animationState.setAndContinue(
     *     resolveVariant(animationState, "gun.pistol.idle").getRawAnimation()
     * );
     * }</pre>
     *
     * @param animationState the current animation state (used to reach the per-player controller)
     * @param baseAnimationName the animation name as you'd normally request it
     * @return the resolved variant
     */
    protected CustomRawAnimation resolveVariant(AnimationState<IPlayerData> animationState, String baseAnimationName) {
        return new CustomRawAnimation(baseAnimationName);
    }
}
