package me.feeldev.animorph.api.event;

import me.feeldev.animorph.api.IFirstPersonProperty;

/**
 * Fired before a player's first-person property is updated.
 * <p>
 * Cancelling this event prevents the property change.
 *
 * @param <P> the player type
 */
public class FirstPersonPropertyUpdateEvent<P> extends AnimorphEvent<P> implements Cancellable {
    private final IFirstPersonProperty property;
    private final IFirstPersonProperty previousProperty;
    private boolean cancelled;

    public FirstPersonPropertyUpdateEvent(P player, IFirstPersonProperty property, IFirstPersonProperty previousProperty) {
        super(player);
        this.property = property;
        this.previousProperty = previousProperty;
    }

    /**
     * Gets the new first-person property being applied.
     *
     * @return the new property
     */
    public IFirstPersonProperty getProperty() {
        return property;
    }

    /**
     * Gets the previous first-person property.
     *
     * @return the previous property, or {@code null} if no override was set
     */
    public IFirstPersonProperty getPreviousProperty() {
        return previousProperty;
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
