package net.Byebye007x.firstprotomod.screen.ManaManager;

import net.Byebye007x.firstprotomod.screen.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ManaManagerMenu extends AbstractContainerMenu {


    public ManaManagerMenu(@Nullable MenuType<?> pMenuType, int pContainerId, Level level) {
        super(pMenuType, pContainerId);
    }

    public ManaManagerMenu(int id, Inventory inv, ContainerLevelAccess access) {
        super(ModMenuTypes.MANA_MANAGER_MENU.get(), id);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
