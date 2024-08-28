package net.Byebye007x.firstprotomod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.entity.custom.SwordWaveEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SwordWaveRenderer extends EntityRenderer<SwordWaveEntity> {

    private final SwordWaveModel<SwordWaveEntity> model;

    public SwordWaveRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new SwordWaveModel<>(pContext.bakeLayer(ModModelLayers.SWORD_WAVE_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(SwordWaveEntity pEntity) {
        return new ResourceLocation(ProtoMod.MOD_ID, "textures/entity/sword_wave.png");
    }

    @Override
    public void render(SwordWaveEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0d, 0.0d, 0.0d); // Adjust translation based on your model's position

        // Rotate the model based on the entity's direction
        pPoseStack.mulPose(Axis.YP.rotationDegrees(-pEntityYaw));
        

        // Get the texture and render the model
        VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(pEntity)));
        model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        pPoseStack.popPose();

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}