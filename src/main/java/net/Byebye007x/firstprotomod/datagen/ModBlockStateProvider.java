package net.Byebye007x.firstprotomod.datagen;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.block.ModBlocks;
import net.Byebye007x.firstprotomod.block.custom.StrawberryCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ProtoMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.RUBY_BLOCK);
        blockWithItem(ModBlocks.RAW_RUBY_BLOCK);
        blockWithItem(ModBlocks.RUBY_ORE);
        blockWithItem(ModBlocks.SOUND_BLOCK);

        stairsBlock(((StairBlock) ModBlocks.RUBY_STAIR.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.RUBY_SLAB.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.RUBY_BUTTON.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.RUBY_PRESSURE_PLATE.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));
        fenceBlock(((FenceBlock) ModBlocks.RUBY_FENCE.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.RUBY_FENCE_GATE.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.RUBY_DOOR.get()), modLoc("block/ruby_door_bottom"), modLoc("block/ruby_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.RUBY_TRAP_DOOR.get()), modLoc("block/ruby_trapdoor"), true, "cutout");

        makeStrawberryCrop((CropBlock) ModBlocks.STRAWBERRY_CROP.get(), "strawberry_stage", "strawberry_stage");

        simpleBlockWithItem(ModBlocks.GEM_POLISHING_TABLE.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gem_polishing_table")));
    }

    public void makeStrawberryCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()),
                new ResourceLocation(ProtoMod.MOD_ID, "block/" + textureName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
