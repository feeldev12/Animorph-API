package me.feeldev.animorph.api.event;

/**
 * Fired after models and emotes have been reloaded from disk.
 * <p>
 * This event is not cancellable — the reload has already completed.
 * Use it to invalidate caches or refresh data that depends on registered models/emotes.
 */
public class AnimorphReloadEvent extends AnimorphEvent<Void> {

    public AnimorphReloadEvent() {
        super(null);
    }
}
