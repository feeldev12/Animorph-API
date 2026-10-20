package me.feeldev.animorph.client.api.event;

import me.feeldev.animorph.api.event.AnimorphEvent;
import java.util.Set;
import java.util.UUID;

/**
 * Fired on the client when an emote stops playing on a player.
 * <p>
 * This event is not cancellable — the emote has already stopped.
 * Use it to react to emote endings (e.g. clean up effects, update UI).
 */
public class ClientEmoteStopEvent extends AnimorphEvent<UUID> {

    /**
     * The reason the emote stopped.
     */
    public enum Reason {

        /**
         * The server explicitly stopped the emote (clearEmote / "empty" message).
         */
        SERVER,
        /**
         * The animation finished playing naturally on the client.
         */
        FINISHED
    }

    public ClientEmoteStopEvent(UUID playerId, String emoteId, Set<String> layerIds, Reason reason) {
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
     * Gets the emote ID that stopped.
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
     * Gets the reason the emote stopped.
     *
     * @return {@link Reason#SERVER} if the server stopped it,
     *         {@link Reason#FINISHED} if the animation ended naturally
     */
    public Reason getReason() {
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
}
