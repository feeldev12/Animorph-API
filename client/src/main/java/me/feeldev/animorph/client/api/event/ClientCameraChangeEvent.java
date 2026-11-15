package me.feeldev.animorph.client.api.event;

import me.feeldev.animorph.api.event.AnimorphEvent;
import me.feeldev.animorph.client.api.ActiveCamera;
import java.util.UUID;

/**
 * Fired on the client when the camera driving the local player's view changes: another camera, another
 * source (server, animation cut, default, accessibility), or a change of its aim or body setting. Fired on
 * the render thread, before the frame that uses the new camera is drawn.
 * <p>
 * Not cancellable: it reports what the player sees.
 */
public class ClientCameraChangeEvent extends AnimorphEvent<UUID> {

    public ClientCameraChangeEvent(UUID playerId, ActiveCamera previous, ActiveCamera current) {
        super(playerId);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The local player.
     */
    public UUID getPlayerId() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The camera before the change.
     */
    public ActiveCamera getPrevious() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The camera now.
     */
    public ActiveCamera getCurrent() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
