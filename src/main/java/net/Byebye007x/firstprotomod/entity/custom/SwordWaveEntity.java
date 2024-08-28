package net.Byebye007x.firstprotomod.entity.custom;


import net.Byebye007x.firstprotomod.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;



public class SwordWaveEntity extends AbstractHurtingProjectile {

    private static int life_time_ticks = 200;

    public SwordWaveEntity(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SwordWaveEntity(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ) {
        super(ModEntities.SWORD_WAVE.get(), pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
    }

//    @Override
//    public void tick() {
//        super.tick();
//    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity target = pResult.getEntity();
        Entity attacker = this.getOwner();

        if (target != this.getOwner()) {
            target.hurt(damageSources().indirectMagic(this, attacker), 6.0F);

            this.discard();
        }

    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        if (!this.level().isClientSide) {
            BlockPos blockPos = pResult.getBlockPos();
            BlockState blockState = this.level().getBlockState(blockPos);

            if (canDestroyBlock(blockState)) {
                this.level().destroyBlock(blockPos, true);
            }
            this.discard();
        }
    }

    private boolean canDestroyBlock(BlockState blockState) {
        // Only destroy blocks that aren't air and aren't unbreakable
        return !blockState.isAir() && blockState.getDestroySpeed(level(), blockPosition()) >= 0;
    }

    @Override
    protected float getInertia() {
        return 1.0f;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

}
