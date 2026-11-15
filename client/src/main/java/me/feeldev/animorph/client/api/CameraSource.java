package me.feeldev.animorph.client.api;

/**
 * Where the local player's animated camera comes from (see {@link ClientMorphAPI#getActiveCamera()}), in
 * priority order.
 */
public enum CameraSource {

    /**
     * The player turned camera animations off (accessibility): the view stays at the eyes.
     */
    ACCESSIBILITY,
    /**
     * The server forces it ({@code /animorph camera}, {@code IMorphAPI#setCamera}).
     */
    SERVER,
    /**
     * A camera cut ({@code animorph:camera:<id>}) of the animation playing.
     */
    TIMELINE,
    /**
     * The model's {@code camera} bone.
     */
    DEFAULT,
    /**
     * No animated camera (the model has no camera bone): the vanilla view.
     */
    NONE
}
