package me.feeldev.animorph.client.api.event;

import me.feeldev.animorph.api.event.AnimorphEvent;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;

/**
 * Fired on the client just before Animorph renders the morph model of a player.
 * <p>
 * At this point the {@link MatrixStack} has been positioned and rotated by Animorph
 * (same transforms that GeckoLib's {@code preRender} applies), but
 * {@code actuallyRender} has not yet been called. Use this event to:
 * <ul>
 *   <li>Push additional MatrixStack transforms (scale, translate, rotate).</li>
 *   <li>Hide or unhide bones on the {@link BakedGeoModel} before the draw call.</li>
 *   <li>Render geometry that must appear <em>behind</em> the morph model.</li>
 * </ul>
 *
 * <p>This event is <b>cancellable</b>: call {@link #setCancelled(boolean) setCancelled(true)} to
 * suppress the entire morph render for this frame. When cancelled, {@code actuallyRender},
 * all render layers, and {@link PlayerMorphPostRenderEvent} are all skipped.
 *
 * <p><b>Important:</b> if you push to the MatrixStack here you MUST pop the same
 * number of times before the method returns — failure to do so will corrupt the
 * render stack for all subsequent entities in the frame.
 *
 * <pre>{@code
 * ClientMorphAPI.getEventBus().register(PlayerMorphPreRenderEvent.class, event -> {
 *     event.getMatrices().push();
 *     event.getMatrices().scale(1.1f, 1.1f, 1.1f);
 *     // render something, then:
 *     event.getMatrices().pop();
 * });
 * }</pre>
 */
public class PlayerMorphPreRenderEvent extends AnimorphEvent<AbstractClientPlayerEntity> {

    private final MatrixStack matrices;
    private final BakedGeoModel model;
    private final VertexConsumerProvider bufferSource;
    private final float partialTick;
    private final int packedLight;
    private final int packedOverlay;
    private boolean cancelled = false;

    public PlayerMorphPreRenderEvent(
            AbstractClientPlayerEntity player,
            MatrixStack matrices,
            BakedGeoModel model,
            VertexConsumerProvider bufferSource,
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

    /** The player whose morph is about to be rendered. */
    public AbstractClientPlayerEntity getPlayer() {
        return super.getPlayer();
    }

    /**
     * The active MatrixStack for this render frame.
     * <p>
     * Transforms applied here affect the entire morph render that follows.
     * Always balance every {@code push()} with a {@code pop()}.
     */
    public MatrixStack getMatrices() {
        return matrices;
    }

    /**
     * The baked model that will be rendered.
     * <p>
     * You can call {@code model.getBone(name)} to hide/show individual bones
     * before the draw call.
     */
    public BakedGeoModel getModel() {
        return model;
    }

    /**
     * The buffer source for this render frame.
     * <p>
     * Use this to render additional geometry before the morph model is drawn.
     * Retrieve a buffer with {@code bufferSource.getBuffer(renderLayer)}.
     */
    public VertexConsumerProvider getBufferSource() {
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

    /**
     * Returns {@code true} if the morph render has been cancelled for this frame.
     */
    public boolean isCancelled() {
        return cancelled;
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
        this.cancelled = cancelled;
    }
}
