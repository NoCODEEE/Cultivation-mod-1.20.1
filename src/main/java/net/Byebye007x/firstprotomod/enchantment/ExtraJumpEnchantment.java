package net.Byebye007x.firstprotomod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

public class ExtraJumpEnchantment extends Enchantment {
    protected ExtraJumpEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.ARMOR_FEET, pApplicableSlots);
    }

    public void doExtraJump (LivingEntity pEntity, int pLevel) {

    }



    @Override
    public int getMaxLevel() {
        return 2;
    }
}
