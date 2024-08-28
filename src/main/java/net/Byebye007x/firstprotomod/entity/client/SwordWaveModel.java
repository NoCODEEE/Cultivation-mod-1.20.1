package net.Byebye007x.firstprotomod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class SwordWaveModel <T extends Entity> extends HierarchicalModel<T> {

    private final ModelPart swordwave;

        public SwordWaveModel(ModelPart root) {
            this.swordwave = root.getChild("sword_wave");
        }

        public static LayerDefinition createSwordBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition sword_wave = partdefinition.addOrReplaceChild("sword_wave", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F, -2.0F, -4.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 24).addBox(-1.0F, -2.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 6).addBox(-7.0F, -2.0F, -3.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 0).addBox(-8.0F, -2.0F, -2.0F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 10).addBox(-6.0F, -3.0F, -2.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 8).addBox(-6.0F, -1.0F, -2.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 4).addBox(-7.0F, -3.0F, -1.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 2).addBox(-7.0F, -1.0F, -1.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 18).addBox(4.0F, -3.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 16).addBox(4.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 22).addBox(8.0F, -3.0F, 1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 22).addBox(8.0F, -1.0F, 1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(22, 12).addBox(-11.0F, -3.0F, 1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(8, 22).addBox(-11.0F, -1.0F, 1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 18).addBox(-9.0F, -3.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 16).addBox(-9.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(14, 14).addBox(4.0F, -2.0F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 14).addBox(-10.0F, -2.0F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 20).addBox(-12.0F, -2.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 20).addBox(7.0F, -2.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(23, 19).addBox(10.0F, -2.0F, 1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(23, 23).addBox(12.0F, -2.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(10, 24).addBox(13.0F, -2.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(6, 24).addBox(-14.0F, -2.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(23, 21).addBox(-14.0F, -2.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(23, 17).addBox(-13.0F, -2.0F, 1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

            return LayerDefinition.create(meshdefinition, 64, 64);
        }

        @Override
        public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
            swordwave.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        }

    @Override
    public ModelPart root() {
        return swordwave;
    }
}
