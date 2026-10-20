package me.feeldev.animorph.client.api;

import org.joml.Vector3d;

/**
 * Immutable snapshot of a bone transform for a specific render context.
 * <p>
 * Values are copied at capture time so consumers can use them outside render
 * without depending on mutable {@code GeoBone} state.
 */
public record BoneTransformSnapshot(Vector3d worldPosition, Vector3d rotationRadians, Vector3d scale, boolean firstPerson, long frameId) {
}
