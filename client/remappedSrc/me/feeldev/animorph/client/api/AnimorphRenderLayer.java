package me.feeldev.animorph.client.api;

/**
 * A client-side render layer that runs within the morph model's render pipeline.
 *
 * <p>Register instances via {@link ClientMorphAPI#registerRenderLayer(AnimorphRenderLayer)}
 * (global) or {@link ClientMorphAPI#registerRenderLayer(String, AnimorphRenderLayer)}
 * (scoped to a specific model ID). The layer is called after the main model geometry
 * is drawn, at the same point in the pipeline as GeckoLib's own render layers.
 *
 * <pre>{@code
 * // Global — runs for every morph
 * ClientMorphAPI.registerRenderLayer(context -> {
 *     // draw something on top of any morph
 * });
 *
 * // Scoped — only runs when the active model ID is "buh_cat"
 * ClientMorphAPI.registerRenderLayer("buh_cat", context -> {
 *     IPlayerData morph = context.animatable();
 *     MatrixStack matrices = context.matrices();
 *     // render something specific to buh_cat
 * });
 * }</pre>
 */
@FunctionalInterface
public interface AnimorphRenderLayer {

    /**
     * Called once per render frame for each player whose active morph matches
     * the registered model ID (or all morphs if registered globally).
     *
     * @param context the full rendering context for this frame
     */
    void render(AnimorphRenderContext context);
}
