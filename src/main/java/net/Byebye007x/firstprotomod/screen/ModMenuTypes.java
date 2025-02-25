package net.Byebye007x.firstprotomod.screen;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.screen.GemPolishingTable.GemPolishingTableMenu;
import net.Byebye007x.firstprotomod.screen.ManaManager.ManaManagerMenu;
import net.Byebye007x.firstprotomod.screen.ManaManager.ManaManagerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ProtoMod.MOD_ID);

    public static final RegistryObject<MenuType<GemPolishingTableMenu>> GEM_POLISHING_MENU =
            registerMenuType(GemPolishingTableMenu::new);
    public static final RegistryObject<MenuType<ManaManagerMenu>> MANA_MANAGER_MENU = MENUS.register("mana_manager_menu",
            () -> IForgeMenuType.create((id, inv, data) -> new ManaManagerMenu(id, inv, ContainerLevelAccess.NULL)));

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory) {
        return MENUS.register("gem_polishing_menu",() -> IForgeMenuType.create(factory));
    }


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
