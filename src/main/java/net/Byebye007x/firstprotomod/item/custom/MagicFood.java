package net.Byebye007x.firstprotomod.item.custom;

import net.Byebye007x.firstprotomod.magic.PlayerMagicProvider;
import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.Byebye007x.firstprotomod.networking.packet.MagicDataSyncC2SPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MagicFood extends Item {
    private static final int addMp = 10;
    private static final int addMaxMp = 3;
    private static final int addMpRegen = 1;

    public MagicFood(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        ItemStack itemStack = super.finishUsingItem(pStack, pLevel, pLivingEntity);

        // Check if the world is not remote, ensuring this runs on the server side
        if (!pLevel.isClientSide) {
            if (pLivingEntity instanceof ServerPlayer player) {
                player.getCapability(PlayerMagicProvider.PLAYER_MP).ifPresent(playerMagic -> {
                    playerMagic.addMp(addMp);
                    playerMagic.addMaxMp(addMaxMp);
                    playerMagic.setMpRegen(playerMagic.getMpRegen() + addMpRegen);
                    ModPackages.sendToPlayer(new MagicDataSyncC2SPacket(playerMagic.getMp(), playerMagic.getMAX_MP(), playerMagic.getMpRegen()), player);
                });
            }
        }
        return itemStack;
    }
}