package me.feeldev.animorph.common.constants;

/**
 * The player pose a hitbox override applies to, mirroring a model yml's {@code hitboxes:} block.
 */
public enum HitboxPoseType {
    STANDING,
    SLEEPING,
    FALL_FLYING,
    SWIMMING,
    SPIN_ATTACK,
    CROUCHING,
    DYING;

    public static HitboxPoseType getByOrdinal(int ordinal) {
        return values()[ordinal];
    }
}
