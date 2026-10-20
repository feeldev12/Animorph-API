package me.feeldev.animorph.client.interfaces.geo;

import me.feeldev.animorph.client.models.CustomRawAnimation;
import me.feeldev.animorph.common.models.VariantMode;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.EasingType;
import java.util.function.Function;

public interface IAnimationController<T extends GeoAnimatable> {

    Function<T, EasingType> animorph$getOverrideEasingTypeFunction();

    void animorph$setIsJustStarting(boolean isJustStarting);

    boolean animorph$isJustStarting();

    double animorph$getStartTick();

    void animorph$setAnimationStartTick(double tick);

    double animorph$getCurrentAnimationTick();

    boolean animorph$isSaturating();

    void animorph$resetSaturation();

    /**
     * Drops the controller's current and queued animation. GeckoLib's {@code PlayState.STOP} only
     * flags the controller STOPPED and keeps the old animation, so a later tick would report it as
     * finished ({@code hasAnimationFinished}) or, on CONTINUE, resume it. Clearing it makes the
     * next {@code process()} see nothing to play.
     */
    void animorph$clearCurrentAnimation();

    double animorph$getTransitionLength();

    /**
     * Flat snapshot of the processor's bones, handed over right before {@code process} by
     * Animorph's blended tick (null otherwise). Lets the bone queue reset skip the bone map when
     * the bones did not change — see {@code BoneQueueReuse#canReuseFlat}.
     */
    void animorph$setProcessorBones(software.bernie.geckolib.cache.object.GeoBone[] bones);

    /**
     * Set by Animorph's blended tick once every bone queue of this controller is known to be empty
     * (applied and drained this tick); cleared as soon as GeckoLib refills them.
     */
    void animorph$setQueuesDrained(boolean drained);

    /**
     * This controller's bone queues in the processor's bone order, valid for the {@code process}
     * call that just ran (it is rebuilt or re-validated by {@code createInitialQueues}, which every
     * non-STOPPED {@code process} runs), or null when they are not available as a one-to-one flat
     * array (bones not handed over, repeated bone names). Callers fall back to the queue map then.
     * Elements are {@code BoneAnimationQueue}s.
     */
    Object[] animorph$getFlatQueues();

    /**
     * Whether this controller's bones win over every other (non-override) controller touching
     * the same bones this tick, instead of stacking additively on top of them. Scoped to
     * whichever bones this controller's current animation actually touches — bones it doesn't
     * animate are untouched and still driven normally by other controllers.
     */
    boolean animorph$isOverride();

    void animorph$setOverride(boolean override);

    /**
     * Per-tick override for whichever specific animation/emote is currently playing on this
     * controller, taking priority over {@link #animorph$isOverride()} when non-null. Lets a single
     * controller (e.g. the shared "emote" controller, or a {@code BBAnimController} running a
     * model's own animator state machine) mix override and non-override animations, instead of the
     * whole controller being permanently one or the other. Reset to {@code null} at the start of
     * every tick before the controller's state handler runs — a state handler that never calls
     * {@link #animorph$setCurrentOverride(Boolean)} leaves this {@code null} and the controller
     * falls back to its static {@link #animorph$isOverride()} value, unchanged from before.
     */
    @org.jetbrains.annotations.Nullable
    Boolean animorph$getCurrentOverride();

    void animorph$setCurrentOverride(@org.jetbrains.annotations.Nullable Boolean override);

    /**
     * The id of the controller (on the player's other animatable — 3rd-person body or
     * first-person arm, whichever this one isn't) that this controller keeps its animation tick
     * synced with. When this controller is actively running an animation, its current tick is
     * pushed to the linked controller's {@link #animorph$setAnimationStartTick(double)} every
     * frame, so switching perspective mid-animation resumes instead of restarting from 0.
     */
    @org.jetbrains.annotations.Nullable
    String animorph$getLinkedControllerId();

    void animorph$setLinkedControllerId(@org.jetbrains.annotations.Nullable String linkedControllerId);

    /**
     * Whether this controller's {@code BoneAnimationQueue} map had at least one non-empty
     * rotation/position/scale sub-queue this frame, computed once by
     * {@code AnimationProcessorMixin#blendedTickImpl} right after {@code AnimationController#process}
     * while it resets bones to their initial pose. {@code false} lets the later additive/override/
     * molang apply passes skip iterating this controller's bone-queue map entirely, since every
     * queue would poll to {@code null} anyway (see {@code BoneQueueActivity}).
     */
    boolean animorph$hasActiveBoneQueues();

    void animorph$setHasActiveBoneQueues(boolean hasActiveBoneQueues);

    /**
     * Primes this controller for an instant (zero-blend) resume: saves its current transition
     * length (once) and forces it to 0, so the next time it starts running a synced animation
     * (see {@link #animorph$setAnimationStartTick(double)}) it snaps straight to the correct tick
     * instead of blending in toward the animation's tick-0 frame first. Automatically restored
     * once the controller actually resumes running.
     */
    void animorph$primeInstantResume();

    /**
     * Whether this controller hasn't actively ticked recently — i.e. whatever view it belongs to
     * isn't currently being rendered anywhere. Used to guard the linked-controller sync so it
     * doesn't fight a linked controller that's ALSO genuinely active right now (e.g. the
     * inventory screen's 3rd-person player paperdoll rendering while you're simultaneously in
     * first person): forcing a reset onto an actively-running controller every tick would prevent
     * it from ever settling into a stable running state, freezing its animation in place.
     */
    boolean animorph$isDormant();

    /**
     * How often (in ticks) this controller should reroll its animation variants while
     * continuously staying in the same requested state, on top of the always-on reroll that
     * happens whenever the requested base name changes (state entry). {@code <= 0} disables the
     * periodic reroll — variants are then only picked once per state entry.
     */
    int animorph$getVariantRerollTicks();

    void animorph$setVariantRerollTicks(int ticks);

    /**
     * Per-tick selection strategy for whichever specific animation {@link #animorph$resolveVariant}
     * is about to resolve, taking priority over the default {@link VariantMode#RANDOM} pick.
     * Mirrors {@link #animorph$setCurrentOverride(Boolean)} — reset to {@code null} (falls back
     * to {@link VariantMode#RANDOM}) at the start of every tick before the controller's state
     * handler runs, so a handler that never calls this leaves variant picking random as before.
     */
    void animorph$setCurrentVariantMode(@org.jetbrains.annotations.Nullable VariantMode mode);

    /**
     * Resolves {@code baseAnimationName} to one of its numbered variants (e.g. requesting
     * {@code "standing.idle"} with {@code "standing.idle.2"}/{@code "standing.idle.3"} also
     * present in the model picks one of the three per {@link #animorph$setCurrentVariantMode}'s
     * strategy), so authors get animation variety for free just by adding numbered clips
     * alongside the base name — no controller-side bookkeeping required.
     * <p>
     * A variant is picked once when {@code baseAnimationName} changes from the last call (state
     * entry), and again every {@link #animorph$getVariantRerollTicks()} ticks after that if
     * configured; every other call in between returns the same previously-picked variant, so
     * GeckoLib doesn't see a "different" animation and restart a merely-continuing clip. Falls
     * back to {@code baseAnimationName} unchanged if no numbered variants exist.
     *
     * @param baseAnimationName the animation name as the caller would normally request it
     * @return the resolved variant, wrapped ready to hand to {@code AnimationState#setAndContinue}
     */
    CustomRawAnimation animorph$resolveVariant(String baseAnimationName);
}
