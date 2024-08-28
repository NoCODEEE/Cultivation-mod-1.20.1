package net.Byebye007x.firstprotomod.entity.client;// Made with Blockbench 4.10.4

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Byebye007x.firstprotomod.entity.animations.ModAnimationDefinitionsGf;
import net.Byebye007x.firstprotomod.entity.custom.GFEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GFModel<T extends Entity> extends HumanoidModel<LivingEntity> {
	private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();
	private final ModelPart gf;
	private final ModelPart head;

	public GFModel(ModelPart pRoot) {
		super(pRoot);
		this.gf = pRoot.getChild("gf");
		this.head = gf.getChild("head");
	}

	public static LayerDefinition createGFBodyLayer() {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();

			PartDefinition gf = partdefinition.addOrReplaceChild("gf", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

			PartDefinition head = gf.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
					.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -24.0F, 0.0F));

			PartDefinition body = gf.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
					.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));

			PartDefinition right_arm = gf.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
					.texOffs(40, 32).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

			PartDefinition left_arm = gf.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
					.texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, -22.0F, 0.0F));

			PartDefinition right_leg = gf.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
					.texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

			PartDefinition left_leg = gf.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
					.texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, -12.0F, 0.0F));

			return LayerDefinition.create(meshdefinition, 64, 64);
		}


//	this.animateWalk(ModAnimationDefinitionsGf.GF_WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);
//		this.animate(((GFEntity) entity).attackAnimationState, ModAnimationDefinitionsGf.GF_SWING, ageInTicks, 1f);
//		this.animate(((GFEntity) entity).idleAnimationState, ModAnimationDefinitionsGf.GF_IDLE, ageInTicks, 1f);


	@Override
	public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
//		this.root().getAllParts().forEach(ModelPart::resetPose);
//		this.applyHeadRotation(pNetHeadYaw, pHeadPitch, pAgeInTicks);
//
//		this.animated(((GFEntity) pEntity).idleAnimationState, ModAnimationDefinitionsGf.GF_IDLE, pAgeInTicks);
	}

//	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
//		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -5.0F, 5.0F);
//		pHeadPitch = Mth.clamp(pHeadPitch, -5.0F, 5.0F);
//
//		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
//		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
//	}
//
//	protected void animated(AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks) {
//		animating(pAnimationState, pAnimationDefinition, pAgeInTicks, 1.0F);
//	}
//
//	protected void animating(AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks, float pSpeed) {
//		pAnimationState.updateTime(pAgeInTicks, pSpeed);
//		pAnimationState.ifStarted((p_233392_) -> {
//			animate(this, pAnimationDefinition, p_233392_.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE);
//		});
//	}
//
//
//	public static void animate(GFModel<?> pModel, AnimationDefinition pAnimationDefinition, long pAccumulatedTime, float pScale, Vector3f pAnimationVecCache) {
//		float f = getElapsedSeconds(pAnimationDefinition, pAccumulatedTime);
//
//		for(Map.Entry<String, List<AnimationChannel>> entry : pAnimationDefinition.boneAnimations().entrySet()) {
//			Optional<ModelPart> optional = pModel.getAnyDescendantWithName(entry.getKey());
//			List<AnimationChannel> list = entry.getValue();
//			optional.ifPresent((p_232330_) -> {
//				list.forEach((p_288241_) -> {
//					Keyframe[] akeyframe = p_288241_.keyframes();
//					int i = Math.max(0, Mth.binarySearch(0, akeyframe.length, (p_232315_) -> {
//						return f <= akeyframe[p_232315_].timestamp();
//					}) - 1);
//					int j = Math.min(akeyframe.length - 1, i + 1);
//					Keyframe keyframe = akeyframe[i];
//					Keyframe keyframe1 = akeyframe[j];
//					float f1 = f - keyframe.timestamp();
//					float f2;
//					if (j != i) {
//						f2 = Mth.clamp(f1 / (keyframe1.timestamp() - keyframe.timestamp()), 0.0F, 1.0F);
//					} else {
//						f2 = 0.0F;
//					}
//
//					keyframe1.interpolation().apply(pAnimationVecCache, f2, akeyframe, i, j, pScale);
//					p_288241_.target().apply(p_232330_, pAnimationVecCache);
//				});
//			});
//		}
//
//	}
	private static float getElapsedSeconds(AnimationDefinition pAnimationDefinition, long pAccumulatedTime) {
		float f = (float)pAccumulatedTime / 1000.0F;
		return pAnimationDefinition.looping() ? f % pAnimationDefinition.lengthInSeconds() : f;
	}

	public Optional<ModelPart> getAnyDescendantWithName(String pName) {
		return pName.equals("root") ? Optional.of(this.root()) : this.root().getAllParts().filter((p_233400_) -> p_233400_.hasChild(pName)).findFirst().map((p_233397_) -> p_233397_.getChild(pName));
	}

    public ModelPart root() {
        return gf;
    }

    @Override
		public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			gf.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}

}