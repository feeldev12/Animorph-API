package me.feeldev.animorph.api.event;

/**
 * Base class for all Animorph events.
 *
 * @param <P> the player type ({@code Player} on Bukkit, {@code ServerPlayerEntity} on Fabric)
 */
public abstract class AnimorphEvent<P> {
    private final P player;

    public AnimorphEvent(P player) {
        this.player = player;
    }

    /**
     * Gets the player involved in this event.
     *
     * @return the player
     */
    public P getPlayer() {
        return player;
    }
}
