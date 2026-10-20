package me.feeldev.animorph.client.api;

import com.mojang.blaze3d.vertex.PoseStack;
import me.feeldev.animorph.client.interfaces.IPlayerData;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;

public record AnimorphRenderContext(PoseStack matrices, IPlayerData animatable, BakedGeoModel bakedModel, @Nullable RenderType renderType, MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
}
