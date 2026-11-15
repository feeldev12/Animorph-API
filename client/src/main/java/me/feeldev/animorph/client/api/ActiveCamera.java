package me.feeldev.animorph.client.api;

import me.feeldev.animorph.api.CameraAim;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

/**
 * The animated camera driving the local player's view this frame (see {@link ClientMorphAPI#getActiveCamera()}).
 *
 * @param id                the camera ({@code default} = the {@code camera} bone, or a {@code camera_<id>} id);
 *                          {@code null} for the vanilla view
 * @param requestedId       the camera that was asked for (by the server or the animation); it differs from
 *                          {@code id} when the model doesn't have it and {@code default} is used instead
 * @param source            where it comes from
 * @param aim               where the crosshair comes from
 * @param showBody          whether the whole body is drawn
 * @param perspectiveLocked whether F5 is locked to first person by the server
 */
public record ActiveCamera(@Nullable String id, @Nullable String requestedId, CameraSource source, CameraAim aim, boolean showBody, boolean perspectiveLocked) {

    /**
     * No animated camera.
     */
    public static final ActiveCamera NONE = null;

    /**
     * The camera, empty for the vanilla view.
     */
    public Optional<String> camera() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Whether an animated camera drives the view.
     */
    public boolean isAnimated() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
