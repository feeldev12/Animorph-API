package me.feeldev.animorph.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * How a player looks on their own model beyond the model itself: their skin, skin model (slim or
 * classic), cape, and extra layer models attached to them — e.g. a hat, wings, or a body part that
 * replaces the model's head. Lets an integration dress each player differently while the models
 * stay shared: every attached layer is a registered {@code is_layer} model, baked once on each
 * client however many players wear it; only this description travels per player.
 * <p>
 * Textures are refs ({@code client:<id>} or a remote {@code https://...#sha256=...}), so their
 * content never travels in a game packet; until one is there the player's own (vanilla) one shows.
 *
 * @param modelId the model this applies to (the player's own avatar); {@code null} for any model.
 *                A player switched to another model (a Morph) looks like that model.
 * @param skinRef the skin texture; {@code null} keeps the player's own
 * @param slim    whether the skin is slim (arms 3 px wide); {@code null} keeps the player's own
 * @param capeRef the cape texture; {@code null} keeps the player's own
 * @param layers  layer models attached to the player, drawn in this order
 */
public record PlayerAppearance(String modelId, String skinRef, Boolean slim, String capeRef, List<AttachedLayer> layers) {

    public static final PlayerAppearance NONE = new PlayerAppearance(null, null, null, null, List.of());

    public PlayerAppearance {
        layers = List.copyOf(Objects.requireNonNull(layers, "layers"));
    }

    public boolean isEmpty() {
        return skinRef == null && slim == null && capeRef == null && layers.isEmpty();
    }

    /** Every texture ref, e.g. to prefetch them before the player shows up. */
    public List<String> textureRefs() {
        List<String> refs = new ArrayList<>(2);
        if (skinRef != null) refs.add(skinRef);
        if (capeRef != null) refs.add(capeRef);
        return refs;
    }

    /**
     * @param modelId         a registered model with {@code is_layer: true}
     * @param hideBones       bones of the player's model whose own cubes are hidden while it's
     *                        attached — never their children, so a body part replacing the torso
     *                        lists the torso's bones and keeps the arms and head; empty for
     *                        something worn on top
     * @param showFirstPerson whether it's drawn in first person too
     */
    public record AttachedLayer(String modelId, List<String> hideBones, boolean showFirstPerson) {
        public AttachedLayer {
            Objects.requireNonNull(modelId, "modelId");
            hideBones = List.copyOf(Objects.requireNonNull(hideBones, "hideBones"));
        }
    }
}
