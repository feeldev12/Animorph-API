package me.feeldev.animorph.client.api;

import org.joml.Vector3d;

/**
 * Immutable snapshot of a bone transform for a specific render context.
 */
public record BoneTransformSnapshot(
        Vector3d worldPosition,
        Vector3d rotationRadians,
        Vector3d scale,
        boolean firstPerson,
        long frameId
) {
}
