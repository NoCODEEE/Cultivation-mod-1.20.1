package net.Byebye007x.firstprotomod.networking.packet;

import net.Byebye007x.firstprotomod.entity.custom.SwordWaveEntity;
import net.Byebye007x.firstprotomod.magic.PlayerMagicProvider;
import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.Byebye007x.firstprotomod.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UseMagicC2SPacket {
    public UseMagicC2SPacket() {

    }

    public UseMagicC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // ON the server
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel().getLevel();




            //Use Magic
            player.getCapability(PlayerMagicProvider.PLAYER_MP).ifPresent(playerMagic -> {
                if (playerMagic.getMp() > 0) {
                    playerMagic.subMp(1);
                    SwordWaveEntity swordWave = new SwordWaveEntity(level, player,
                            0d, 0.0d, 0d);
                    swordWave.setPos(player.getX(), player.getEyeY() - 0.3f, player.getZ());
                    swordWave.setYRot(player.getYRot());
                    swordWave.setXRot(player.getXRot());
                    swordWave.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, 1.5f, 1.0F);
                    level.addFreshEntity(swordWave);
                    level.playSeededSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0F, 0);

                } else {
                    player.displayClientMessage(Component.literal("Not enough Mp"), true);
                    level.playSeededSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F, 0);
                }


                ModPackages.sendToPlayer(new MagicDataSyncC2SPacket(playerMagic.getMp(), playerMagic.getMAX_MP(), playerMagic.getMpRegen()), player);
            });



        });
        return true;
    }

}
