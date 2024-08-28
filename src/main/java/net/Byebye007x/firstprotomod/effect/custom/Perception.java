package net.Byebye007x.firstprotomod.effect.custom;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class Perception extends MobEffect {
    public Perception(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);

        if (!entity.level().isClientSide) {
            double radius = 10.0D + amplifier * 5.0D;
            List<LivingEntity> nearbyEntities = entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(radius));

            for (LivingEntity nearbyEntity : nearbyEntities) {
                if (nearbyEntity == entity) {
                    continue;

                }
                // Apply the glowing effect to other entities
                nearbyEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20 * (amplifier + 1), 0, false, false));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }


}
