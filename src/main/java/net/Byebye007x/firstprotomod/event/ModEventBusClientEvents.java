package net.Byebye007x.firstprotomod.event;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.client.CustomHealthHUD;
import net.Byebye007x.firstprotomod.client.MpHUD;
import net.Byebye007x.firstprotomod.enchantment.CloudStepEnchantment;
import net.Byebye007x.firstprotomod.enchantment.DashEnchantment;
import net.Byebye007x.firstprotomod.entity.client.GFModel;
import net.Byebye007x.firstprotomod.entity.client.ModModelLayers;
import net.Byebye007x.firstprotomod.entity.client.RhinoModel;
import net.Byebye007x.firstprotomod.entity.client.SwordWaveModel;
import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.Byebye007x.firstprotomod.networking.packet.UseCloudStepEnchantmentC2SPacket;
import net.Byebye007x.firstprotomod.networking.packet.UseDashEnchantmentC2SPacket;
import net.Byebye007x.firstprotomod.networking.packet.UseMagicC2SPacket;
import net.Byebye007x.firstprotomod.renderer.BlockWhiteBorder;
import net.Byebye007x.firstprotomod.util.KeyBinding;
import net.Byebye007x.firstprotomod.util.ToggleHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

public class ModEventBusClientEvents {

    @Mod.EventBusSubscriber(modid = ProtoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModModelLayers.RHINO_LAYER, RhinoModel::createRhinoBodyLayer);
            event.registerLayerDefinition(ModModelLayers.GF_LAYER, GFModel::createGFBodyLayer);
            event.registerLayerDefinition(ModModelLayers.SWORD_WAVE_LAYER, SwordWaveModel::createSwordBodyLayer);

        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("health_bar", CustomHealthHUD.CUSTOM_HEALTH_BAR);
            event.registerAboveAll("health_value", CustomHealthHUD.HEALTH_VALUE);
            event.registerAboveAll("mp_bar", MpHUD.CUSTOM_MP_BAR);
            event.registerAboveAll("mp_value", MpHUD.MP_VALUE);
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.USE_MAGIC);
            event.register(KeyBinding.USE_DASH);
            event.register(KeyBinding.USE_CLOUD_STEP);
            event.register(KeyBinding.CULTIVATING);
            event.register(KeyBinding.WHITE_BORDER);

        }


    }

    @Mod.EventBusSubscriber(modid = ProtoMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Player player = Minecraft.getInstance().player;
            if (KeyBinding.USE_MAGIC.consumeClick()) {
                ModPackages.sendToServer(new UseMagicC2SPacket());
            }

            if (KeyBinding.USE_DASH.consumeClick()) {
                if (DashEnchantment.canDash(player)) {
                    DashEnchantment.doDash(player, DashEnchantment.getExistingLevel(player));
                    ModPackages.sendToServer(new UseDashEnchantmentC2SPacket());
                }
            }

            if (KeyBinding.USE_CLOUD_STEP.consumeClick()) {
                if (CloudStepEnchantment.canJump(player)) {
                    CloudStepEnchantment.doJump(player);
                    ModPackages.sendToServer(new UseCloudStepEnchantmentC2SPacket());
                }
            }

            if (KeyBinding.CULTIVATING.consumeClick()) {
                ToggleHandler.changeCultivationState();
                if (ToggleHandler.isCultivating()) {
                    player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
                } else {
                    player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
                }
                System.out.println(ToggleHandler.isCultivating());
            }

            if (KeyBinding.WHITE_BORDER.consumeClick()) {
                ToggleHandler.changeAltState();
            }
        }

//        private static int tick = 0;
//        @SubscribeEvent
//        public static void onPlayerTick (TickEvent.PlayerTickEvent event) {
//            if (event.side == LogicalSide.CLIENT) {
//                tick++;
//            }
//            if (tick == 80) tick = 0;
//        }

        @SubscribeEvent
        public static void onRenderWorldLast(RenderLevelStageEvent event) {
            if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_TRIPWIRE_BLOCKS) {
                if (ToggleHandler.isAlt()) {
                    BlockWhiteBorder.renderBlockOutline(event.getPoseStack());
                    Minecraft.getInstance().renderBuffers().bufferSource().endBatch(RenderType.lines());
                }
            }
        }



    }
}
