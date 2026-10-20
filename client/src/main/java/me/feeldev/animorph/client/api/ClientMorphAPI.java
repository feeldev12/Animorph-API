package me.feeldev.animorph.client.api;

import me.feeldev.animorph.api.IFirstPersonProperty;
import me.feeldev.animorph.api.event.AnimorphEventBus;
import me.feeldev.animorph.client.interfaces.ICustomMolangQuery;
import me.feeldev.animorph.client.interfaces.IPlayerData;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
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

    private ClientMorphAPI() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

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
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Registers the internal handler. Called by Animorph client on initialization.
     *
     * @param handler the handler implementation
     */
    public static void register(ClientMorphAPIHandler handler) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Checks if the client API is available.
     *
     * @return {@code true} if the handler has been registered and is ready to use
     */
    public static boolean isAvailable() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Render Layer API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Registers a custom render layer that fires for every morphed player.
     * <p>
     * The layer is invoked after all built-in Animorph layers (armor, items, cape, elytra)
     * but before the frame is flushed. Use it to render additional geometry on top of the morph.
     * For reacting to the render state without adding geometry, prefer subscribing to
     * {@link me.feeldev.animorph.client.api.event.PlayerMorphPreRenderEvent} or
     * {@link me.feeldev.animorph.client.api.event.PlayerMorphPostRenderEvent} via {@link #getEventBus()}.
     *
     * @param layer the render layer to register globally
     */
    public static void registerRenderLayer(AnimorphRenderLayer layer) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Registers a custom render layer that fires only when a specific model is active.
     *
     * @param modelId the model ID to scope this layer to
     * @param layer   the render layer to register
     */
    public static void registerRenderLayer(String modelId, AnimorphRenderLayer layer) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
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
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Checks if a native animation controller with the given ID is registered.
     *
     * @param id the controller ID to check
     * @return {@code true} if a controller with that ID exists
     */
    public static boolean hasController(String id) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
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
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
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
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Animation Events API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Adds an animation event from code, at a tick of an animation, without a keyframe in the animation file. It
     * fires exactly like an Animorph action keyframe placed with the Blockbench plugin: listeners of
     * {@link me.feeldev.animorph.client.api.event.ClientAnimationKeyframeEvent} receive it, and with
     * {@code sendToServer} the local player's crossings are also forwarded to the server.
     *
     * <pre>{@code
     * // "release" six ticks into kart.throw_forward, also sent to the server
     * ClientMorphAPI.addAnimationEvent("kart.throw_forward", 6, "release", true);
     * }</pre>
     *
     * @param animation    the animation name, as in the animation file
     * @param tick         the animation tick (>= 0)
     * @param event        the event name: lowercase letters, digits, {@code _ - .}, at most 64 characters
     * @param sendToServer also forward the local player's crossings to the server
     */
    public static void addAnimationEvent(String animation, double tick, String event, boolean sendToServer) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
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
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Checks if a player currently has a custom model applied on the client.
     *
     * @param playerId the UUID of the player
     * @return {@code true} if the player has an active custom model
     */
    public static boolean hasModel(UUID playerId) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the emote ID currently playing on a player (model-level).
     *
     * @param playerId the UUID of the player
     * @return an {@link Optional} containing the emote ID, or empty if no emote is playing
     */
    public static Optional<String> getEmoteId(UUID playerId) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Checks if a player is currently playing an emote on the client.
     *
     * @param playerId the UUID of the player
     * @return {@code true} if the player has an active emote
     */
    public static boolean isEmoting(UUID playerId) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
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
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets a model bone from a player's current morph model.
     * <p>
     * This is useful for gameplay integrations that need to read a specific
     * bone transform (for example, emitting particles or raycasts from an anchor bone).
     *
     * @param playerId the UUID of the player
     * @param boneName the model bone name
     * @return an {@link Optional} containing the bone if available
     */
    public static Optional<GeoBone> getPlayerModelBone(UUID playerId, String boneName) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public static Optional<GeoBone> getPlayerModelBone(UUID playerId, String boneName, boolean firstPerson) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets a bone from a player's active model-layer.
     * <p>
     * Only model layers are supported. Texture-only layers do not contain bones.
     *
     * @param playerId the UUID of the player
     * @param layerId  the layer ID
     * @param boneName the layer model bone name
     * @return an {@link Optional} containing the bone if available
     */
    public static Optional<GeoBone> getPlayerLayerBone(UUID playerId, String layerId, String boneName) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public static Optional<GeoBone> getPlayerLayerBone(UUID playerId, String layerId, String boneName, boolean firstPerson) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets an immutable layer-bone transform snapshot for first/third person context.
     */
    public static Optional<BoneTransformSnapshot> getPlayerLayerBoneSnapshot(UUID playerId, String layerId, String boneName, boolean firstPerson) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Ragdoll API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Triggers procedural ragdoll physics for a morphed player.
     * <p>
     * The impulse vector controls how the body reacts: its direction maps to
     * the body tilt and its magnitude controls rotation intensity. Pass
     * {@code Vec3.ZERO} or {@code null} for a neutral collapse with no
     * directional bias (e.g. standing-still death).
     * <p>
     * Calling this while already ragdolled restarts the effect with the new impulse.
     * Calling this on a player with no active morph is a no-op — the state is stored
     * but never applied since {@code PlayerMorphModel.handleAnimations} won't run.
     *
     * @param playerId the UUID of the target player
     * @param impulse  direction + magnitude of the triggering force, or {@code null}
     */
    public static void startRagdoll(UUID playerId, @Nullable Vec3 impulse) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Immediately cancels an active ragdoll, returning the model to its
     * normal animation state on the next render frame.
     *
     * @param playerId the UUID of the player to release
     */
    public static void stopRagdoll(UUID playerId) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Returns {@code true} if the player's morph is currently in ragdoll mode.
     *
     * @param playerId the UUID of the player to check
     * @return {@code true} if a ragdoll is active and has not yet expired
     */
    public static boolean isRagdolled(UUID playerId) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
