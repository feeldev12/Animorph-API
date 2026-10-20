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

    public FirstPersonPropertyUpdateEvent(P player, IFirstPersonProperty property, IFirstPersonProperty previousProperty) {
        super(player);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the new first-person property being applied.
     *
     * @return the new property
     */
    public IFirstPersonProperty getProperty() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the previous first-person property.
     *
     * @return the previous property, or {@code null} if no override was set
     */
    public IFirstPersonProperty getPreviousProperty() {
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
