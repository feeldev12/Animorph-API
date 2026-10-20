package me.feeldev.animorph.api.event;

import java.util.Set;

/**
 * Fired before an emote is played on a player.
 * <p>
 * Cancelling this event prevents the emote from playing.
 *
 * @param <P> the player type
 */
public class EmotePlayEvent<P> extends AnimorphEvent<P> implements Cancellable {

    public EmotePlayEvent(P player, String emoteId, String previousEmoteId, Set<String> layerIds, double startTick) {
        super(player);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the emote ID being played.
     *
     * @return the emote ID
     */
    public String getEmoteId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the emote ID the player currently has on this target (model or layer).
     *
     * @return the previous emote ID, or {@code "empty"} if none
     */
    public String getPreviousEmoteId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the layer IDs the emote targets.
     *
     * @return the layer IDs, or {@code null} if targeting the model level
     */
    public Set<String> getLayerIds() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the animation tick to start from.
     *
     * @return the start tick, or {@code -1} if starting from the beginning
     */
    public double getStartTick() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Whether this is a layer-level emote.
     *
     * @return {@code true} if targeting specific layers
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
