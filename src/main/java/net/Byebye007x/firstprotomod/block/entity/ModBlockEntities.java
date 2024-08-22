package net.Byebye007x.firstprotomod.block.entity;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ProtoMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<GemPolishingTableBlockEntity>> GEM_POLISHING_BE =
            BLOCK_ENTITIES.register("gem_polishing_be", () ->
                    BlockEntityType.Builder.of(GemPolishingTableBlockEntity::new,
                            ModBlocks.GEM_POLISHING_TABLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
