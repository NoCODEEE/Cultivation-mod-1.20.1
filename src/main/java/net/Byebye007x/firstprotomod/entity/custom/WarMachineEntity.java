package net.Byebye007x.firstprotomod.entity.custom;

import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidType;

public class WarMachineEntity extends LivingEntity {

    private final Level level;

    public WarMachineEntity(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.level = pLevel;
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
        return null;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }


    @Override
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
        if (!this.level.isClientSide) {
            pPlayer.startRiding(this);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean canBeRiddenUnderFluidType(FluidType type, Entity rider) {
        return true;
    }

    @Override
    protected boolean canAddPassenger(Entity pPassenger) {
        return this.getPassengers().isEmpty();
    }

    @Override
    protected void addPassenger(Entity pPassenger) {
        super.addPassenger(pPassenger);
        if (pPassenger instanceof ServerPlayer) {
            ((ServerPlayer) pPassenger).connection.send(new ClientboundSetPassengersPacket(this));
        }
    }

    @Override
    protected boolean canRide(Entity pVehicle) {
        return super.canRide(pVehicle);
    }

    @Override
    protected void positionRider(Entity pPassenger, MoveFunction pCallback) {
        super.positionRider(pPassenger, pCallback);
    }

    @Override
    public void tick() {
        super.tick();

        // Handle movement while the entity is being ridden
        if (this.isVehicle() && this.getControllingPassenger() instanceof Player player) {

            // Make the entity face the direction the player is looking
            this.setYRot(player.getYRot()); // Set entity's Y rotation to match the player's Y rotation
            this.yRotO = this.getYRot(); // Store the previous Y rotation

            // Synchronize head rotation (if necessary)
            this.setYHeadRot(player.getYRot());

            // Set pitch (looking up and down)
            this.setXRot(player.getXRot() * 0.5F);
        }
    }

    @Override
    public HumanoidArm getMainArm() {
        return null;
    }


}
