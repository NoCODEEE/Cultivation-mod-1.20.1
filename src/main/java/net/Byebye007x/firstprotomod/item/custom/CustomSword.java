package net.Byebye007x.firstprotomod.item.custom;

import net.Byebye007x.firstprotomod.enchantment.DashEnchantment;
import net.Byebye007x.firstprotomod.enchantment.ModEnchantments;
import net.Byebye007x.firstprotomod.entity.custom.SwordWaveEntity;
import net.Byebye007x.firstprotomod.particle.ModParticles;
import net.Byebye007x.firstprotomod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TextComponentTagVisitor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomSword extends SwordItem {
    private static final String TAG_TICK_COUNTER = "TickCounter";
    private static final String TAG_SHOTS_REMAINING = "ShotsRemaining";
    private static final int NOT_HOTBAR = 9;
    private static final int DELAY_TICKS = 2;
//    private static final int TOTAL_SHOTS = ThreadLocalRandom.current().nextInt(1, 5);
    private static final int TOTAL_SHOTS = 1;


    public CustomSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide) {

            for (int i = 0; i <= TOTAL_SHOTS; i++) {
                CompoundTag tag = itemstack.getOrCreateTag();
                if (!tag.contains(TAG_SHOTS_REMAINING)) {
                    tag.putInt(TAG_SHOTS_REMAINING, TOTAL_SHOTS);
                    tag.putInt(TAG_TICK_COUNTER, DELAY_TICKS);
                }
            }
                pLevel.playSeededSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                        ModSounds.CUSTOM_SWORD_SWING.get(), SoundSource.PLAYERS, 1.0F, 1.0F, 0);


        } else {
            spawnParticles(pLevel, pPlayer, ModParticles.GLITTER_LIGHT.get());
        }

        pPlayer.swing(pUsedHand);
        pPlayer.getCooldowns().addCooldown(this, 20); // 1 second cooldown

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
    }

    private void spawnParticles(Level pLevel, Player player, ParticleOptions Particle) {
        // Parameters: particle type, x, y, z, xSpeed, ySpeed, zSpeed
        for (int i = 0; i < 10; i++) {
            pLevel.addParticle(Particle,
                    player.getX() + (pLevel.random.nextDouble() - 0.5),
                    player.getY() + 1.5,
                    player.getZ() + (pLevel.random.nextDouble() - 0.5),
                    0.0D, 0.1D, 0.0D);
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pLevel.isClientSide && pStack.getTag() != null) {
            CompoundTag tag = pStack.getTag();
            int shotsRemaining = tag.getInt(TAG_SHOTS_REMAINING);
            int tickCounter = tag.getInt(TAG_TICK_COUNTER);

            if (shotsRemaining > 0) {
                if (tickCounter <= 0) {
                    // Shoot an arrow
                    shootEntity(pLevel, (Player) pEntity);
                    tag.putInt(TAG_SHOTS_REMAINING, shotsRemaining - 1);
                    tag.putInt(TAG_TICK_COUNTER, DELAY_TICKS); // Reset the delay counter for the next shot
                } else {
                    tag.putInt(TAG_TICK_COUNTER, tickCounter - 1);
                }
            } else {
                // Reset the tags after shooting all arrows
                tag.remove(TAG_SHOTS_REMAINING);
                tag.remove(TAG_TICK_COUNTER);
            }
        }
    }

    private static void performDash(Player player) {

        Vec3 lookVec = player.getLookAngle();
        double dashSpeed = 1.5; // Scale speed with enchantment level

        Vec3 dashVec = new Vec3(lookVec.x * dashSpeed, 0.1, lookVec.z * dashSpeed);
        player.setDeltaMovement(dashVec);
    }

    public void shootEntity(Level pLevel, Player pPlayer) {
        SwordWaveEntity swordWave = new SwordWaveEntity(pLevel, pPlayer, 0d, 0.0d, 0d);
        swordWave.setPos(pPlayer.getX(), pPlayer.getEyeY() - 0.3f, pPlayer.getZ());
        swordWave.setYRot(pPlayer.getYRot());
        swordWave.setXRot(pPlayer.getXRot());
        swordWave.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0f, 1.5f, 1.0F);
        pLevel.addFreshEntity(swordWave);
    }
//        arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

    public int whereInHotbar(Player player, Item item) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.is(item)) {
                return i;
            }
        }
        return NOT_HOTBAR;
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
