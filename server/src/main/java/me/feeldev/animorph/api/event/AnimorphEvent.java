package me.feeldev.animorph.api.event;

/**
 * Base class for all Animorph events.
 *
 * @param <P> the player type ({@code Player} on Bukkit, {@code ServerPlayerEntity} on Fabric)
 */
public abstract class AnimorphEvent<P> {

    public AnimorphEvent(P player) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the player involved in this event.
     *
     * @return the player
     */
    public P getPlayer() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
