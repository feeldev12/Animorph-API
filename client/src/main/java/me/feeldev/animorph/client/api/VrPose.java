package me.feeldev.animorph.client.api;

import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;

/**
 * Where a VR headset puts a player's head and hands, for {@link ClientMorphAPI#setVrPose}.
 * <p>
 * Everything is in GeckoLib model pixels (16 per block, y up, front -Z), relative to the player's
 * feet and turned by the body's yaw. A VR mod converts its tracking space into this one (with the
 * player's calibration: avatar eye height in pixels over the real eye height).
 *
 * @param eye       the eyes
 * @param head      the head's rotation in model space
 * @param leftHand  the left controller's grip, or {@code null} when it is not tracked
 * @param rightHand the right controller's grip, or {@code null} when it is not tracked
 */
public record VrPose(Vector3fc eye, Quaternionfc head, @Nullable Vector3fc leftHand, @Nullable Vector3fc rightHand) {
}
