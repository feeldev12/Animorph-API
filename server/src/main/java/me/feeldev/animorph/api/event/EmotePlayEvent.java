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
    private final String emoteId;
    private final String previousEmoteId;
    private final Set<String> layerIds;
    private final double startTick;
    private boolean cancelled;

    public EmotePlayEvent(P player, String emoteId, String previousEmoteId, Set<String> layerIds, double startTick) {
        super(player);
        this.emoteId = emoteId;
        this.previousEmoteId = previousEmoteId;
        this.layerIds = layerIds;
        this.startTick = startTick;
    }

    /**
     * Gets the emote ID being played.
     *
     * @return the emote ID
     */
    public String getEmoteId() {
        return emoteId;
    }

    /**
     * Gets the emote ID the player currently has on this target (model or layer).
     *
     * @return the previous emote ID, or {@code "empty"} if none
     */
    public String getPreviousEmoteId() {
        return previousEmoteId;
    }

    /**
     * Gets the layer IDs the emote targets.
     *
     * @return the layer IDs, or {@code null} if targeting the model level
     */
    public Set<String> getLayerIds() {
        return layerIds;
    }

    /**
     * Gets the animation tick to start from.
     *
     * @return the start tick, or {@code -1} if starting from the beginning
     */
    public double getStartTick() {
        return startTick;
    }

    /**
     * Whether this is a layer-level emote.
     *
     * @return {@code true} if targeting specific layers
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
