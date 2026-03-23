package me.feeldev.animorph.api;

import me.feeldev.animorph.api.event.AnimorphEventBus;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Main API interface for interacting with the Animorph system.
 * <p>
 * Provides methods to manage custom models, emotes, layers, and text placeholders
 * on players. Works on both Bukkit and Fabric platforms.
 * <p>
 * Obtain an instance via {@link AnimorphProvider#getApi()}.
 *
 * <pre>{@code
 * IMorphAPI<Player> api = AnimorphProvider.getApi();
 * api.updateModel(player, "dragon", false);
 * api.playEmote(player, "wave", null);
 * }</pre>
 *
 * @param <P> the player type ({@code Player} on Bukkit, {@code ServerPlayerEntity} on Fabric)
 */
public interface IMorphAPI<P> {

    /**
     * Gets the event bus for registering event listeners.
     * <p>
     * Use this to listen for model updates, emote plays, and layer changes.
     *
     * <pre>{@code
     * api.getEventBus().register(ModelUpdateEvent.class, event -> {
     *     System.out.println(event.getPlayer() + " is changing model to " + event.getModelId());
     * });
     * }</pre>
     *
     * @return the event bus instance
     */
    AnimorphEventBus getEventBus();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Text API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Updates a text placeholder value on a player's model.
     * <p>
     * Text placeholders are dynamic text values rendered on model bones
     * (e.g. nametags, labels, counters).
     * If no viewers are specified, the update is sent to the player and all tracking players.
     *
     * @param player      the player whose placeholder to update
     * @param placeholder the placeholder key (as defined in the model)
     * @param value       the new value to display
     * @param viewers     optional specific viewers to send the update to;
     *                    if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void updateTextPlaceholder(P player, String placeholder, String value, P... viewers);

    /**
     * Re-sends all current text placeholder values for a player.
     * <p>
     * Useful for syncing placeholders to newly tracking players or after a reconnect.
     *
     * @param player  the player whose placeholders to resend
     * @param viewers optional specific viewers to send the update to;
     *                if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void updateTextPlaceholder(P player, P... viewers);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Model API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Removes the custom model from a player, restoring the default appearance.
     *
     * @param player  the player to clear the model from
     * @param viewers optional specific viewers to send the update to;
     *                if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void clearModel(P player, P... viewers);

    /**
     * Re-sends the player's currently assigned model.
     * <p>
     * Useful for syncing the model to newly tracking players.
     * Equivalent to calling {@link #updateModelPersistent(Object, boolean, Object[]) updateModelPersistent(player, false, viewers)}.
     *
     * @param player  the player whose model to resend
     * @param viewers optional specific viewers to send the update to;
     *                if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void updateModelPersistent(P player, P... viewers);

    /**
     * Re-sends the player's currently assigned model.
     * <p>
     * Useful for syncing the model to newly tracking players.
     *
     * @param player  the player whose model to resend
     * @param force   if {@code true}, forces the model data to be re-sent even if the client already has it cached
     * @param viewers optional specific viewers to send the update to;
     *                if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void updateModelPersistent(P player, boolean force, P... viewers);

    /**
     * Sets a new model on a player by its ID.
     * <p>
     * The model must be registered in the server's models directory.
     * Use {@code "empty"} as modelId to clear the model.
     *
     * @param player  the player to apply the model to
     * @param modelId the ID of the model to apply (e.g. {@code "dragon"}, {@code "fairy"})
     * @param force   if {@code true}, forces the model data to be re-sent even if the client already has it cached
     * @param viewers optional specific viewers to send the update to;
     *                if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void updateModel(P player, String modelId, boolean force, P... viewers);

    /**
     * Gets the model data currently assigned to a player.
     *
     * @param player the player to query
     * @return an {@link Optional} containing the model data, or empty if the player has no model
     */
    Optional<? extends IModelData> getModel(P player);

    /**
     * Gets a registered model by its ID.
     *
     * @param modelId the model ID to look up
     * @return an {@link Optional} containing the model data, or empty if not found
     */
    Optional<? extends IModelData> getModel(String modelId);

    /**
     * Returns all registered models.
     *
     * @return an unmodifiable map of model ID to model data
     */
    Map<String, ? extends IModelData> registeredModels();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Animation API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets a registered emote/animation by its ID.
     *
     * @param animationId the animation ID to look up
     * @return an {@link Optional} containing the emote data, or empty if not found
     */
    Optional<? extends IEmoteData> getAnimation(String animationId);

    /**
     * Returns all registered emotes/animations.
     *
     * @return an unmodifiable map of animation ID to emote data
     */
    Map<String, ? extends IEmoteData> registeredAnimations();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Emote API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Stops any currently playing emote on a player (model and all layers).
     *
     * @param player  the player to stop the emote on
     * @param viewers optional specific viewers to send the update to;
     *                if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void clearEmote(P player, P... viewers);

    /**
     * Stops currently playing emotes on specific layers of a player.
     *
     * @param player   the player to stop the emote on
     * @param layerIds the set of layer IDs to clear; if {@code null}, clears the model emote
     * @param viewers  optional specific viewers to send the update to;
     *                 if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void clearEmote(P player, Set<String> layerIds, P... viewers);

    /**
     * Re-sends the player's currently playing emote (model-level).
     * <p>
     * Useful for syncing emotes to newly tracking players.
     *
     * @param player  the player whose emote to resend
     * @param viewers optional specific viewers to send the update to;
     *                if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void playEmote(P player, P... viewers);

    /**
     * Re-sends the player's currently playing emote for the given emote type.
     * <p>
     * Useful for syncing emotes to newly tracking players.
     *
     * @param player    the player whose emote to resend
     * @param emoteType the emote scope to resend ({@link EmoteType#MODEL} or {@link EmoteType#LAYER})
     * @param viewers   optional specific viewers to send the update to;
     *                  if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void playEmote(P player, EmoteType emoteType, P... viewers);

    /**
     * Plays an emote on a player by its ID.
     *
     * @param player   the player to play the emote on
     * @param emoteId  the emote/animation ID to play (e.g. {@code "wave"}, {@code "dance"})
     * @param layerIds the set of layer IDs to play the emote on;
     *                 if {@code null} or empty, plays on the model level
     * @param viewers  optional specific viewers to send the update to;
     *                 if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void playEmote(P player, String emoteId, Set<String> layerIds, P... viewers);

    /**
     * Plays an emote on a player by its ID, starting at a specific tick.
     * <p>
     * Specifying a start tick allows syncing the emote playback position
     * across multiple players.
     *
     * @param player    the player to play the emote on
     * @param emoteId   the emote/animation ID to play
     * @param startTick the animation tick to start from; use {@code -1} to start from the beginning
     * @param layerIds  the set of layer IDs to play the emote on;
     *                  if {@code null} or empty, plays on the model level
     * @param viewers   optional specific viewers to send the update to;
     *                  if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void playEmote(P player, String emoteId, double startTick, Set<String> layerIds, P... viewers);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Layer API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Re-sends all current layer states for a player.
     * <p>
     * Useful for syncing layer visibility to newly tracking players.
     *
     * @param player  the player whose layers to resend
     * @param viewers optional specific viewers to send the update to;
     *                if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void updateLayers(P player, P... viewers);

    /**
     * Shows or hides a layer on a player's current model.
     *
     * @param player  the player to modify the layer on
     * @param layerId the ID of the layer to toggle
     * @param state   {@code true} to show the layer, {@code false} to hide it
     * @param viewers optional specific viewers to send the update to;
     *                if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void applyLayer(P player, String layerId, boolean state, P... viewers);

    /**
     * Shows or hides a layer on a specific model assigned to a player.
     * <p>
     * Use this overload when you need to target a layer on a specific model
     * (e.g. if the player could have a different model than expected).
     *
     * @param player  the player to modify the layer on
     * @param modelId the model ID that contains the layer
     * @param layerId the ID of the layer to toggle
     * @param state   {@code true} to show the layer, {@code false} to hide it
     * @param viewers optional specific viewers to send the update to;
     *                if empty, broadcasts to the player and all trackers
     */
    @SuppressWarnings("unchecked")
    void applyLayer(P player, String modelId, String layerId, boolean state, P... viewers);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Player State API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets the model ID currently assigned to a player.
     *
     * @param player the player to query
     * @return the model ID (e.g. {@code "dragon"}), or {@code "empty"} if no model is assigned
     */
    String getModelId(P player);

    /**
     * Checks if a player has a custom model applied.
     *
     * @param player the player to check
     * @return {@code true} if the player has a model other than {@code "empty"}
     */
    boolean hasModel(P player);

    /**
     * Gets the emote ID currently playing on a player (model-level).
     *
     * @param player the player to query
     * @return the emote ID, or {@code "empty"} if no emote is playing
     */
    String getEmoteId(P player);

    /**
     * Checks if a player is currently playing an emote (model-level).
     *
     * @param player the player to check
     * @return {@code true} if the player has an active emote
     */
    boolean isEmoting(P player);

    /**
     * Gets the emote ID currently playing on a specific layer of a player.
     *
     * @param player  the player to query
     * @param layerId the layer ID to query
     * @return the emote ID playing on the layer, or {@code "empty"} if none
     */
    String getLayerEmoteId(P player, String layerId);

    /**
     * Checks if a specific layer is currently visible on a player.
     *
     * @param player  the player to check
     * @param layerId the layer ID to query
     * @return {@code true} if the layer is active/visible, {@code false} if hidden or not found
     */
    boolean isLayerActive(P player, String layerId);

    /**
     * Gets all layer states for a player.
     * <p>
     * The returned map contains layer IDs as keys and their visibility state as values.
     *
     * @param player the player to query
     * @return an unmodifiable map of layer ID to visibility state; empty map if no layers exist
     */
    Map<String, Boolean> getLayerStates(P player);

    /**
     * Gets all text placeholder values for a player.
     * <p>
     * The returned map contains placeholder keys and their current values.
     *
     * @param player the player to query
     * @return an unmodifiable map of placeholder key to value; empty map if no placeholders are set
     */
    Map<String, String> getTextPlaceholders(P player);
}
