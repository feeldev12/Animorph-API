package me.feeldev.animorph.client.api.event;

import me.feeldev.animorph.api.event.AnimorphEvent;
import java.util.UUID;

/**
 * Fired on the client after a player's model is updated or cleared.
 * <p>
 * This event is not cancellable — the model change has already been applied.
 * Use it to react to model changes (e.g. update custom UI, trigger effects).
 */
public class ClientModelUpdateEvent extends AnimorphEvent<UUID> {

    public ClientModelUpdateEvent(UUID playerId, String modelId, String previousModelId) {
        super(playerId);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the UUID of the player whose model changed.
     *
     * @return the player UUID
     */
    public UUID getPlayerId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the new model ID.
     *
     * @return the model ID, or {@code null} if the model was cleared
     */
    public String getModelId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the previous model ID.
     *
     * @return the previous model ID, or {@code null} if no model was set
     */
    public String getPreviousModelId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Whether the model was cleared (removed).
     *
     * @return {@code true} if the model was removed
     */
    public boolean isClearing() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
