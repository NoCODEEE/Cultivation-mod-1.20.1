package net.Byebye007x.firstprotomod;

import com.mojang.logging.LogUtils;
import net.Byebye007x.firstprotomod.block.ModBlocks;
import net.Byebye007x.firstprotomod.block.entity.ModBlockEntities;
import net.Byebye007x.firstprotomod.effect.ModEffects;
import net.Byebye007x.firstprotomod.entity.ModEntities;
import net.Byebye007x.firstprotomod.entity.client.GFRenderer;
import net.Byebye007x.firstprotomod.entity.client.RhinoRenderer;
import net.Byebye007x.firstprotomod.item.ModCreativeModetab;
import net.Byebye007x.firstprotomod.item.ModItems;
import net.Byebye007x.firstprotomod.loot.ModLootModifiers;
import net.Byebye007x.firstprotomod.screen.GemPolishingTableScreen;
import net.Byebye007x.firstprotomod.screen.ModMenuTypes;
import net.Byebye007x.firstprotomod.sound.ModSounds;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ProtoMod.MOD_ID)
public class ProtoMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "firstprotomod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    public ProtoMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModetab.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModLootModifiers.register((modEventBus));
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModEffects.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);


        // Register the commonSetup method for mod loading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RUBY);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

        // Do something when the server starts
//        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.RHINO.get(), RhinoRenderer::new);
            EntityRenderers.register(ModEntities.GF.get(), GFRenderer::new);

            MenuScreens.register(ModMenuTypes.GEM_POLISHING_MENU.get(), GemPolishingTableScreen::new);

        }
    }
}
