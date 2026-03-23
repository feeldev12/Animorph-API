package me.feeldev.animorph.api.event;

/**
 * Fired before a layer's visibility is toggled on a player.
 * <p>
 * Cancelling this event prevents the layer state change.
 *
 * @param <P> the player type
 */
public class LayerUpdateEvent<P> extends AnimorphEvent<P> implements Cancellable {
    private final String layerId;
    private final boolean state;
    private final boolean previousState;
    private boolean cancelled;

    public LayerUpdateEvent(P player, String layerId, boolean state, boolean previousState) {
        super(player);
        this.layerId = layerId;
        this.state = state;
        this.previousState = previousState;
    }

    /**
     * Gets the layer ID being toggled.
     *
     * @return the layer ID
     */
    public String getLayerId() {
        return layerId;
    }

    /**
     * Gets the new visibility state.
     *
     * @return {@code true} if showing, {@code false} if hiding
     */
    public boolean getState() {
        return state;
    }

    /**
     * Gets the previous visibility state.
     *
     * @return the previous state
     */
    public boolean getPreviousState() {
        return previousState;
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
