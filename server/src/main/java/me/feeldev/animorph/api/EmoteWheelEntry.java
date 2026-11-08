package me.feeldev.animorph.api;

/**
 * One sector of a player's emote wheel (see {@link IMorphAPI#setEmoteWheel}).
 *
 * @param emoteId a registered emote ({@code "<namespace>:<name>"})
 * @param name    what the wheel shows for it
 */
public record EmoteWheelEntry(String emoteId, String name) {

    /**
     * The most sectors a wheel shows; more are dropped.
     */
    public static final int MAX_ENTRIES = 8;

    public EmoteWheelEntry {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
