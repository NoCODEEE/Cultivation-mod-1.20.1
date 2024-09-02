package net.Byebye007x.firstprotomod.client;


import com.mojang.blaze3d.systems.RenderSystem;
import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.magic.PlayerMagicProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProtoMod.MOD_ID, value = Dist.CLIENT)
public class MpHUD {

    private static final ResourceLocation MP_BAR =
            new ResourceLocation(ProtoMod.MOD_ID, "textures/gui/hud/mp_bar.png");


    @SubscribeEvent
    public static void stopRenderOverlays(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player.isCreative() || player.isSpectator()) {
            if (event.getOverlay().overlay() == CUSTOM_MP_BAR) {
                event.setCanceled(true);
            }
        }
    }

    public static final IGuiOverlay CUSTOM_MP_BAR = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = 5;
        int y = 25;
        float scale = 0.5f;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, MP_BAR);

            //value
        guiGraphics.blit(MP_BAR, x, y, 0, 36, (int) ((remainingValue() * scale) - 1), (int) (24 * scale), 256, 64);
            //empty bar
        guiGraphics.blit(MP_BAR, x, y, 0, 0, (int) (257 * scale), (int) (27 * scale), 256, 28);
    });


    private static int remainingValue() {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        float mp = ClientMpData.getPlayerMp();
        float maxMp = ClientMpData.getPlayerMaxMp();

//        System.out.println("MP: " + mp + " / " + maxMp + "\n");

        final int onePortion = 32;
        int portionRender = (int) Math.ceil(mp / maxMp * 8);

        return portionRender * onePortion;
    }

}