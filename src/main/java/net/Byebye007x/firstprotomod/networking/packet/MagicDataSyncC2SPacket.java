package net.Byebye007x.firstprotomod.networking.packet;

import net.Byebye007x.firstprotomod.client.ClientMpData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MagicDataSyncC2SPacket {
    private final int MP;
    private final int MAX_MP;
    private final int MP_REGEN;

    public MagicDataSyncC2SPacket(int MP, int MAX_MP, int MP_REGEN) {
        this.MP = MP;
        this.MAX_MP = MAX_MP;
        this.MP_REGEN = MP_REGEN;
    }

    public MagicDataSyncC2SPacket(FriendlyByteBuf buf) {
        this.MP = buf.readInt();
        this.MAX_MP = buf.readInt();
        this.MP_REGEN = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(MP);
        buf.writeInt(MAX_MP);
        buf.writeInt(MP_REGEN);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // ON the client
//            System.out.println("MP: " + MP + " / " + MAX_MP + " / " + MP_REGEN + "\n");
            ClientMpData.setPlayerMp(MP, MAX_MP, MP_REGEN);

        });
        return true;
    }

}
