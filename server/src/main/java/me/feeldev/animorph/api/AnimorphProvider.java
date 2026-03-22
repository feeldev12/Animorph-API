package me.feeldev.animorph.api;

/**
 * Static accessor for the Animorph API instance.
 * <p>
 * The API is registered by the Animorph plugin/mod on startup.
 * Other plugins/mods should use this class to obtain the API.
 *
 * <pre>{@code
 * if (AnimorphProvider.isAvailable()) {
 *     IMorphAPI<Player> api = AnimorphProvider.getApi();
 *     api.updateModel(player, "dragon", false);
 * }
 * }</pre>
 */
public final class AnimorphProvider {

    private AnimorphProvider() {}

    /**
     * Returns the registered Animorph API instance.
     *
     * @param <P> the player type ({@code Player} on Bukkit, {@code ServerPlayerEntity} on Fabric)
     * @return the API instance
     * @throws IllegalStateException if the API has not been registered yet (Animorph plugin not loaded)
     */
    @SuppressWarnings("unchecked")
    public static <P> IMorphAPI<P> getApi() {
        throw new NotImplementedException();
    }

    /**
     * Registers the API implementation. Called internally by the Animorph plugin/mod on startup.
     *
     * @param <P> the player type
     * @param api the API implementation to register
     */
    public static <P> void register(IMorphAPI<P> api) {
        throw new NotImplementedException();
    }

    /**
     * Checks if the Animorph API is available.
     *
     * @return {@code true} if the API has been registered and is ready to use
     */
    public static boolean isAvailable() {
        throw new NotImplementedException();
    }
}
