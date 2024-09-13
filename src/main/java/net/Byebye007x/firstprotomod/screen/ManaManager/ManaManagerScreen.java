package net.Byebye007x.firstprotomod.screen.ManaManager;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Byebye007x.firstprotomod.ProtoMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ManaManagerScreen extends AbstractContainerScreen<ManaManagerMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ProtoMod.MOD_ID, "textures/gui/mana_manager_gui.png");

    public ManaManagerScreen(ManaManagerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelY = 10;

        Button addMana = Button.builder(Component.literal("Add mana"),
                        button -> { System.out.println("Button clicked!"); })
                .bounds(this.leftPos + 50, this.topPos + 20, 100, 20)
                .build();

        this.addRenderableWidget(addMana);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }
}
