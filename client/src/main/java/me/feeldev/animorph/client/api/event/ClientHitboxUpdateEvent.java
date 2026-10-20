package me.feeldev.animorph.client.api.event;

import me.feeldev.animorph.api.event.AnimorphEvent;
import me.feeldev.animorph.common.models.Hitboxes;
import java.util.UUID;

/**
 * Fired on the client after a player's hitbox is updated.
 * <p>
 * This event is not cancellable — the change has already been applied.
 */
public class ClientHitboxUpdateEvent extends AnimorphEvent<UUID> {

    public ClientHitboxUpdateEvent(UUID playerId, Hitboxes hitboxes) {
        super(playerId);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the UUID of the player whose hitbox changed.
     *
     * @return the player UUID
     */
    public UUID getPlayerId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the new hitboxes.
     *
     * @return the updated hitboxes
     */
    public Hitboxes getHitboxes() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
