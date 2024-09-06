package net.Byebye007x.firstprotomod.enchantment;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ProtoMod.MOD_ID);

    public static RegistryObject<Enchantment> LIGHTNING_STRIKER =
            ENCHANTMENTS.register("lightning_striker",
                    () -> new LightningStrikerEnchantment(Enchantment.Rarity.UNCOMMON,
                            EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));
    public static RegistryObject<Enchantment> DASH =
            ENCHANTMENTS.register("dash",
                    () -> new DashEnchantment(Enchantment.Rarity.UNCOMMON,
                            EquipmentSlot.FEET));
    public static RegistryObject<Enchantment> CLOUD_STEP =
            ENCHANTMENTS.register("cloud_step",
                    () -> new CloudStepEnchantment(Enchantment.Rarity.UNCOMMON,
                            EquipmentSlot.FEET));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
