package me.feeldev.animorph.api.event;

/**
 * Fired before a model is applied to or cleared from a player.
 * <p>
 * Cancelling this event prevents the model change from occurring.
 *
 * @param <P> the player type
 */
public class ModelUpdateEvent<P> extends AnimorphEvent<P> implements Cancellable {

    public ModelUpdateEvent(P player, String modelId, String previousModelId) {
        super(player);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the model ID being applied.
     *
     * @return the new model ID, or {@code "empty"} if clearing
     */
    public String getModelId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the model ID the player currently has.
     *
     * @return the previous model ID, or {@code "empty"} if none
     */
    public String getPreviousModelId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Whether this event represents a model being cleared.
     *
     * @return {@code true} if the model is being removed
     */
    public boolean isClearing() {
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
