package net.Byebye007x.firstprotomod.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BlockWhiteBorder {

    public static void renderBlockOutline(PoseStack poseStack) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        // Get the player's eye position and view vector
        Vec3 eyePosition = player.getEyePosition(1.0F);
        Vec3 viewVector = player.getViewVector(1.0F);

        // Extend the view vector to the maximum reach distance (using Minecraft's pick range)
        Vec3 targetVector = eyePosition.add(viewVector.scale(mc.gameMode.getPickRange()));

        // Perform a ray trace to find the block the player is looking at
        BlockHitResult traceResult = mc.level.clip(new ClipContext(
                eyePosition, targetVector, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));


        // If we hit a block, render the outline around it
        if (traceResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = traceResult.getBlockPos();
            drawWhiteOutlineAroundBlock(mc, poseStack, blockPos);
        }
    }

    public static void drawWhiteOutlineAroundBlock(Minecraft mc, PoseStack poseStack, BlockPos blockPos) {
        // Get the current camera position (player's point of view)
        Vec3 cameraPos = mc.gameRenderer.getMainCamera().getPosition();

        // Push the current matrix state
        poseStack.pushPose();

        // Translate the poseStack based on the difference between the block's world position and the camera position
        poseStack.translate(blockPos.getX() - cameraPos.x, blockPos.getY() - cameraPos.y, blockPos.getZ() - cameraPos.z);

        AABB blockBounds = new AABB(BlockPos.ZERO).deflate(- 0.002D);

        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = bufferSource.getBuffer(RenderType.lines());

        // Render the white outline around the block
        LevelRenderer.renderLineBox(poseStack, buffer, blockBounds, 1.0F, 1.0F, 1.0F, 1.0F);

        // Pop the pose stack to restore it to the previous state
        poseStack.popPose();

        bufferSource.endBatch(RenderType.lines());
    }

}
