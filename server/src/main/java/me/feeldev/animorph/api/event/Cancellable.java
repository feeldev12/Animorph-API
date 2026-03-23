package me.feeldev.animorph.api.event;

/**
 * Interface for events that can be cancelled.
 * <p>
 * When an event is cancelled, the action that triggered it will not be executed.
 */
public interface Cancellable {

    /**
     * Gets whether this event is cancelled.
     *
     * @return {@code true} if cancelled
     */
    boolean isCancelled();

    /**
     * Sets the cancellation state of this event.
     *
     * @param cancelled {@code true} to cancel, {@code false} to un-cancel
     */
    void setCancelled(boolean cancelled);
}
