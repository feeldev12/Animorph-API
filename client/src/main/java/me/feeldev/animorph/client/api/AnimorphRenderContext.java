package me.feeldev.animorph.client.api;

import com.mojang.blaze3d.vertex.PoseStack;
import me.feeldev.animorph.client.interfaces.IPlayerData;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;

/**
 * Snapshot of the rendering context passed to each {@link AnimorphRenderLayer}.
 *
 * <p>Provides everything a custom render layer needs to draw within the
 * morph model's render pipeline — the same data that GeckoLib passes to
 * its own {@code GeoRenderLayer} instances.
 *
 * @param matrices      the current matrix stack, already positioned at the entity root
 * @param animatable    the active morph context; exposes the player entity and the GeckoLib model
 * @param bakedModel    the fully-baked GeckoLib geometry currently being rendered
 * @param renderType    the active render layer, or {@code null} if none is set
 * @param bufferSource  the vertex consumer provider for obtaining draw buffers
 * @param partialTick   the partial tick for interpolation
 * @param packedLight   the packed block/sky light value at the entity position
 * @param packedOverlay the packed overlay UV (hurt flash, etc.)
 */
public record AnimorphRenderContext(
        PoseStack matrices,
        IPlayerData animatable,
        BakedGeoModel bakedModel,
        @Nullable RenderType renderType,
        MultiBufferSource bufferSource,
        float partialTick,
        int packedLight,
        int packedOverlay
) {}
