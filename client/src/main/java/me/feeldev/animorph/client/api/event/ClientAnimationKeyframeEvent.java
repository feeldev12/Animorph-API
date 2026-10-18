package me.feeldev.animorph.client.api.event;

import me.feeldev.animorph.api.event.AnimorphEvent;

import java.util.UUID;

/**
 * Fired on the client when a player's animation crosses an Animorph action keyframe: one placed with the Animorph
 * Blockbench plugin ({@code animorph:<event>} / {@code animorph:server:<event>} in the animation's timeline) or added
 * from code with {@code ClientMorphAPI#addAnimationEvent}. Fired once per crossing for every visible player (body
 * animation only), on the client's own animation clock, so it is roughly in sync between clients but not exact.
 * Body animations only advance while the body model renders: in first person (unless the model sets
 * {@code showModel}) the local player's events do not fire.
 * <p>
 * Server-marked events of the local player are forwarded to the server automatically; a listener can also forward
 * any event of the local player with {@link #sendToServer()}. At most one small packet per crossing is sent, and only
 * when one of those asked for it. The server receives it as {@code AnimationKeyframeEvent}: it is reported by the
 * client, so the server must validate whatever it does with it.
 *
 * <pre>{@code
 * ClientMorphAPI.getEventBus().register(ClientAnimationKeyframeEvent.class, event -> {
 *     if (event.getEvent().equals("release")) {
 *         spawnParticles(event.getPlayer());
 *         event.sendToServer();
 *     }
 * });
 * }</pre>
 */
public class ClientAnimationKeyframeEvent extends AnimorphEvent<UUID> {
    private final String animation;
    private final String event;
    private final double tick;
    private final boolean localPlayer;
    private boolean sendToServer;

    public ClientAnimationKeyframeEvent(UUID playerId, String animation, String event, double tick,
                                        boolean localPlayer, boolean sendToServer) {
        super(playerId);
        this.animation = animation;
        this.event = event;
        this.tick = tick;
        this.localPlayer = localPlayer;
        this.sendToServer = sendToServer;
    }

    /** The playing animation's name, as in the animation file. */
    public String getAnimation() {
        return animation;
    }

    /** The event name ({@code <event>} in {@code animorph:<event>}). */
    public String getEvent() {
        return event;
    }

    /** The animation tick the keyframe sits on. */
    public double getTick() {
        return tick;
    }

    /** Whether the animated player is this client's own player (only its events can reach the server). */
    public boolean isLocalPlayer() {
        return localPlayer;
    }

    /** Whether the event will be forwarded to the server after the listeners ran. */
    public boolean isSentToServer() {
        return sendToServer && localPlayer;
    }

    /**
     * Forwards this event to the server once the listeners ran (no-op for other players' animations). Server-marked
     * keyframes ({@code animorph:server:<event>}) are forwarded without calling this.
     */
    public void sendToServer() {
        this.sendToServer = true;
    }
}
