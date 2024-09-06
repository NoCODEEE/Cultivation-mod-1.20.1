package net.Byebye007x.firstprotomod.networking.packet;

import net.Byebye007x.firstprotomod.effect.ModEffects;
import net.Byebye007x.firstprotomod.magic.PlayerMagicProvider;
import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CultivateC2SPacket {
    public CultivateC2SPacket() {

    }

    public CultivateC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // ON the server
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel().getLevel();


            //Cultivate Success
            player.getCapability(PlayerMagicProvider.PLAYER_MP).ifPresent(playerMagic -> {
                    playerMagic.addMaxMp(1);
                    player.displayClientMessage(Component.literal("Max Mana Increase"), true);

                ModPackages.sendToPlayer(new MagicDataSyncC2SPacket(playerMagic.getMp(), playerMagic.getMAX_MP(), playerMagic.getMpRegen()), player);

            });


        });
        return true;
    }

}
