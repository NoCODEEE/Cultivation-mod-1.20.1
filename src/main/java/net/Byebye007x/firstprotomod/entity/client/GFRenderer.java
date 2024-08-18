package net.Byebye007x.firstprotomod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.entity.custom.GFEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class GFRenderer extends MobRenderer<GFEntity, HumanoidModel<GFEntity>> {
    public GFRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HumanoidModel<>(pContext.bakeLayer(ModelLayers.PLAYER)), 0.3f);
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    private static final ResourceLocation[] VARIANT_TEXTURES = {
            new ResourceLocation(ProtoMod.MOD_ID, "textures/entity/gf/alya_gf.png"),
            new ResourceLocation(ProtoMod.MOD_ID, "textures/entity/gf/frieren_gf.png"),
            new ResourceLocation(ProtoMod.MOD_ID, "textures/entity/gf/megumin_gf.png"),
            new ResourceLocation(ProtoMod.MOD_ID, "textures/entity/gf/blue_maid_gf.png"),
            new ResourceLocation(ProtoMod.MOD_ID, "textures/entity/gf/shinokono_gf.png"),
            new ResourceLocation(ProtoMod.MOD_ID, "textures/entity/gf/ganyu_gf.png"),
            new ResourceLocation(ProtoMod.MOD_ID, "textures/entity/gf/purple_flower_gf.png")
    };

    @Override
    public ResourceLocation getTextureLocation(GFEntity pEntity) {
        return VARIANT_TEXTURES[pEntity.getVariant()];
    }

    @Override
    public void render(GFEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby() || pEntity.isInSittingPose()) {
            pPoseStack.scale(0.7f, 0.7f, 0.7f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
