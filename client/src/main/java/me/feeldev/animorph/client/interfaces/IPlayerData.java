package me.feeldev.animorph.client.interfaces;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import me.feeldev.animorph.client.api.NotImplementedException;
import net.minecraft.util.Arm;
import software.bernie.geckolib.animatable.GeoAnimatable;

/**
 * Provides access to player data within animation controllers.
 * <p>
 * This interface is the main type parameter for animation controllers and handlers.
 * It gives access to the player entity, model, and animation helpers.
 *
 * @see me.feeldev.animorph.client.api.AnimorphController
 */
public interface IPlayerData extends GeoAnimatable {

    /**
     * Returns the model currently applied to this player.
     *
     * @return the player's model, or {@code null} if no model is assigned
     */
    IPlayerModel animorph$getModel();

    /**
     * Returns the Minecraft player entity.
     *
     * @return the client player entity
     */
    AbstractClientPlayerEntity animorph$getPlayer();

    /**
     * Returns the player list entry for this player.
     *
     * @return the player list entry
     */
    PlayerListEntry animorph$getPlayerListEntry();

    /**
     * Gets the hand swing progress for a specific arm.
     *
     * @param partialTick the partial tick for interpolation
     * @param humanoidArm the arm to query ({@link Arm#LEFT} or {@link Arm#RIGHT})
     * @return the swing progress (0.0 to 1.0), or 0.0 if the arm is not swinging
     */
    default float getSwingProgress(float partialTick, Arm humanoidArm) {
        throw new NotImplementedException();
    }

    /**
     * Gets the limb movement speed, clamped to [0.0, 1.0].
     * <p>
     * Useful for detecting if the player is walking, sprinting, or standing still.
     *
     * @param speed the partial tick for interpolation
     * @return the limb speed (0.0 = standing, approaching 1.0 = sprinting)
     */
    default float getLimbSpeed(float speed) {
        throw new NotImplementedException();
    }
}
