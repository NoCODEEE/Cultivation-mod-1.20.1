package net.Byebye007x.firstprotomod.item.custom;

import net.Byebye007x.firstprotomod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class PloyScythe extends SwordItem {
    public PloyScythe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (!level.isClientSide()) {
            boolean isHolding = isHoldingItem(player.getMainHandItem(), ModItems.PLOY_SCYTHE.get()) ||
                    isHoldingItem(player.getOffhandItem(), ModItems.PLOY_SCYTHE.get());

            MobEffectInstance Effect = player.getEffect(MobEffects.DAMAGE_BOOST);

            if (isHolding) {
                if (Effect == null || Effect.getAmplifier() != 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 1, false, false, true));
                }
            } else {
                if (Effect != null && Effect.getAmplifier() == 1) {
                    player.removeEffect(MobEffects.DAMAGE_BOOST);
                }
            }
        }
    }


    public boolean hasInHotbar(Player player, Item item) {

            for (int i = 0; i < 9; i++) {
                ItemStack stack = player.getInventory().getItem(i);
                if (stack.is(item)) {
                    return true;
                }
            }
            return false;

    }


    private boolean isHoldingItem(ItemStack stack, Item item) {
        return stack.getItem() == item;
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        // Add a fancy text with color and bold formatting using translatable
        pTooltipComponents.add(Component.translatable("tooltip.firstprotomod.ploy_scythe1")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC));

        pTooltipComponents.add(Component.translatable("tooltip.firstprotomod.ploy_scythe2")
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}