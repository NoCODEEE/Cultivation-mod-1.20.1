package net.Byebye007x.firstprotomod.networking.packet;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SPacket {
    public C2SPacket() {

    }

    public C2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // ON the server
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel().getLevel();

            EntityType.COW.spawn(level, (CompoundTag) null, null,
                    player.blockPosition(), MobSpawnType.COMMAND, true, false);
        });
        return true;
    }

}
