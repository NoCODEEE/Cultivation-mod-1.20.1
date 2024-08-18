package net.Byebye007x.firstprotomod.entity.custom;

import net.Byebye007x.firstprotomod.entity.ModEntities;
import net.Byebye007x.firstprotomod.entity.ai.GfAttackGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class GFEntity extends TamableAnimal{
    private static final EntityDataAccessor<Boolean> ATTACKING_GF =
            SynchedEntityData.defineId(GFEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID =
            SynchedEntityData.defineId(GFEntity.class, EntityDataSerializers.BYTE);
    protected static final EntityDataAccessor<Optional<UUID>> GF_IS_MINE =
            SynchedEntityData.defineId(GFEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(GFEntity.class, EntityDataSerializers.INT);
    private static ItemStack heldItem = ItemStack.EMPTY;

    public GFEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setTame(false);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;


//    @Override
//    public void tick() {
//        super.tick();
//
//        if(this.level().isClientSide()) {
//            setupAnimationStates();
//        }
//    }
//    private void setupAnimationStates() {
//        if (isInSittingPose()) {
////            if  (this.idleAnimationTimeout <= 0) {
////                this.idleAnimationTimeout = this.random.nextInt(40) + 80;
////                this.idleAnimationState.start(this.tickCount);
////            } else {
////                --this.idleAnimationTimeout;
////                }
//            System.out.print('A');
//        }
//        if (this.isAttacking() && attackAnimationTimeout <= 0) {
//            attackAnimationTimeout = 6;
//            attackAnimationState.start(this.tickCount);
//        } else {
//            --this.attackAnimationTimeout;
//        }
//
//        if (!this.isAttacking()) {
//            attackAnimationState.stop();
//        }
//    }
//
//    @Override
//    protected void updateWalkAnimation(float pPartialTick) {
//        float f;
//        if(this.getPose() == Pose.STANDING) {
//            f = Math.min(pPartialTick * 6F, 1f);
//        } else {
//            f = 0f;
//        }
//
//        this.walkAnimation.update(f, 0.2f);
//    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING_GF, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING_GF);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING_GF, false);
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(GF_IS_MINE, Optional.empty());
        this.entityData.define(VARIANT, 0);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new GfAttackGoal(this, 2.0d, true));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.15d));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class,3f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20d)
                .add(Attributes.MOVEMENT_SPEED, 0.2d)
                .add(Attributes.ATTACK_DAMAGE, 3d)
                .add(Attributes.FOLLOW_RANGE, 20d)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5d);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.GF.get().create(pLevel);
    }

    public boolean isFood(ItemStack pStack) {
        Item item = pStack.getItem();
        return item.isEdible() && !pStack.is(Items.ROTTEN_FLESH) ;
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || itemstack.is(Items.DIAMOND) && !this.isTame() && !this.isAttacking();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            } else {
                InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
                if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(pPlayer)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    return InteractionResult.SUCCESS;
                } else {
                    return interactionresult;
                }
            }
        }
         else if (itemstack.is(Items.DIAMOND) && !this.isAttacking()) {
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                this.tame(pPlayer);
                this.navigation.stop();
                this.setTarget((LivingEntity)null);
                this.setOrderedToSit(true);
                this.level().broadcastEntityEvent(this, (byte)7);
            } else {
                this.level().broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        ItemStack playerItem = pPlayer.getItemInHand(pHand);
        MobEffectInstance damageBoostEffect = pPlayer.getEffect(MobEffects.POISON);
        boolean hasDamageBoostLevel2 = damageBoostEffect != null && damageBoostEffect.getAmplifier() == 1
                && damageBoostEffect.getDuration() > 10000;

        if (pPlayer.isShiftKeyDown() && this.isOwnedBy(pPlayer)) {
            if (!this.level().isClientSide) {
                if (heldItem.isEmpty() && !playerItem.isEmpty()) {
                    // Give item to the entity
                    heldItem = playerItem.copy();
                    pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                    this.setItemSlot(EquipmentSlot.MAINHAND, heldItem); // Update rendering
                    if (hasDamageBoostLevel2) {
                        pPlayer.removeEffect(MobEffects.POISON);
                    }
                } else if (!heldItem.isEmpty()) {
                    // Take the item from the entity
                    pPlayer.addItem(heldItem);
                    heldItem = ItemStack.EMPTY;
                    this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY); // Update rendering
                }
            }
            return InteractionResult.SUCCESS;
        }

        return super.interactAt(pPlayer, pVec, pHand);
    }

    public int getVariant() {
        return entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        pSpawnData = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);

        int variant = this.random.nextInt(7);
        this.setVariant(variant);
        return pSpawnData;
    }

    // Save the entity's held item when saving the entity
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (!heldItem.isEmpty()) {
            pCompound.put("HeldItem", heldItem.save(new CompoundTag()));
        }
    }

    // Load the entity's held item when loading the entity
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("HeldItem")) {
            heldItem = ItemStack.of(pCompound.getCompound("HeldItem"));
            this.setItemSlot(EquipmentSlot.MAINHAND, heldItem); // Update rendering
        }
    }

    @Override
    public InteractionHand getUsedItemHand() {
        return InteractionHand.MAIN_HAND; // Main hand or offhand
    }

    // Method to set the held item
    public void setHeldItem(ItemStack stack) {
        this.setItemInHand(InteractionHand.MAIN_HAND, stack); // Set item in main hand
    }

    // Method to get the held item
    public ItemStack getHeldItem() {
        return this.getItemInHand(InteractionHand.MAIN_HAND);
    }

    // Ensure the item is visually rendered in the entity's hand
//    @Override
//    public void aiStep() {
//        super.aiStep();
//        if (!this.level().isClientSide && this.tickCount % 20 == 0) {
//            this.setHeldItem(new ItemStack(Items.IRON_SWORD));
//        }
//    }

}
