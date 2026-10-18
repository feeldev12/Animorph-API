package me.feeldev.animorph.api.event;

/**
 * Fired on the server when a player's client reports that one of its own animations crossed an Animorph action
 * keyframe forwarded to the server: a keyframe placed as {@code animorph:server:<event>} with the Animorph Blockbench
 * plugin, an event added from code with {@code sendToServer}, or one a client listener forwarded with
 * {@code ClientAnimationKeyframeEvent#sendToServer()}.
 * <p>
 * The event is <b>reported by the client</b>: Animorph only checks that it comes from the animated player itself,
 * that the names are valid and that the player is not flooding events. A modified client can send it early or not at
 * all, so validate the game state before acting on it (for example, that the player really holds the item it throws).
 *
 * <pre>{@code
 * AnimorphProvider.getApi().getEventBus().register(AnimationKeyframeEvent.class, event -> {
 *     if (event.getEvent().equals("release")) {
 *         throwHeldItem(event.getPlayer());
 *     }
 * });
 * }</pre>
 *
 * @param <P> the player type
 */
public class AnimationKeyframeEvent<P> extends AnimorphEvent<P> {
    private final String animation;
    private final String event;

    public AnimationKeyframeEvent(P player, String animation, String event) {
        super(player);
        this.animation = animation;
        this.event = event;
    }

    /** The animation the client was playing, as named in the animation file. */
    public String getAnimation() {
        return animation;
    }

    /** The event name ({@code <event>} in {@code animorph:server:<event>}). */
    public String getEvent() {
        return event;
    }
}
