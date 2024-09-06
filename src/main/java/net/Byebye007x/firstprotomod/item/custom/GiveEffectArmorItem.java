package net.Byebye007x.firstprotomod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.Byebye007x.firstprotomod.item.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Map;

public class GiveEffectArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<MobEffectInstance>>())
                    .put(ModArmorMaterials.RUBY, List.of(
                            new MobEffectInstance(MobEffects.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false, true),
                            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1, false, false, true)
                    )).build();

    public GiveEffectArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (!level.isClientSide()) {
            if (hasFullSuitOfArmorOn(player)) {
                applyArmorEffects(player);
            } else {
                clearArmorEffects(player);
            }
        }
    }

    private void applyArmorEffects(Player player) {
        ArmorMaterial armorMaterial = getArmorMaterial(player);
        if (MATERIAL_TO_EFFECT_MAP.containsKey(armorMaterial)) {
            List<MobEffectInstance> effects = MATERIAL_TO_EFFECT_MAP.get(armorMaterial);
            for (MobEffectInstance effect : effects) {
                if (!player.hasEffect(effect.getEffect())) {
                    player.addEffect(new MobEffectInstance(effect));
                }
            }
        }
    }

    private void clearArmorEffects(Player player) {
        ArmorMaterial armorMaterial = getArmorMaterial(player);
        if (MATERIAL_TO_EFFECT_MAP.containsKey(armorMaterial)) {
            List<MobEffectInstance> effects = MATERIAL_TO_EFFECT_MAP.get(armorMaterial);
            for (MobEffectInstance effect : effects) {
                if (player.hasEffect(effect.getEffect())) {
                    player.removeEffect(effect.getEffect());
                }
            }
        }
    }

    private ArmorMaterial getArmorMaterial(Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        if (boots.getItem() instanceof ArmorItem armorItem) {
            return armorItem.getMaterial();
        }
        return null;
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

        return boots.getItem() instanceof ArmorItem && ((ArmorItem) boots.getItem()).getMaterial() == material &&
                leggings.getItem() instanceof ArmorItem && ((ArmorItem) leggings.getItem()).getMaterial() == material &&
                chestplate.getItem() instanceof ArmorItem && ((ArmorItem) chestplate.getItem()).getMaterial() == material &&
                helmet.getItem() instanceof ArmorItem && ((ArmorItem) helmet.getItem()).getMaterial() == material;
    }
}


