package net.Byebye007x.firstprotomod.datagen.loot;

import net.Byebye007x.firstprotomod.block.Modblocks;
import net.Byebye007x.firstprotomod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(Modblocks.RUBY_BLOCK.get());
        this.dropSelf(Modblocks.RAW_RUBY_BLOCK.get());
        this.dropSelf(Modblocks.SOUND_BLOCK.get());



        this.dropSelf(Modblocks.RUBY_STAIR.get());
        this.dropSelf(Modblocks.RUBY_BUTTON.get());
        this.dropSelf(Modblocks.RUBY_PRESSURE_PLATE.get());
        this.dropSelf(Modblocks.RUBY_FENCE.get());
        this.dropSelf(Modblocks.RUBY_FENCE_GATE.get());
        this.dropSelf(Modblocks.RUBY_TRAP_DOOR.get());
        this.dropSelf(Modblocks.RUBY_WALL.get());

        this.add(Modblocks.RUBY_ORE.get(),
                block -> createCopperLikeOreDrops(Modblocks.RUBY_ORE.get(), ModItems.RAW_RUBY.get()));

        this.add(Modblocks.RUBY_SLAB.get(),
                block -> createSlabItemTable(Modblocks.RUBY_SLAB.get()));
        this.add(Modblocks.RUBY_DOOR.get(),
                block -> createDoorTable(Modblocks.RUBY_DOOR.get()));
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Modblocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
