package net.Byebye007x.firstprotomod.datagen;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ProtoMod.MOD_ID, existingFileHelper);
    }

    // register model
    @Override
    protected void registerModels() {
        normalItem(ModItems.RUBY);
        normalItem(ModItems.RAW_RUBY);
        normalItem(ModItems.PINE_CONE);
        normalItem(ModItems.STRAWBERRY);
        normalItem(ModItems.TEST_HEART);
    }

    // normal item
    private ItemModelBuilder normalItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ProtoMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
