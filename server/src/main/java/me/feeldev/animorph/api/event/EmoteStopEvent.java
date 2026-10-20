package me.feeldev.animorph.api.event;

import java.util.Set;

/**
 * Fired before an emote is stopped on a player.
 * <p>
 * Cancelling this event prevents the emote from being stopped.
 *
 * @param <P> the player type
 */
public class EmoteStopEvent<P> extends AnimorphEvent<P> implements Cancellable {

    public EmoteStopEvent(P player, String emoteId, Set<String> layerIds) {
        super(player);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the emote ID that is being stopped.
     *
     * @return the emote ID
     */
    public String getEmoteId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the layer IDs the emote was playing on.
     *
     * @return the layer IDs, or {@code null} if it was a model-level emote
     */
    public Set<String> getLayerIds() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Whether this was a layer-level emote.
     *
     * @return {@code true} if the emote was targeting specific layers
     */
    public boolean isLayerEmote() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    @Override
    public void setCancelled(boolean cancelled) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
