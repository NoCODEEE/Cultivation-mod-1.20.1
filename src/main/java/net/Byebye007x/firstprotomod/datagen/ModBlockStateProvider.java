package net.Byebye007x.firstprotomod.datagen;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.block.Modblocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ProtoMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(Modblocks.RUBY_BLOCK);
        blockWithItem(Modblocks.RAW_RUBY_BLOCK);
        blockWithItem(Modblocks.RUBY_ORE);
        blockWithItem(Modblocks.SOUND_BLOCK);

        stairsBlock(((StairBlock) Modblocks.RUBY_STAIR.get()), blockTexture(Modblocks.RUBY_BLOCK.get()));
        slabBlock(((SlabBlock) Modblocks.RUBY_SLAB.get()), blockTexture(Modblocks.RUBY_BLOCK.get()), blockTexture(Modblocks.RUBY_BLOCK.get()));

        buttonBlock(((ButtonBlock) Modblocks.RUBY_BUTTON.get()), blockTexture(Modblocks.RUBY_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) Modblocks.RUBY_PRESSURE_PLATE.get()), blockTexture(Modblocks.RUBY_BLOCK.get()));
        fenceBlock(((FenceBlock) Modblocks.RUBY_FENCE.get()), blockTexture(Modblocks.RUBY_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) Modblocks.RUBY_FENCE_GATE.get()), blockTexture(Modblocks.RUBY_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) Modblocks.RUBY_DOOR.get()), modLoc("block/ruby_door_bottom"), modLoc("block/ruby_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) Modblocks.RUBY_TRAP_DOOR.get()), modLoc("block/ruby_trapdoor"), true, "cutout");

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
