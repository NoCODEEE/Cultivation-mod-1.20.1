package net.Byebye007x.firstprotomod.event;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.entity.client.GFModel;
import net.Byebye007x.firstprotomod.entity.client.ModModelLayers;
import net.Byebye007x.firstprotomod.entity.client.RhinoModel;
import net.Byebye007x.firstprotomod.entity.client.SwordWaveModel;
import net.Byebye007x.firstprotomod.particle.ModParticles;
import net.Byebye007x.firstprotomod.particle.custom.GlitterLightParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProtoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.RHINO_LAYER, RhinoModel::createRhinoBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GF_LAYER, GFModel::createGFBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SWORD_WAVE_LAYER, SwordWaveModel::createSwordBodyLayer);

    }
}
