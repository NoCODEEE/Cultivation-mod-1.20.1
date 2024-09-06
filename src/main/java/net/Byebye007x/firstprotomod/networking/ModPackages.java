package net.Byebye007x.firstprotomod.networking;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.networking.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackages {
    private static SimpleChannel INSTANCE;

    private static int packetID = 0;
    private static int id() {
        return packetID++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ProtoMod.MOD_ID, "packages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(UseMagicC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(UseMagicC2SPacket::new)
                .encoder(UseMagicC2SPacket::toBytes)
                .consumerMainThread(UseMagicC2SPacket::handle)
                .add();

        net.messageBuilder(UseDashEnchantmentC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(UseDashEnchantmentC2SPacket::new)
                .encoder(UseDashEnchantmentC2SPacket::toBytes)
                .consumerMainThread(UseDashEnchantmentC2SPacket::handle)
                .add();

        net.messageBuilder(UseCloudStepEnchantmentC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(UseCloudStepEnchantmentC2SPacket::new)
                .encoder(UseCloudStepEnchantmentC2SPacket::toBytes)
                .consumerMainThread(UseCloudStepEnchantmentC2SPacket::handle)
                .add();

        net.messageBuilder(CultivateC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CultivateC2SPacket::new)
                .encoder(CultivateC2SPacket::toBytes)
                .consumerMainThread(CultivateC2SPacket::handle)
                .add();

        net.messageBuilder(MagicDataSyncC2SPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(MagicDataSyncC2SPacket::new)
                .encoder(MagicDataSyncC2SPacket::toBytes)
                .consumerMainThread(MagicDataSyncC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG packages) {
        INSTANCE.sendToServer(packages);
    }

    public static <MSG> void sendToPlayer(MSG packages, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), packages);
    }
}
