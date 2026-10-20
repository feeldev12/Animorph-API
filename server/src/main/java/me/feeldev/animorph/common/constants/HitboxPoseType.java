package me.feeldev.animorph.common.constants;

public enum HitboxPoseType {

    STANDING,
    SLEEPING,
    FALL_FLYING,
    SWIMMING,
    SPIN_ATTACK,
    CROUCHING,
    DYING;

    public static HitboxPoseType getByOrdinal(int ordinal) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
