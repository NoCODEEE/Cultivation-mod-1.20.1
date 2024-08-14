package net.Byebye007x.firstprotomod.item.custom;

import net.Byebye007x.firstprotomod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingSwapItemsEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CustomSword extends SwordItem {
    public CustomSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide) {
            int numArrows = ThreadLocalRandom.current().nextInt(1, 5);

            for (int i = 0; i <= numArrows; i++){
                Arrow arrow = new Arrow(pLevel, pPlayer);
                arrow.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 5.0f, 1.0F);
                arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

                pLevel.addFreshEntity(arrow);
                pLevel.playSeededSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                        ModSounds.CUSTOM_SWORD_SWING.get(), SoundSource.PLAYERS, 1.0F, 1.0F, 0);


                itemstack.hurtAndBreak(1, pPlayer, (p) -> {
                    p.broadcastBreakEvent(pUsedHand);
                });
            }
        }

        pPlayer.swing(pUsedHand);
        pPlayer.getCooldowns().addCooldown(this, 20); // 1 second cooldown

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        // Add a fancy text with color and bold formatting using translatable
        pTooltipComponents.add(Component.translatable("tooltip.firstprotomod.test_sword.1")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));

        // Add italic text with a custom color using translatable
        pTooltipComponents.add(Component.translatable("tooltip.firstprotomod.test_sword.2")
                .withStyle(style -> style.withItalic(true).withColor(TextColor.fromRgb(0x00FF00)))); // Green color

        // Add text with multiple formatting options using translatable
        pTooltipComponents.add(Component.translatable("tooltip.firstprotomod.test_sword.3")
                .withStyle(ChatFormatting.AQUA, ChatFormatting.ITALIC, ChatFormatting.UNDERLINE));

        // Add a text with custom RGB color and strikethrough using translatable
        pTooltipComponents.add(Component.translatable("tooltip.firstprotomod.test_sword.4")
                .withStyle(style -> style.withColor(TextColor.fromRgb(0xFF5555)) // Red color
                        .withStrikethrough(true)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isFireResistant() {
        return super.isFireResistant();
    }
}
