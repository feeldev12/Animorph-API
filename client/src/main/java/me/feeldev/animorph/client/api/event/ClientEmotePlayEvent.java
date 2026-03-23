package me.feeldev.animorph.client.api.event;

import me.feeldev.animorph.api.event.AnimorphEvent;

import java.util.Set;
import java.util.UUID;

/**
 * Fired on the client after an emote starts or stops on a player.
 * <p>
 * This event is not cancellable — the emote change has already been applied.
 * Use it to react to emote changes (e.g. play sounds, trigger particles).
 */
public class ClientEmotePlayEvent extends AnimorphEvent<UUID> {
    private final String emoteId;
    private final Set<String> layerIds;
    private final double startTick;

    public ClientEmotePlayEvent(UUID playerId, String emoteId, Set<String> layerIds, double startTick) {
        super(playerId);
        this.emoteId = emoteId;
        this.layerIds = layerIds;
        this.startTick = startTick;
    }

    /**
     * Gets the UUID of the player.
     *
     * @return the player UUID
     */
    public UUID getPlayerId() {
        return getPlayer();
    }

    /**
     * Gets the emote ID being played.
     *
     * @return the emote ID, or {@code "empty"} if stopping
     */
    public String getEmoteId() {
        return emoteId;
    }

    /**
     * Gets the layer IDs the emote targets.
     *
     * @return the layer IDs, or {@code null} if model-level
     */
    public Set<String> getLayerIds() {
        return layerIds;
    }

    /**
     * Gets the start tick of the animation.
     *
     * @return the start tick, or {@code -1} if from the beginning
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

    /**
     * Whether the emote is being stopped.
     *
     * @return {@code true} if the emote is being cleared
     */
    public boolean isClearing() {
        return "empty".equals(emoteId) || emoteId == null;
    }
}
