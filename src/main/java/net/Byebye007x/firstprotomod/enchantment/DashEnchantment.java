package net.Byebye007x.firstprotomod.enchantment;

import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;

import java.util.Map;

public class DashEnchantment extends Enchantment {
    protected DashEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.ARMOR_FEET, pApplicableSlots);
    }

    public static void doDash(Player player, int pLevel) {
            Vec3 lookVec = player.getLookAngle();
            double dashSpeed = 1.5 * pLevel; // Example dash speed

            player.setDeltaMovement(
                    lookVec.x * dashSpeed,
                    player.getDeltaMovement().y + 0.1f,
                    lookVec.z * dashSpeed
            );
            player.getCooldowns().addCooldown(player.getItemBySlot(EquipmentSlot.FEET).getItem(), 100);
    }

    public static boolean canDash(Player player) {
        int level = DashEnchantment.getExistingLevel(player);
        return level > 0 && player.onGround() && !player.getCooldowns().isOnCooldown(player.getItemBySlot(EquipmentSlot.FEET).getItem());
    }

    public static int getExistingLevel (Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(boots);

        if (enchantments.containsKey(ModEnchantments.DASH.get())) {
            return enchantments.get(ModEnchantments.DASH.get());
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
