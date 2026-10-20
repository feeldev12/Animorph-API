package me.feeldev.animorph.client.api.event;

import me.feeldev.animorph.api.IFirstPersonProperty;
import me.feeldev.animorph.api.event.AnimorphEvent;
import java.util.UUID;

/**
 * Fired on the client after a player's first-person property is updated.
 * <p>
 * This event is not cancellable — the change has already been applied.
 */
public class ClientFirstPersonPropertyUpdateEvent extends AnimorphEvent<UUID> {

    public ClientFirstPersonPropertyUpdateEvent(UUID playerId, IFirstPersonProperty property) {
        super(playerId);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the UUID of the player whose first-person property changed.
     *
     * @return the player UUID
     */
    public UUID getPlayerId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the new first-person property.
     *
     * @return the updated first-person property
     */
    public IFirstPersonProperty getProperty() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
