package net.Byebye007x.firstprotomod.item;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier RUBY = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2000, 10f, 4f, 25,
                    ModTags.Blocks.NEEDS_CUSTOM_TOOL, () -> Ingredient.of(ModItems.RUBY.get())),
            new ResourceLocation(ProtoMod.MOD_ID, "ruby"), List.of(Tiers.NETHERITE), List.of());
}
