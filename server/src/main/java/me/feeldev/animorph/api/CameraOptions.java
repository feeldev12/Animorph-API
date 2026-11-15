package me.feeldev.animorph.api;

/**
 * The animated camera the server forces on a player (see {@link IMorphAPI#setCamera(Object, CameraOptions)}).
 * <p>
 * A model's cameras are bones: {@code camera} is the camera {@value #DEFAULT}, and {@code camera_<id>} the
 * camera {@code <id>} ({@code camera_cine} is {@code cine}). Every value left unset falls back to what the
 * model's config says for that camera ({@code properties.cameras}), or to the defaults.
 *
 * <pre>{@code
 * api.setCamera(player, "cine");                       // the model's own settings for "cine"
 * api.setCamera(player, CameraOptions.builder("cine")
 *         .aim(CameraAim.LOCKED)                       // no crosshair while it lasts
 *         .blendTicks(10)                              // half a second to get there
 *         .lockPerspective(true)                       // F5 does nothing until released
 *         .build());
 * api.releaseCamera(player);                           // back to the animation / default camera
 * }</pre>
 *
 * @param id              the camera: {@value #DEFAULT} or an id from a {@code camera_<id>} bone
 * @param aim             where the crosshair comes from; {@code null} = the model's
 * @param blendTicks      ticks to move smoothly to this camera (0 = cut), at most
 *                        {@value #MAX_BLEND_TICKS}; {@code -1} = the model's
 * @param lockPerspective keep the player in first person (F5 does nothing) while this camera is forced
 * @param showBody        draw the player's whole body while this camera is active; {@code null} = the model's
 */
public record CameraOptions(String id, CameraAim aim, int blendTicks, boolean lockPerspective, Boolean showBody) {

    /**
     * The {@code camera} bone.
     */
    public static final String DEFAULT = "default";

    /**
     * Not a camera: in commands, lets go of the forced camera. Can't be used as an id.
     */
    public static final String RELEASE = "release";

    public static final int MAX_ID_LENGTH = 32;

    public static final int MAX_BLEND_TICKS = 1200;

    /**
     * {@link #blendTicks()} meaning "the model's".
     */
    public static final int MODEL_BLEND = -1;

    public CameraOptions {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * This camera with the model's settings.
     */
    public static CameraOptions of(String id) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public static Builder builder(String id) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * A builder starting from these options.
     */
    public Builder toBuilder() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Whether {@code id} can name a camera bone {@code camera_<id>}: lowercase letters, digits and {@code _},
     * 1 to {@value #MAX_ID_LENGTH} characters, and not {@value #DEFAULT} or {@value #RELEASE}.
     */
    public static boolean isValidId(String id) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Whether {@code id} can be forced: {@value #DEFAULT} or a {@linkplain #isValidId valid id}.
     */
    public static boolean isValidTarget(String id) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public static final class Builder {

        private Builder(String id) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        /**
         * Where the crosshair comes from; {@code null} = the model's.
         */
        public Builder aim(CameraAim aim) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        /**
         * Ticks to move smoothly to this camera (0 = cut); {@code -1} = the model's.
         */
        public Builder blendTicks(int blendTicks) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        /**
         * Keep the player in first person while this camera is forced.
         */
        public Builder lockPerspective(boolean lockPerspective) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        /**
         * Draw the player's whole body; {@code null} = the model's.
         */
        public Builder showBody(Boolean showBody) {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }

        public CameraOptions build() {
            throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
        }
    }
}
