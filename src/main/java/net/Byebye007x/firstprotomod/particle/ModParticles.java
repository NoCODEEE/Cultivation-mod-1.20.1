package net.Byebye007x.firstprotomod.particle;

import net.Byebye007x.firstprotomod.ProtoMod;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ProtoMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> GLITTER_LIGHT = PARTICLE_TYPES.register("glitter_light",
            () -> new SimpleParticleType(true));


    public static void register (IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
