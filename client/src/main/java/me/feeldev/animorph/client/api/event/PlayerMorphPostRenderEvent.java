package me.feeldev.animorph.client.api.event;

import com.mojang.blaze3d.vertex.PoseStack;
import me.feeldev.animorph.api.event.AnimorphEvent;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import software.bernie.geckolib.cache.object.BakedGeoModel;

/**
 * Fired on the client just after Animorph finishes rendering the morph model
 * of a player (model geometry + all render layers).
 * <p>
 * Use this event to render additional geometry on top of the morph, apply
 * post-render effects, or react to the render having completed. The
 * {@link PoseStack} is still in the same coordinate frame as the model,
 * so transforms are relative to the player's feet.
 *
 * <p>This event is <b>not cancellable</b>.
 *
 * <pre>{@code
 * ClientMorphAPI.getEventBus().register(PlayerMorphPostRenderEvent.class, event -> {
 *     VertexConsumer buffer = event.getBufferSource().getBuffer(RenderType.lines());
 *     // render a bounding box, outline, particle anchor, etc.
 * });
 * }</pre>
 */
public class PlayerMorphPostRenderEvent extends AnimorphEvent<AbstractClientPlayer> {

    public PlayerMorphPostRenderEvent(AbstractClientPlayer player, PoseStack poseStack, BakedGeoModel model, MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
        super(player);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The player whose morph was rendered.
     */
    public AbstractClientPlayer getPlayer() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The active PoseStack for this render frame.
     * <p>
     * The stack is still at the model's coordinate frame (player feet).
     * Always balance any {@code pushPose()} calls with {@code popPose()}.
     */
    public PoseStack getPoseStack() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The baked model that was rendered.
     */
    public BakedGeoModel getModel() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The buffer source for this render frame.
     * <p>
     * Use this to render additional geometry on top of the morph model.
     */
    public MultiBufferSource getBufferSource() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Partial render tick, in {@code [0, 1)}.
     */
    public float getPartialTick() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Packed block + sky light for this render position.
     */
    public int getPackedLight() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Packed overlay UVs (damage flash, etc.).
     */
    public int getPackedOverlay() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
