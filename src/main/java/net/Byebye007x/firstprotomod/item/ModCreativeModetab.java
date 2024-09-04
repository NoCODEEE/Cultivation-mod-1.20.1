package net.Byebye007x.firstprotomod.item;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeModetab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ProtoMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CUSTOM_TAB = CREATIVE_MODE_TABS.register("firstprotomod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TEST_HEART.get()))
                    .title(Component.translatable("creativetab.firstprotomod_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //Items
                        output.accept(Items.DIAMOND);
                        output.accept(ModItems.RUBY.get());
                        output.accept(ModItems.RAW_RUBY.get());
                        output.accept(ModItems.TEST_HEART.get());
                        output.accept(ModItems.TEST_SWORD.get());
                        output.accept(ModItems.STRAWBERRY.get());
                        output.accept(ModItems.PINE_CONE.get());
                        output.accept(ModItems.RUBY_SWORD.get());
                        output.accept(ModItems.RUBY_PICKAXE.get());
                        output.accept(ModItems.RUBY_AXE.get());
                        output.accept(ModItems.RUBY_SHOVEL.get());
                        output.accept(ModItems.RUBY_HOE.get());
                        output.accept(ModItems.RUBY_HELMET.get());
                        output.accept(ModItems.RUBY_CHESTPLATE.get());
                        output.accept(ModItems.RUBY_LEGGINGS.get());
                        output.accept(ModItems.RUBY_BOOTS.get());
                        output.accept(ModItems.STRAWBERRY_SEEDS.get());
                        output.accept(ModItems.PLOY_SCYTHE.get());
                        output.accept(ModItems.RHINO_SPAWN_EGG.get());
                        output.accept(ModItems.MAGIC_FRUIT.get());

                        //Blocks
                        output.accept(ModBlocks.RUBY_ORE.get());
                        output.accept(ModBlocks.RAW_RUBY_BLOCK.get());
                        output.accept(ModBlocks.RUBY_BLOCK.get());
                        output.accept(ModBlocks.SOUND_BLOCK.get());
                        output.accept(ModBlocks.GEM_POLISHING_TABLE.get());


                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
