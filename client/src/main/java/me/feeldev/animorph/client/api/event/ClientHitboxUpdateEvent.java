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
    private final Hitboxes hitboxes;

    public ClientHitboxUpdateEvent(UUID playerId, Hitboxes hitboxes) {
        super(playerId);
        this.hitboxes = hitboxes;
    }

    /**
     * Gets the UUID of the player whose hitbox changed.
     *
     * @return the player UUID
     */
    public UUID getPlayerId() {
        return getPlayer();
    }

    /**
     * Gets the new hitboxes.
     *
     * @return the updated hitboxes
     */
    public Hitboxes getHitboxes() {
        return hitboxes;
    }
}
