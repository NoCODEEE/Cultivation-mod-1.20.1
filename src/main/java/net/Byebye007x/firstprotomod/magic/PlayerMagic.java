package net.Byebye007x.firstprotomod.magic;

import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.Byebye007x.firstprotomod.networking.packet.MagicDataSyncC2SPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PlayerMagic {
    private int mp;
    private int MAX_MP = 10;
    private final int MIN_MP = 0;
    private int mp_regen = 1;


    public void setMp(int newMp) {
        this.mp = newMp;
    }

    public void setMAX_MP(int newMaxMp) {
        this.MAX_MP = newMaxMp;
    }

    public void setMpRegen(int newMpRegen) {
        this.mp_regen = newMpRegen;
    }

    public int getMp() {
        return mp;
    }

    public int getMAX_MP() {
        return MAX_MP;
    }

    public int getMpRegen() {
        return mp_regen;
    }

    public void addMp(int add) {
        this.mp = Math.min(mp + add, MAX_MP);
    }

    public void subMp(int sub) {
        this.mp = Math.max(mp - sub, MIN_MP);
    }

    public void addMaxMp(int add) {
        this.MAX_MP += add;
    }

    public static void autoRegen(ServerPlayer player) {
        if(!player.level().isClientSide()) {
            player.getCapability(PlayerMagicProvider.PLAYER_MP).ifPresent(playerMagic -> {
                int maxMp = playerMagic.getMAX_MP();
                int mp = playerMagic.getMp();

                if (mp < maxMp) {
                    playerMagic.addMp(playerMagic.getMpRegen());
                }
                ModPackages.sendToPlayer(new MagicDataSyncC2SPacket(playerMagic.getMp(), playerMagic.getMAX_MP(), playerMagic.getMpRegen()), player);

            });
        }
    }

    public void copyFrom(PlayerMagic source) {
        this.mp = source.mp;
        this.MAX_MP = source.MAX_MP;
        this.mp_regen = source.mp_regen;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("mp", mp);
        nbt.putInt("max_mp", MAX_MP);
        nbt.putInt("mp_regen", mp_regen);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.mp = nbt.getInt("mp");
        this.MAX_MP = nbt.getInt("max_mp");
        this.mp_regen = nbt.getInt("mp_regen");
    }
}
