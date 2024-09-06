package net.Byebye007x.firstprotomod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;

import java.util.Map;

public class CloudStepEnchantment extends Enchantment {
    public static int availableJump;

    protected CloudStepEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.ARMOR_FEET, pApplicableSlots);
    }

    public static void doJump(Player player) {
            Vec3 lookVec = player.getLookAngle();
            double dashSpeed = 0.65d + (double) (availableJump - 1) / 10;

                player.setDeltaMovement(
                        player.getDeltaMovement().x,
                        player.getDeltaMovement().y + dashSpeed,
                        player.getDeltaMovement().z
                );
                availableJump--;
    }

    public static boolean canJump(Player player) {
        int level = CloudStepEnchantment.getExistingLevel(player);
        return level > 0 && !player.onGround() && availableJump > 0;
    }


    public static int getExistingLevel (Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(boots);

        if (enchantments.containsKey(ModEnchantments.CLOUD_STEP.get())) {
            return enchantments.get(ModEnchantments.CLOUD_STEP.get());
        }

        return 0;
    }


    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getEquipmentSlot() == EquipmentSlot.FEET;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getEquipmentSlot() == EquipmentSlot.FEET;
    }
}
