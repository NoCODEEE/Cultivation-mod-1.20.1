package net.Byebye007x.firstprotomod.util;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> VALUABLES = tag("valuable_resource");
        public static final TagKey<Block> NEEDS_CUSTOM_TOOL = tag("needs_custom_tool");



        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ProtoMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SHARP = tag("sharp_tools");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ProtoMod.MOD_ID, name));
        }
    }
}
