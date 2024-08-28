package net.Byebye007x.firstprotomod.screen;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.Byebye007x.firstprotomod.ProtoMod;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ProtoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CustomHealthHUD {

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player != null && mc.screen == null) { // Ensure the overlay is not blocked by other screens
            PoseStack poseStack = event.getGuiGraphics().pose(); // Or event.getMatrixStack() depending on your API
            renderCustomHealthBar(poseStack, player);
        }
    }

    private static void renderCustomHealthBar(PoseStack poseStack, Player player) {
        Minecraft mc = Minecraft.getInstance();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int health = (int) player.getHealth();
        int maxHealth = (int) player.getMaxHealth();

        int barWidth = 100;
        int barHeight = 10;
        int x = (screenWidth / 2) - (barWidth / 2);
        int y = screenHeight - 49;

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();

        drawRect(poseStack, x, y, barWidth, barHeight, 0xFF000000); // Draw the background (black)
        int healthBarWidth = (int) ((health / (float) maxHealth) * barWidth);
        drawRect(poseStack, x, y, healthBarWidth, barHeight, 0xFFFF0000); // Draw the health bar (red)
    }

    private static void drawRect(PoseStack poseStack, int x, int y, int width, int height, int color) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();

        RenderSystem.setShaderColor(
                (color >> 16 & 255) / 255.0F,
                (color >> 8 & 255) / 255.0F,
                (color & 255) / 255.0F,
                (color >> 24 & 255) / 255.0F
        );

        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        bufferbuilder.vertex(poseStack.last().pose(), x, y + height, 0.0F).endVertex();
        bufferbuilder.vertex(poseStack.last().pose(), x + width, y + height, 0.0F).endVertex();
        bufferbuilder.vertex(poseStack.last().pose(), x + width, y, 0.0F).endVertex();
        bufferbuilder.vertex(poseStack.last().pose(), x, y, 0.0F).endVertex();
        tesselator.end();
    }
}

