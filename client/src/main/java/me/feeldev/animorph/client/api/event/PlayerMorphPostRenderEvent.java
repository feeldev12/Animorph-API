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
 *     VertexConsumer buffer = event.getBufferSource().getBuffer(RenderLayer.getLines());
 *     // render a bounding box, outline, particle anchor, etc.
 * });
 * }</pre>
 */
public class PlayerMorphPostRenderEvent extends AnimorphEvent<AbstractClientPlayer> {

    private final PoseStack matrices;
    private final BakedGeoModel model;
    private final MultiBufferSource bufferSource;
    private final float partialTick;
    private final int packedLight;
    private final int packedOverlay;

    public PlayerMorphPostRenderEvent(
            AbstractClientPlayer player,
            PoseStack matrices,
            BakedGeoModel model,
            MultiBufferSource bufferSource,
            float partialTick,
            int packedLight,
            int packedOverlay
    ) {
        super(player);
        this.matrices = matrices;
        this.model = model;
        this.bufferSource = bufferSource;
        this.partialTick = partialTick;
        this.packedLight = packedLight;
        this.packedOverlay = packedOverlay;
    }

    /** The player whose morph was rendered. */
    public AbstractClientPlayer getPlayer() {
        return super.getPlayer();
    }

    /**
     * The active PoseStack for this render frame.
     * <p>
     * The stack is still at the model's coordinate frame (player feet).
     * Always balance any {@code pushPose()} calls with {@code popPose()}.
     */
    public PoseStack getPoseStack() {
        return matrices;
    }

    /** The baked model that was rendered. */
    public BakedGeoModel getModel() {
        return model;
    }

    /**
     * The buffer source for this render frame.
     * <p>
     * Use this to render additional geometry on top of the morph model.
     */
    public MultiBufferSource getBufferSource() {
        return bufferSource;
    }

    /** Partial render tick, in {@code [0, 1)}. */
    public float getPartialTick() {
        return partialTick;
    }

    /** Packed block + sky light for this render position. */
    public int getPackedLight() {
        return packedLight;
    }

    /** Packed overlay UVs (damage flash, etc.). */
    public int getPackedOverlay() {
        return packedOverlay;
    }
}
