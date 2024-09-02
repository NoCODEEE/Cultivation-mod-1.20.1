package net.Byebye007x.firstprotomod.event;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.client.CustomHealthHUD;
import net.Byebye007x.firstprotomod.client.MpHUD;
import net.Byebye007x.firstprotomod.entity.ModEntities;
import net.Byebye007x.firstprotomod.entity.client.GFModel;
import net.Byebye007x.firstprotomod.entity.client.ModModelLayers;
import net.Byebye007x.firstprotomod.entity.client.RhinoModel;
import net.Byebye007x.firstprotomod.entity.client.SwordWaveModel;
import net.Byebye007x.firstprotomod.entity.custom.SwordWaveEntity;
import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.Byebye007x.firstprotomod.networking.packet.C2SPacket;
import net.Byebye007x.firstprotomod.networking.packet.UseMagicC2SPacket;
import net.Byebye007x.firstprotomod.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.swing.text.JTextComponent;
import java.util.logging.Level;


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
            event.registerAboveAll("mp_bar", MpHUD.CUSTOM_MP_BAR);
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.USE_MAGIC);

        }


    }

    @Mod.EventBusSubscriber(modid = ProtoMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.USE_MAGIC.consumeClick()) {
                ModPackages.sendToServer(new UseMagicC2SPacket());
            }
        }
    }

}
