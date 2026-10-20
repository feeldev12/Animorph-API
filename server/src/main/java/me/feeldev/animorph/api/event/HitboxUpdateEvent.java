package me.feeldev.animorph.api.event;

import me.feeldev.animorph.common.models.Hitboxes;

/**
 * Fired before a player's hitbox is updated.
 * <p>
 * Unlike most cosmetic overrides, this affects real server-side collision — cancelling this
 * event prevents the hitbox change.
 *
 * @param <P> the player type
 */
public class HitboxUpdateEvent<P> extends AnimorphEvent<P> implements Cancellable {

    public HitboxUpdateEvent(P player, Hitboxes hitboxes, Hitboxes previousHitboxes) {
        super(player);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the new hitboxes being applied.
     *
     * @return the new hitboxes
     */
    public Hitboxes getHitboxes() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the previous hitboxes.
     *
     * @return the previous hitboxes
     */
    public Hitboxes getPreviousHitboxes() {
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
