package net.Byebye007x.firstprotomod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.entity.custom.GFEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GFRenderer extends MobRenderer<GFEntity, GFModel<GFEntity>> {
    public GFRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GFModel<>(pContext.bakeLayer(ModModelLayers.GF_LAYER)), 0.3f);
    }


    @Override
    public ResourceLocation getTextureLocation(GFEntity pEntity) {
        return new ResourceLocation(ProtoMod.MOD_ID, "textures/entity/gf.png");
    }

    @Override
    public void render(GFEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
