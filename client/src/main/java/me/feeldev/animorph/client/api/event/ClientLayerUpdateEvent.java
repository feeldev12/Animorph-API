package me.feeldev.animorph.client.api.event;

import me.feeldev.animorph.api.event.AnimorphEvent;
import me.feeldev.animorph.api.LayerType;

import java.util.UUID;

/**
 * Fired on the client after a layer's visibility is toggled on a player.
 * <p>
 * This event is not cancellable — the layer change has already been applied.
 * Use it to react to layer changes (e.g. update custom overlays).
 */
public class ClientLayerUpdateEvent extends AnimorphEvent<UUID> {
    private final String layerId;
    private final LayerType layerType;
    private final boolean state;

    public ClientLayerUpdateEvent(UUID playerId, String layerId, LayerType layerType, boolean state) {
        super(playerId);
        this.layerId = layerId;
        this.layerType = layerType;
        this.state = state;
    }

    /**
     * Gets the UUID of the player.
     *
     * @return the player UUID
     */
    public UUID getPlayerId() {
        return getPlayer();
    }

    /**
     * Gets the layer ID that was toggled.
     *
     * @return the layer ID
     */
    public String getLayerId() {
        return layerId;
    }

    /**
     * Gets the type of layer (MODEL or TEXTURE).
     *
     * @return the layer type
     */
    public LayerType getLayerType() {
        return layerType;
    }

    /**
     * Gets the new visibility state.
     *
     * @return {@code true} if the layer is now visible
     */
    public boolean getState() {
        return state;
    }
}
