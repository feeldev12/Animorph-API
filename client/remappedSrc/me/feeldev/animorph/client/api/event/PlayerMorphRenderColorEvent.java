package me.feeldev.animorph.client.api.event;

import me.feeldev.animorph.api.event.AnimorphEvent;
import net.minecraft.client.player.AbstractClientPlayer;
import software.bernie.geckolib.util.Color;

/**
 * Fired on the client when Animorph resolves the render color for a morphed player.
 * <p>
 * The color is an ARGB value applied to the entire morph model. Modify it to:
 * <ul>
 *   <li>Make the model transparent (lower alpha).</li>
 *   <li>Apply a tint (modify RGB channels).</li>
 *   <li>Implement custom damage flash or status effects.</li>
 * </ul>
 *
 * <p>Multiple listeners can each modify the color — changes are accumulated in
 * registration order. The final color returned by the last listener is used.
 *
 * <p>This event is <b>mutable</b>: call {@link #setColor(Color)} to change the color.
 *
 * <pre>{@code
 * ClientMorphAPI.getEventBus().register(PlayerMorphRenderColorEvent.class, event -> {
 *     Color current = event.getColor();
 *     // make the morph 50% transparent
 *     event.setColor(Color.ofARGB(current.getAlpha() / 2,
 *             current.getRed(), current.getGreen(), current.getBlue()));
 * });
 * }</pre>
 */
public class PlayerMorphRenderColorEvent extends AnimorphEvent<AbstractClientPlayer> {

    private Color color;
    private final float partialTick;
    private final int packedLight;

    public PlayerMorphRenderColorEvent(
            AbstractClientPlayer player,
            Color color,
            float partialTick,
            int packedLight
    ) {
        super(player);
        this.color = color;
        this.partialTick = partialTick;
        this.packedLight = packedLight;
    }

    /** The player whose morph color is being resolved. */
    public AbstractClientPlayer getPlayer() {
        return super.getPlayer();
    }

    /**
     * The current render color.
     * <p>
     * This already incorporates Animorph's own adjustments (e.g. the invisible-player
     * alpha reduction). You can read it, modify it, or replace it entirely.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Replaces the render color.
     *
     * @param color the new ARGB color; must not be {@code null}
     */
    public void setColor(Color color) {
        if (color == null) throw new IllegalArgumentException("color must not be null");
        this.color = color;
    }

    /** Partial render tick, in {@code [0, 1)}. */
    public float getPartialTick() {
        return partialTick;
    }

    /** Packed block + sky light for this render position. */
    public int getPackedLight() {
        return packedLight;
    }
}
