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
    private final String emoteId;
    private final Set<String> layerIds;
    private boolean cancelled;

    public EmoteStopEvent(P player, String emoteId, Set<String> layerIds) {
        super(player);
        this.emoteId = emoteId;
        this.layerIds = layerIds;
    }

    /**
     * Gets the emote ID that is being stopped.
     *
     * @return the emote ID
     */
    public String getEmoteId() {
        return emoteId;
    }

    /**
     * Gets the layer IDs the emote was playing on.
     *
     * @return the layer IDs, or {@code null} if it was a model-level emote
     */
    public Set<String> getLayerIds() {
        return layerIds;
    }

    /**
     * Whether this was a layer-level emote.
     *
     * @return {@code true} if the emote was targeting specific layers
     */
    public boolean isLayerEmote() {
        return layerIds != null && !layerIds.isEmpty();
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
