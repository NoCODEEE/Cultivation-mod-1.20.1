package net.Byebye007x.firstprotomod.item.custom;

import net.Byebye007x.firstprotomod.enchantment.DashEnchantment;
import net.Byebye007x.firstprotomod.magic.PlayerMagicProvider;
import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.Byebye007x.firstprotomod.networking.packet.MagicDataSyncC2SPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class MagicFood extends Item {
    private static final int addMp = 10;

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
                    playerMagic.setMAX_MP(playerMagic.getMAX_MP() + addMp);
                    ModPackages.sendToPlayer(new MagicDataSyncC2SPacket(playerMagic.getMp(), playerMagic.getMAX_MP(), playerMagic.getMpRegen()), player);
                });
            }
        }
        return itemStack;
    }
}