package net.Byebye007x.firstprotomod.entity.client.armor;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.example.item.GeckoArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ClothArmorRenderer extends GeoArmorRenderer<GeckoArmorItem> {
    public ClothArmorRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(ProtoMod.MOD_ID, "armor/cloth_armor")));
    }
}
