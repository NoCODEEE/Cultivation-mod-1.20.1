package net.Byebye007x.firstprotomod.event;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.entity.ModEntities;
import net.Byebye007x.firstprotomod.entity.custom.GFEntity;
import net.Byebye007x.firstprotomod.entity.custom.RhinoEntity;
import net.Byebye007x.firstprotomod.particle.ModParticles;
import net.Byebye007x.firstprotomod.particle.custom.GlitterLightParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProtoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
        event.put(ModEntities.GF.get(), GFEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerParticleFactories (final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.GLITTER_LIGHT.get(),
                GlitterLightParticle.Provider::new);
    }
}
