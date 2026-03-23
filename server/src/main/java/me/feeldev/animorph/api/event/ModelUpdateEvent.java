package me.feeldev.animorph.api.event;

/**
 * Fired before a model is applied to or cleared from a player.
 * <p>
 * Cancelling this event prevents the model change from occurring.
 *
 * @param <P> the player type
 */
public class ModelUpdateEvent<P> extends AnimorphEvent<P> implements Cancellable {
    private final String modelId;
    private final String previousModelId;
    private boolean cancelled;

    public ModelUpdateEvent(P player, String modelId, String previousModelId) {
        super(player);
        this.modelId = modelId;
        this.previousModelId = previousModelId;
    }

    /**
     * Gets the model ID being applied.
     *
     * @return the new model ID, or {@code "empty"} if clearing
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * Gets the model ID the player currently has.
     *
     * @return the previous model ID, or {@code "empty"} if none
     */
    public String getPreviousModelId() {
        return previousModelId;
    }

    /**
     * Whether this event represents a model being cleared.
     *
     * @return {@code true} if the model is being removed
     */
    public boolean isClearing() {
        return "empty".equals(modelId);
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
