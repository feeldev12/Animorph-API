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

    public PlayerMorphNametagRenderEvent(AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float partialTick, Component label) {
        super(player);
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The player whose nametag is about to be rendered.
     */
    public AbstractClientPlayer getPlayer() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The active PoseStack, already positioned at the nametag anchor.
     * <p>
     * Always balance every {@code pushPose()} with a {@code popPose()}.
     */
    public PoseStack getPoseStack() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The buffer source for this render frame.
     * <p>
     * Use this to render additional geometry alongside the nametag. Retrieve a
     * buffer with {@code bufferSource.getBuffer(renderType)}.
     */
    public MultiBufferSource getBufferSource() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Packed block + sky light for this render position.
     */
    public int getPackedLight() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Partial render tick, in {@code [0, 1)}.
     */
    public float getPartialTick() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The label text about to be drawn. Already resolved (team prefix/suffix, etc).
     */
    public Component getLabel() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Replaces the label text that will be drawn.
     *
     * @param label the new label; must not be {@code null}
     */
    public void setLabel(Component label) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
