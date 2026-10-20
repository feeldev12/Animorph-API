package me.feeldev.animorph.api.event;

import java.util.function.Consumer;

/**
 * A lightweight event bus for Animorph events.
 * <p>
 * Thread-safe. Listeners are invoked in registration order.
 *
 * <pre>{@code
 * AnimorphEventBus bus = api.getEventBus();
 * bus.register(ModelUpdateEvent.class, event -> {
 *     if (event.getModelId().equals("banned_model")) {
 *         event.setCancelled(true);
 *     }
 * });
 * }</pre>
 */
public class AnimorphEventBus {

    /**
     * Registers a listener for a specific event type.
     *
     * @param eventClass the event class to listen for
     * @param listener   the listener callback
     * @param <T>        the event type
     */
    public <T extends AnimorphEvent<?>> void register(Class<T> eventClass, Consumer<T> listener) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Unregisters a previously registered listener.
     *
     * @param eventClass the event class
     * @param listener   the listener to remove
     * @param <T>        the event type
     */
    public <T extends AnimorphEvent<?>> void unregister(Class<T> eventClass, Consumer<T> listener) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Fires an event, invoking all registered listeners.
     *
     * @param event the event to fire
     * @param <T>   the event type
     * @return the event (possibly modified by listeners)
     */
    @SuppressWarnings("unchecked")
    public <T extends AnimorphEvent<?>> T fire(T event) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
