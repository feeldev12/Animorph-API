package me.feeldev.animorph.client.api;

import me.feeldev.animorph.api.IFirstPersonProperty;
import me.feeldev.animorph.api.event.AnimorphEventBus;
import me.feeldev.animorph.client.interfaces.ICustomMolangQuery;
import me.feeldev.animorph.client.interfaces.IPlayerData;

import java.util.Optional;
import java.util.UUID;

/**
 * Client-side API for extending the Animorph system.
 * <p>
 * Provides methods to register custom animation controllers, Molang queries,
 * and query client-side player state.
 * <p>
 * This class is only available on the client (Fabric mod environment).
 *
 * <pre>{@code
 * // Register a custom controller
 * ClientMorphAPI.registerController("my_controller", new MyCustomController());
 *
 * // Register a custom Molang variable
 * ClientMorphAPI.registerMolangVariable("query.my_var");
 *
 * // Check player state
 * ClientMorphAPI.getPlayerModelId(playerUUID).ifPresent(modelId -> { ... });
 * }</pre>
 */
public final class ClientMorphAPI {

    private static ClientMorphAPIHandler handler;
    private static final AnimorphEventBus eventBus = new AnimorphEventBus();

    private ClientMorphAPI() {}

    /**
     * Gets the client-side event bus for registering event listeners.
     * <p>
     * Client events are post-events (not cancellable) that fire after the state
     * has been applied. Use them to react to model, emote, and layer changes.
     *
     * <pre>{@code
     * ClientMorphAPI.getEventBus().register(ClientModelUpdateEvent.class, event -> {
     *     System.out.println("Player " + event.getPlayerId() + " model changed to " + event.getModelId());
     * });
     * }</pre>
     *
     * @return the client event bus instance
     */
    public static AnimorphEventBus getEventBus() {
        return eventBus;
    }

    /**
     * Registers the client API handler implementation. Called internally by the Animorph client mod on startup.
     *
     * @param impl the handler implementation
     * @throws IllegalStateException if the handler has already been registered
     */
    public static void register(ClientMorphAPIHandler impl) {
        if (handler != null) {
            throw new IllegalStateException("ClientMorphAPI has already been registered.");
        }
        handler = impl;
    }

    /**
     * Checks if the client API is available.
     *
     * @return {@code true} if the handler has been registered and is ready to use
     */
    public static boolean isAvailable() {
        return handler != null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Controller API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Registers a custom animation controller.
     * <p>
     * Once registered, models can reference this controller by its ID
     * in their animation controller configuration. The controller will be
     * automatically loaded when a model that uses it is applied to a player.
     *
     * <pre>{@code
     * ClientMorphAPI.registerController("my_idle", new AnimorphController("my_idle", 5) {
     *     @Override
     *     protected AnimationController.AnimationStateHandler<IPlayerData>
     *     createAnimationStateHandler(IPlayerData entity) {
     *         return animationState -> {
     *             return animationState.setAndContinue(RawAnimation.begin().thenLoop("animation.my_idle"));
     *         };
     *     }
     * });
     * }</pre>
     *
     * @param id         the unique controller ID (referenced by models)
     * @param controller the controller instance
     * @throws IllegalStateException    if the API handler is not initialized
     * @throws IllegalArgumentException if a controller with the same ID is already registered
     */
    public static void registerController(String id, AnimorphController controller) {
        if (handler == null) throw new NotImplementedException();
        handler.registerController(id, controller);
    }

    /**
     * Checks if a native animation controller with the given ID is registered.
     *
     * @param id the controller ID to check
     * @return {@code true} if a controller with that ID exists
     */
    public static boolean hasController(String id) {
        if (handler == null) throw new NotImplementedException();
        return handler.hasController(id);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Molang API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Registers a custom Molang variable that can be used in Blockbench animator controllers.
     * <p>
     * The variable must be registered before it can be set via a query added with
     * {@link #addMolangQuery(ICustomMolangQuery)}.
     * Variable names should follow the {@code query.my_variable} convention.
     *
     * <pre>{@code
     * // Register once during mod initialization
     * ClientMorphAPI.registerMolangVariable("query.my_stamina");
     * }</pre>
     *
     * @param name the variable name (e.g. {@code "query.my_stamina"})
     */
    public static void registerMolangVariable(String name) {
        if (handler == null) throw new NotImplementedException();
        handler.registerMolangVariable(name);
    }

    /**
     * Registers a custom Molang query that is evaluated every animation tick.
     * <p>
     * Use this to set multiple Molang variables at once based on the current animation state
     * and player data. Queries are evaluated for every player with an active model.
     *
     * <pre>{@code
     * // Register the variable first
     * ClientMorphAPI.registerMolangVariable("query.my_stamina");
     *
     * // Then add a query that updates it each tick
     * ClientMorphAPI.addMolangQuery((animationState, animTime) -> {
     *     IPlayerData playerData = animationState.getAnimatable();
     *     AbstractClientPlayerEntity player = playerData.animorph$getPlayer();
     *     MathParser.setVariable("query.my_stamina", () -> getStamina(player));
     * });
     * }</pre>
     *
     * @param query the query to register
     */
    public static <T extends IPlayerData> void addMolangQuery(ICustomMolangQuery<T> query) {
        if (handler == null) throw new NotImplementedException();
        handler.addMolangQuery(query);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Player State API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets the model ID currently applied to a player on the client.
     *
     * @param playerId the UUID of the player
     * @return an {@link Optional} containing the model ID, or empty if the player has no model
     */
    public static Optional<String> getPlayerModelId(UUID playerId) {
        if (handler == null) throw new NotImplementedException();
        return handler.getPlayerModelId(playerId);
    }

    /**
     * Checks if a player currently has a custom model applied on the client.
     *
     * @param playerId the UUID of the player
     * @return {@code true} if the player has an active custom model
     */
    public static boolean hasModel(UUID playerId) {
        if (handler == null) throw new NotImplementedException();
        return handler.hasModel(playerId);
    }

    /**
     * Gets the emote ID currently playing on a player (model-level).
     *
     * @param playerId the UUID of the player
     * @return an {@link Optional} containing the emote ID, or empty if no emote is playing
     */
    public static Optional<String> getEmoteId(UUID playerId) {
        if (handler == null) throw new NotImplementedException();
        return handler.getEmoteId(playerId);
    }

    /**
     * Checks if a player is currently playing an emote on the client.
     *
     * @param playerId the UUID of the player
     * @return {@code true} if the player has an active emote
     */
    public static boolean isEmoting(UUID playerId) {
        if (handler == null) throw new NotImplementedException();
        return handler.isEmoting(playerId);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // First Person Property API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets the current first-person property for a player on the client.
     * <p>
     * Returns the effective first-person property that is currently applied,
     * including any server-side overrides.
     *
     * <pre>{@code
     * ClientMorphAPI.getFirstPersonProperty(playerUUID).ifPresent(fp -> {
     *     boolean showsModel = fp.showModel();
     *     boolean showsCustomArms = fp.customArms().show();
     * });
     * }</pre>
     *
     * @param playerId the UUID of the player
     * @return an {@link Optional} containing the first-person property, or empty if the player has no model
     */
    public static Optional<IFirstPersonProperty> getFirstPersonProperty(UUID playerId) {
        if (handler == null) throw new NotImplementedException();
        return handler.getFirstPersonProperty(playerId);
    }
}
