package me.feeldev.animorph.client.api;

import com.mojang.blaze3d.vertex.PoseStack;
import me.feeldev.animorph.client.interfaces.IPlayerData;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * Extension interface for Fabric's {@code ArmorRenderer} that enables correct rendering
 * on Animorph morphs. Implement this alongside your existing {@code ArmorRenderer} to
 * receive the active GeckoLib morph model instead of a vanilla {@code BipedEntityModel}.
 *
 * <p>Registration works exactly like Fabric's API — no separate registry needed:
 * <pre>{@code
 * public class MyArmorRenderer implements ArmorRenderer, AnimorphArmorRenderer {
 *
 *     @Override
 *     public void render(..., BipedEntityModel<LivingEntity> contextModel) {
 *         // vanilla / fabric fallback rendering
 *     }
 *
 *     @Override
 *     public void renderOnMorph(..., IPlayerData morphContext) {
 *         // use morphContext.animorph$getModel() to sync bone transforms
 *     }
 * }
 * }</pre>
 *
 * <p>When the player is morphed, Animorph calls {@link #renderOnMorph} instead of
 * {@code ArmorRenderer#render}.
 * If the registered renderer does not implement this interface, Animorph falls back
 * to the standard Fabric render call with a best-effort context model.
 */
@FunctionalInterface
public interface AnimorphArmorRenderer {

    /**
     * Renders armor on a morphed player.
     *
     * @param matrices      the current matrix stack (positioned at the entity root)
     * @param bufferSource  the vertex consumer provider
     * @param stack         the armor item stack being rendered
     * @param entity        the living entity wearing the armor
     * @param slot          the equipment slot being rendered
     * @param light         the packed light value
     * @param morphContext  the active morph data; use {@code morphContext.animorph$getModel()}
     *                      to access the GeckoLib model and sync bone transforms
     */
    void renderOnMorph(PoseStack matrices, MultiBufferSource bufferSource,
                       ItemStack stack, LivingEntity entity, EquipmentSlot slot,
                       int light, IPlayerData morphContext);
}
