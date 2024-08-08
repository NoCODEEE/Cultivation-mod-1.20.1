package net.Byebye007x.firstprotomod.item;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.block.Modblocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeModetab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ProtoMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CUSTOM_TAB = CREATIVE_MODE_TABS.register("firstprotomod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RUBY.get()))
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

                        //Blocks
                        output.accept(Modblocks.RUBY_ORE.get());
                        output.accept(Modblocks.RAW_RUBY_BLOCK.get());
                        output.accept(Modblocks.RUBY_BLOCK.get());
                        output.accept(Modblocks.SOUND_BLOCK.get());


                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
