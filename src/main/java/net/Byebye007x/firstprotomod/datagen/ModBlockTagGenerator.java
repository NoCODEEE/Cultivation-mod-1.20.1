package net.Byebye007x.firstprotomod.datagen;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.block.Modblocks;
import net.Byebye007x.firstprotomod.item.ModToolTiers;
import net.Byebye007x.firstprotomod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ProtoMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.VALUABLES)
                .add(Modblocks.RUBY_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Modblocks.RUBY_ORE.get(),
                    Modblocks.RUBY_BLOCK.get(),
                    Modblocks.RAW_RUBY_BLOCK.get(),
                    Modblocks.SOUND_BLOCK.get()
                );

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(Modblocks.SOUND_BLOCK.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Modblocks.RUBY_ORE.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(Modblocks.RUBY_BLOCK.get(),
                        Modblocks.RAW_RUBY_BLOCK.get()
                );

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        this.tag(ModTags.Blocks.NEEDS_CUSTOM_TOOL);


        this.tag(BlockTags.FENCES)
                .add(Modblocks.RUBY_FENCE.get()
                );
        this.tag(BlockTags.FENCE_GATES)
                .add(Modblocks.RUBY_FENCE_GATE.get()
                );
        this.tag(BlockTags.WALLS)
                .add(Modblocks.RUBY_WALL.get()
                );
    }
}
