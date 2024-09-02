package net.Byebye007x.firstprotomod.client;


import com.mojang.blaze3d.systems.RenderSystem;
import net.Byebye007x.firstprotomod.ProtoMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ProtoMod.MOD_ID, value = Dist.CLIENT)
public class CustomHealthHUD {

    private static final ResourceLocation HEALTH_BAR =
            new ResourceLocation(ProtoMod.MOD_ID, "textures/gui/hud/health_bar.png");

    @SubscribeEvent
    public static void stopVanillaHealthOverlay(RenderGuiOverlayEvent.Pre event) {
        if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void stopRenderHealthOverlays(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player.isCreative() || player.isSpectator()) {
            if (event.getOverlay().overlay() == CUSTOM_HEALTH_BAR) {
                event.setCanceled(true);
            }
        }
    }

    public static final IGuiOverlay CUSTOM_HEALTH_BAR = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = 5;
        int y = 5;
        float scale = 0.5f;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, HEALTH_BAR);

            //health
        guiGraphics.blit(HEALTH_BAR, x, y, 0, 36, (int) ((remainingHealth() * scale) - 1), (int) (24 * scale), 256, 64);
            //empty bar
        guiGraphics.blit(HEALTH_BAR, x, y, 0, 0, (int) (257 * scale), (int) (27 * scale), 256, 28);
    });


    private static int remainingHealth() {
        Minecraft MINECRAFT = Minecraft.getInstance();
        Player PLAYER = MINECRAFT.player;
        float health = PLAYER.getHealth();
        float maxHealth = PLAYER.getMaxHealth();
        final int oneHealthPortion = 32;
        int portionRender = (int) Math.ceil(health / maxHealth * 8);

        return portionRender * oneHealthPortion;
    }

}