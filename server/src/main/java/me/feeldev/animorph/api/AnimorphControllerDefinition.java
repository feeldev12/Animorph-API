package me.feeldev.animorph.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Describes a single animation controller attached to an {@link AnimorphModelDefinition},
 * either as a first-person or a third-person (default) controller.
 * <p>
 * There are two cases, mirroring the yml model's {@code properties.animation_controllers}/
 * {@code properties.fp_animation_controllers} entries:
 * <ul>
 *     <li>{@link #nativeController(String)} — references a controller already registered
 *     client-side via {@code ClientMorphAPI.registerController(id, ...)}. This is the equivalent
 *     of a yml controller entry with no {@code file:} key.</li>
 *     <li>{@link #content(String, String)} — a controller defined by raw Blockbench-style
 *     controller JSON content, read by the caller (e.g. via the parent
 *     {@link AnimorphModelDefinition}'s inherited {@code readText(path)} helper) and passed in
 *     directly. This is the equivalent of a yml controller entry with a {@code file:} key.</li>
 * </ul>
 * Create one via either factory method, then chain the optional {@link #transitionTime(int)} and
 * {@link #animationTransition(String, int)} setters.
 */
public final class AnimorphControllerDefinition {

    private final String id;
    private final boolean isId;
    private final String content;
    private int transitionTime = 0;
    private boolean override = false;
    private String linkedController = null;
    private int variantRerollTicks = -1;
    private final Map<String, Integer> animationTransitions = new HashMap<>();

    private AnimorphControllerDefinition(String id, boolean isId, String content) {
        this.id = id;
        this.isId = isId;
        this.content = content;
    }

    /**
     * Creates a controller that references an already-registered native controller.
     *
     * @param id the controller ID, matching a {@code ClientMorphAPI.registerController(id, ...)} registration
     * @return a new controller definition
     */
    public static AnimorphControllerDefinition nativeController(String id) {
        return new AnimorphControllerDefinition(id, true, id);
    }

    /**
     * Creates a controller defined by raw JSON content instead of a native registration.
     *
     * @param id          the controller ID, unique within this model's controller set
     * @param jsonContent the Blockbench-style controller JSON content (already read by the caller)
     * @return a new controller definition
     */
    public static AnimorphControllerDefinition content(String id, String jsonContent) {
        return new AnimorphControllerDefinition(id, false, jsonContent);
    }

    /** Sets the transition time in ticks. Defaults to {@code 0}. */
    public AnimorphControllerDefinition transitionTime(int ticks) {
        this.transitionTime = ticks;
        return this;
    }

    /**
     * Marks this controller as an override: on the bones its current animation touches, it wins
     * over every other (non-override) controller instead of blending with them. Defaults to
     * {@code false} (additive, GeckoLib's default blending behaviour).
     */
    public AnimorphControllerDefinition override(boolean override) {
        this.override = override;
        return this;
    }

    /**
     * Links this controller to the corresponding controller (by id) on the player's other
     * animatable — a 3rd-person controller links to a {@code fp_animation_controllers} id, and
     * vice versa. While this controller is actively playing an animation, its elapsed tick is
     * continuously synced to the linked controller so that switching perspective mid-animation
     * resumes instead of restarting from 0. Both controllers should play matching animations
     * (same duration/keyframe timing) for the sync to look seamless. Defaults to {@code null}
     * (no link).
     */
    public AnimorphControllerDefinition linkedController(String controllerId) {
        this.linkedController = controllerId;
        return this;
    }

    /**
     * Sets how often (in ticks) this controller should reroll its animation variants while
     * continuously staying in the same requested state, on top of the always-on reroll that
     * happens whenever the requested animation name changes (state entry). A variant is any
     * numbered sibling of a requested name (e.g. requesting {@code "standing.idle"} with
     * {@code "standing.idle.2"} also present in the model picks one of the two at random) — no
     * per-animation setup needed beyond naming the extra clips. Defaults to {@code -1} (variants
     * are only picked once per state entry, no periodic reroll).
     */
    public AnimorphControllerDefinition variantRerollTicks(int ticks) {
        this.variantRerollTicks = ticks;
        return this;
    }

    /**
     * Adds an animation transition entry. Can be called multiple times to add several entries,
     * mirroring the yml's {@code animation_transitions:} map.
     *
     * @param animationName the animation name
     * @param ticks         the transition time in ticks for that animation
     */
    public AnimorphControllerDefinition animationTransition(String animationName, int ticks) {
        this.animationTransitions.put(animationName, ticks);
        return this;
    }

    String id() { return id; }
    boolean isId() { return isId; }
    String content() { return content; }
    int transitionTime() { return transitionTime; }
    boolean override() { return override; }
    String linkedController() { return linkedController; }
    int variantRerollTicks() { return variantRerollTicks; }
    Map<String, Integer> animationTransitions() { return animationTransitions; }
}
