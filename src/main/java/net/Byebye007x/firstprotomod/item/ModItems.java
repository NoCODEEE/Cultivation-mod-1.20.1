package net.Byebye007x.firstprotomod.item;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.block.ModBlocks;
import net.Byebye007x.firstprotomod.entity.ModEntities;
import net.Byebye007x.firstprotomod.item.custom.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems extends Items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProtoMod.MOD_ID);

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_RUBY = ITEMS.register("raw_ruby",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAINBOW_ORB = ITEMS.register("rainbow_orb",
            () -> new ManaManager(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            () -> new FuelItem(new Item.Properties(), 400));

    public static final RegistryObject<Item> TEST_HEART = ITEMS.register("test_heart",
            () -> new FuelItem(new Item.Properties().food(ModFoods.SPEED_FOOD1), 800));

    public static final RegistryObject<Item> MAGIC_FRUIT = ITEMS.register("magic_fruit",
            () -> new MagicFood(new Item.Properties().food(ModFoods.MAGIC_FRUIT)));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.SPEED_FOOD1)));
    public static final RegistryObject<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.STRAWBERRY_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> RHINO_SPAWN_EGG = ITEMS.register("rhino_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.RHINO,0x7e9680, 0xc5d1c5, new Item.Properties()));

    public static final RegistryObject<Item> COOL_SWORD = ITEMS.register("cool_sword",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TEST_SWORD = ITEMS.register("test_sword",
            () -> new CustomSword(ModToolTiers.RUBY, 996, -3.0F, new Item.Properties()
                    .rarity(Rarity.RARE)) {
                @Override
                public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
                    super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
                    stack.getOrCreateTag().putBoolean("Unbreakable", true);
                }
            });

    public static final RegistryObject<Item> PLOY_SCYTHE = ITEMS.register("ploy_scythe",
            () -> new PloyScythe(ModToolTiers.RUBY, 15, -1.0F, new Item.Properties()
                    .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> SACRED_SWORD = ITEMS.register("sacred_sword",
            () -> new MagicSword(ModToolTiers.RUBY, 4, 0, new Item.Properties(), 20));

    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword",
            () -> new SwordItem(ModToolTiers.RUBY, 4, 0, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe",
            () -> new PickaxeItem(ModToolTiers.RUBY, 2, 1, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe",
            () -> new AxeItem(ModToolTiers.RUBY, 7, -1, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel",
            () -> new ShovelItem(ModToolTiers.RUBY, 1, 2, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe",
            () -> new HoeItem(ModToolTiers.RUBY, 0, 2, new Item.Properties()));

    public static final RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_helmet",
            () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate",
            () -> new GiveEffectArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings",
            () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_BOOTS = ITEMS.register("ruby_boots",
            () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> MAGIC_HELM = ITEMS.register("magic_helm",
            () -> new MagicArmorItem(ModArmorMaterials.MAGIC_CLOTH, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_ROBE = ITEMS.register("magic_robe",
            () -> new MagicArmorItem(ModArmorMaterials.MAGIC_CLOTH, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_PANTS = ITEMS.register("magic_pants",
            () -> new MagicArmorItem(ModArmorMaterials.MAGIC_CLOTH, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_BOOTS = ITEMS.register("magic_boots",
            () -> new MagicArmorItem(ModArmorMaterials.MAGIC_CLOTH, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
