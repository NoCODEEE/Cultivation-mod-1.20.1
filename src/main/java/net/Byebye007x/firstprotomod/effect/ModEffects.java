package net.Byebye007x.firstprotomod.effect;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.effect.custom.Flight;
import net.Byebye007x.firstprotomod.effect.custom.Perception;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ProtoMod.MOD_ID);

    public static final RegistryObject<MobEffect> PERCEPTION = MOB_EFFECTS.register("perception",
            () -> new Perception(MobEffectCategory.BENEFICIAL, 0xFFFFFF)); // Example: green color

    public static final RegistryObject<MobEffect> FLIGHT = MOB_EFFECTS.register("flight",
            () -> new Flight(MobEffectCategory.BENEFICIAL));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
