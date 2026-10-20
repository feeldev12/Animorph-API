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

    public ClientLayerUpdateEvent(UUID playerId, String layerId, LayerType layerType, boolean state) {
        super(playerId);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the UUID of the player.
     *
     * @return the player UUID
     */
    public UUID getPlayerId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the layer ID that was toggled.
     *
     * @return the layer ID
     */
    public String getLayerId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the type of layer (MODEL or TEXTURE).
     *
     * @return the layer type
     */
    public LayerType getLayerType() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Gets the new visibility state.
     *
     * @return {@code true} if the layer is now visible
     */
    public boolean getState() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
