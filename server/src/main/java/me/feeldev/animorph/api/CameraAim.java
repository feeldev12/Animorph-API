package me.feeldev.animorph.api;

/**
 * Where the crosshair (what the player targets: block outline, breaking, placing, attacking) comes
 * from while an animated camera drives the view.
 */
public enum CameraAim {

    /**
     * Along the animated camera, so the target matches the crosshair the player sees.
     */
    CAMERA(1),
    /**
     * From the eyes, like vanilla, wherever the camera is (third-person style cameras).
     */
    EYES(2),
    /**
     * No crosshair and no interaction: the player can't break, place, attack or use items. The mouse
     * still turns the player. Meant for cutscenes.
     */
    LOCKED(3);

    CameraAim(int id) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Its value on the network ({@code 0} is "the model's own", see {@link #byId}).
     */
    public int id() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The aim for a network value; {@code null} for {@code 0} (the model's own) or an unknown value.
     */
    public static CameraAim byId(int id) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The aim for its name in a model config or command ({@code camera}, {@code eyes}, {@code locked}), or {@code null}.
     */
    public static CameraAim byName(String name) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
