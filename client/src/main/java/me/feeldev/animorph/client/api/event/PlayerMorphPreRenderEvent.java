package me.feeldev.animorph.client.api.event;

import com.mojang.blaze3d.vertex.PoseStack;
import me.feeldev.animorph.api.event.AnimorphEvent;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import software.bernie.geckolib.cache.object.BakedGeoModel;

/**
 * Fired on the client just before Animorph renders the morph model of a player.
 * <p>
 * At this point the {@link PoseStack} has been positioned and rotated by Animorph
 * (same transforms that GeckoLib's {@code preRender} applies), but
 * {@code actuallyRender} has not yet been called. Use this event to:
 * <ul>
 *   <li>Push additional PoseStack transforms (scale, translate, rotate).</li>
 *   <li>Hide or unhide bones on the {@link BakedGeoModel} before the draw call.</li>
 *   <li>Render geometry that must appear <em>behind</em> the morph model.</li>
 * </ul>
 *
 * <p>This event is <b>cancellable</b>: call {@link #setCancelled(boolean) setCancelled(true)} to
 * suppress the entire morph render for this frame. When cancelled, {@code actuallyRender},
 * all render layers, and {@link PlayerMorphPostRenderEvent} are all skipped.
 *
 * <p><b>Important:</b> if you push to the PoseStack here you MUST pop the same
 * number of times before the method returns — failure to do so will corrupt the
 * render stack for all subsequent entities in the frame.
 *
 * <pre>{@code
 * ClientMorphAPI.getEventBus().register(PlayerMorphPreRenderEvent.class, event -> {
 *     event.getPoseStack().pushPose();
 *     event.getPoseStack().scale(1.1f, 1.1f, 1.1f);
 *     // render something, then:
 *     event.getPoseStack().popPose();
 * });
 * }</pre>
 */
public class PlayerMorphPreRenderEvent extends AnimorphEvent<AbstractClientPlayer> {

    public PlayerMorphPreRenderEvent(AbstractClientPlayer player, PoseStack poseStack, BakedGeoModel model, MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
        super(player);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The player whose morph is about to be rendered.
     */
    public AbstractClientPlayer getPlayer() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The active PoseStack for this render frame.
     * <p>
     * Transforms applied here affect the entire morph render that follows.
     * Always balance every {@code pushPose()} with a {@code popPose()}.
     */
    public PoseStack getPoseStack() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The baked model that will be rendered.
     * <p>
     * You can call {@code model.getBone(name)} to hide/show individual bones
     * before the draw call.
     */
    public BakedGeoModel getModel() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The buffer source for this render frame.
     * <p>
     * Use this to render additional geometry before the morph model is drawn.
     * Retrieve a buffer with {@code bufferSource.getBuffer(renderType)}.
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

    /**
     * Returns {@code true} if the morph render has been cancelled for this frame.
     */
    public boolean isCancelled() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Cancels or restores the morph render for this frame.
     * <p>
     * When {@code true}, Animorph skips {@code actuallyRender}, all render layers,
     * and {@link PlayerMorphPostRenderEvent} entirely. The vanilla player renderer
     * is NOT restored — the player will simply be invisible for this frame.
     *
     * @param cancelled {@code true} to suppress the render
     */
    public void setCancelled(boolean cancelled) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
