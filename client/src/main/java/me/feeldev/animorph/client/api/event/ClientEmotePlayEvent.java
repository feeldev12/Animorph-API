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

    public ClientEmotePlayEvent(UUID playerId, String emoteId, Set<String> layerIds, double startTick) {
        super(playerId);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the UUID of the player.
     *
     * @return the player UUID
     */
    public UUID getPlayerId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the emote ID being played.
     *
     * @return the emote ID, or {@code "empty"} if stopping
     */
    public String getEmoteId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the layer IDs the emote targets.
     *
     * @return the layer IDs, or {@code null} if model-level
     */
    public Set<String> getLayerIds() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the start tick of the animation.
     *
     * @return the start tick, or {@code -1} if from the beginning
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

    /**
     * Whether the emote is being stopped.
     *
     * @return {@code true} if the emote is being cleared
     */
    public boolean isClearing() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
