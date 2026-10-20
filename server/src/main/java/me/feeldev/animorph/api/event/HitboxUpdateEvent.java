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
    private final Hitboxes hitboxes;
    private final Hitboxes previousHitboxes;
    private boolean cancelled;

    public HitboxUpdateEvent(P player, Hitboxes hitboxes, Hitboxes previousHitboxes) {
        super(player);
        this.hitboxes = hitboxes;
        this.previousHitboxes = previousHitboxes;
    }

    /**
     * Gets the new hitboxes being applied.
     *
     * @return the new hitboxes
     */
    public Hitboxes getHitboxes() {
        return hitboxes;
    }

    /**
     * Gets the previous hitboxes.
     *
     * @return the previous hitboxes
     */
    public Hitboxes getPreviousHitboxes() {
        return previousHitboxes;
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
