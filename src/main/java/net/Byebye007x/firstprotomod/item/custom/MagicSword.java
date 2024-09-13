package net.Byebye007x.firstprotomod.item.custom;

import net.Byebye007x.firstprotomod.client.ClientMpData;
import net.Byebye007x.firstprotomod.item.ModItems;
import net.Byebye007x.firstprotomod.magic.PlayerMagicProvider;
import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.Byebye007x.firstprotomod.networking.packet.MagicDataSyncC2SPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class MagicSword extends SwordItem {
    private final int tempManaIn;
    private boolean hasAppliedManaBoost = false;
    private int baseMaxMp = 0;

    public MagicSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, int manaIncrease) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.tempManaIn = manaIncrease;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();

            if (!pLevel.isClientSide && player instanceof ServerPlayer sPlayer) {
                player.getCapability(PlayerMagicProvider.PLAYER_MP).ifPresent(playerMagic -> {
                    int currentMaxMp = playerMagic.getMAX_MP();

                    if (mainHandItem.getItem() == ModItems.SACRED_SWORD.get()) {
                        if (!hasAppliedManaBoost) {
                            baseMaxMp = currentMaxMp;
                            playerMagic.setMAX_MP(baseMaxMp + tempManaIn);
                            hasAppliedManaBoost = true;
                            ModPackages.sendToPlayer(new MagicDataSyncC2SPacket(playerMagic.getMp(), playerMagic.getMAX_MP(), playerMagic.getMpRegen()), sPlayer);
                        }

                    } else if (hasAppliedManaBoost) {
                        playerMagic.setMAX_MP(baseMaxMp);
                        hasAppliedManaBoost = false;

                        ModPackages.sendToPlayer(new MagicDataSyncC2SPacket(playerMagic.getMp(), playerMagic.getMAX_MP(), playerMagic.getMpRegen()), sPlayer);
                    }
                });
            }
        }
    }
}

