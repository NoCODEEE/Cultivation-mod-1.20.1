package net.Byebye007x.firstprotomod.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class Flight extends MobEffect {
    public Flight(MobEffectCategory pCategory) {
        super(pCategory, 0xFFFFFF);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Player player = (Player) entity;
        player.getAbilities().mayfly = true;
        player.onUpdateAbilities(); // Update the player's abilities
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof Player) {
            Player player = (Player) pLivingEntity;
            if (!player.isCreative() && !player.isSpectator()) {
                player.getAbilities().mayfly = false; // Remove the ability to fly
                player.getAbilities().flying = false; // Stop flying if they were
                player.onUpdateAbilities(); // Update the player's abilities
            }
        }
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
