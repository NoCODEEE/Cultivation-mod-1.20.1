package net.Byebye007x.firstprotomod.entity;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.entity.custom.GFEntity;
import net.Byebye007x.firstprotomod.entity.custom.SwordWaveEntity;
import net.Byebye007x.firstprotomod.entity.custom.RhinoEntity;
import net.Byebye007x.firstprotomod.entity.custom.WarMachineEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ProtoMod.MOD_ID);

    public static final RegistryObject<EntityType<RhinoEntity>> RHINO =
            ENTITY_TYPES.register("rhino", () -> EntityType.Builder.of(RhinoEntity::new, MobCategory.CREATURE)
                    .sized(2.5f, 2.5f).build("rhino"));
    public static final RegistryObject<EntityType<GFEntity>> GF =
            ENTITY_TYPES.register("girlfriend", () -> EntityType.Builder.of(GFEntity::new, MobCategory.CREATURE)
                    .sized(0.7f, 1.95f).build("girlfriend"));

    public static final RegistryObject<EntityType<SwordWaveEntity>> SWORD_WAVE =
            ENTITY_TYPES.register("sword_wave", () -> EntityType.Builder.<SwordWaveEntity>of(SwordWaveEntity::new, MobCategory.MISC)
                    .sized(1.8f, 0.5f).build("sword_wave"));

    public static final RegistryObject<EntityType<WarMachineEntity>> WAR_MACHINE =
            ENTITY_TYPES.register("war_machine", () -> EntityType.Builder.<WarMachineEntity>of(WarMachineEntity::new, MobCategory.MISC)
                    .sized(1.8f, 0.5f).build("war_machine"));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
