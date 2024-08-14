package net.Byebye007x.firstprotomod.item.custom;

import net.Byebye007x.firstprotomod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PloyScythe extends SwordItem {
    public PloyScythe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        ItemStack mainHandItem = player.getMainHandItem();
        // Get the current Damage Boost effect
        MobEffectInstance damageBoostEffect = player.getEffect(MobEffects.DAMAGE_BOOST);

        // Check if the player has Damage Boost level 2
        boolean hasDamageBoostLevel2 = damageBoostEffect != null && damageBoostEffect.getAmplifier() == 1 && damageBoostEffect.getDuration() > 10000;

        if(!level.isClientSide()) {
            if (isHoldingItem(mainHandItem, ModItems.PLOY_SCYTHE.get())) {
                if (!hasDamageBoostLevel2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 1,
                            false, false, true));
                }
            } else {
                if (hasDamageBoostLevel2) {
                    player.removeEffect(MobEffects.DAMAGE_BOOST);
                }

            }
        }
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