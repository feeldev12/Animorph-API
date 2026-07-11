package me.feeldev.animorph.client.api.event;

import com.mojang.blaze3d.vertex.PoseStack;
import me.feeldev.animorph.api.event.AnimorphEvent;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;

/**
 * Fired on the client right before Animorph draws the nametag of a morphed player.
 * <p>
 * The {@link PoseStack} is already positioned at the nametag anchor (after Animorph's
 * {@code nametag} bone offset and any scoreboard-objective stacking). Use this event to:
 * <ul>
 *   <li>Replace or decorate the label text (call {@link #setLabel(Component)}).</li>
 *   <li>Draw extra geometry above or below the nametag (icons, badges, bars) by pushing
 *       additional PoseStack transforms and rendering into {@link #getBufferSource()}.</li>
 * </ul>
 *
 * <p>This event is <b>mutable</b>: the {@link Component} returned by {@link #getLabel()}
 * after all listeners run is what actually gets drawn.
 *
 * <p><b>Important:</b> if you push to the PoseStack here you MUST pop the same
 * number of times before your listener returns — failure to do so will corrupt the
 * render stack for the rest of this entity's nametag draw.
 *
 * <pre>{@code
 * ClientMorphAPI.getEventBus().register(PlayerMorphNametagRenderEvent.class, event -> {
 *     event.getPoseStack().pushPose();
 *     event.getPoseStack().translate(0.0, -0.25, 0.0);
 *     // render something above the nametag using event.getBufferSource(), then:
 *     event.getPoseStack().popPose();
 * });
 * }</pre>
 */
public class PlayerMorphNametagRenderEvent extends AnimorphEvent<AbstractClientPlayer> {

    private final PoseStack poseStack;
    private final MultiBufferSource bufferSource;
    private final int packedLight;
    private final float partialTick;
    private Component label;

    public PlayerMorphNametagRenderEvent(
            AbstractClientPlayer player,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            float partialTick,
            Component label
    ) {
        super(player);
        this.poseStack = poseStack;
        this.bufferSource = bufferSource;
        this.packedLight = packedLight;
        this.partialTick = partialTick;
        this.label = label;
    }

    /** The player whose nametag is about to be rendered. */
    public AbstractClientPlayer getPlayer() {
        return super.getPlayer();
    }

    /**
     * The active PoseStack, already positioned at the nametag anchor.
     * <p>
     * Always balance every {@code pushPose()} with a {@code popPose()}.
     */
    public PoseStack getPoseStack() {
        return poseStack;
    }

    /**
     * The buffer source for this render frame.
     * <p>
     * Use this to render additional geometry alongside the nametag. Retrieve a
     * buffer with {@code bufferSource.getBuffer(renderType)}.
     */
    public MultiBufferSource getBufferSource() {
        return bufferSource;
    }

    /** Packed block + sky light for this render position. */
    public int getPackedLight() {
        return packedLight;
    }

    /** Partial render tick, in {@code [0, 1)}. */
    public float getPartialTick() {
        return partialTick;
    }

    /** The label text about to be drawn. Already resolved (team prefix/suffix, etc). */
    public Component getLabel() {
        return label;
    }

    /**
     * Replaces the label text that will be drawn.
     *
     * @param label the new label; must not be {@code null}
     */
    public void setLabel(Component label) {
        if (label == null) throw new IllegalArgumentException("label must not be null");
        this.label = label;
    }
}
