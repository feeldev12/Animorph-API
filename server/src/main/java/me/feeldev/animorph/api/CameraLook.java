package me.feeldev.animorph.api;

/**
 * Whether an animated camera turns with the player's look ({@code look} in {@code properties.cameras.<id>}).
 */
public enum CameraLook {

    /**
     * The camera turns with the player's look (the mouse), like the plain view: the bone's pose is applied on
     * top of where the player looks. The default for every camera.
     */
    FOLLOW(1),
    /**
     * The camera doesn't follow the mouse: its orientation comes only from the bone's animated pose, on top
     * of the yaw the player's body had when this camera became active (pitch 0). The shot is exactly the
     * one animated in Blockbench. The camera still moves with the player (it isn't anchored in the world),
     * and the player can keep turning underneath; only the view stays put. Meant for cutscenes, usually with
     * {@link CameraAim#LOCKED} or {@link CameraAim#EYES}.
     */
    FIXED(2);

    CameraLook(int id) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Its value on the network ({@code 0} is "the model's own", see {@link #byId}).
     */
    public int id() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The look for a network value; {@code null} for {@code 0} (the model's own) or an unknown value.
     */
    public static CameraLook byId(int id) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The look for its name in a model config or command ({@code follow}, {@code fixed}), or {@code null}.
     */
    public static CameraLook byName(String name) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
