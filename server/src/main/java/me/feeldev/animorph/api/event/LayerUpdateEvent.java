package me.feeldev.animorph.api.event;

/**
 * Fired before a layer's visibility is toggled on a player.
 * <p>
 * Cancelling this event prevents the layer state change.
 *
 * @param <P> the player type
 */
public class LayerUpdateEvent<P> extends AnimorphEvent<P> implements Cancellable {

    public LayerUpdateEvent(P player, String layerId, boolean state, boolean previousState) {
        super(player);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the layer ID being toggled.
     *
     * @return the layer ID
     */
    public String getLayerId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the new visibility state.
     *
     * @return {@code true} if showing, {@code false} if hiding
     */
    public boolean getState() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the previous visibility state.
     *
     * @return the previous state
     */
    public boolean getPreviousState() {
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
