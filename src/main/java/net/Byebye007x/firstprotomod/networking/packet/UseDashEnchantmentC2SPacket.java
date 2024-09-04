package net.Byebye007x.firstprotomod.networking.packet;

import net.Byebye007x.firstprotomod.enchantment.DashEnchantment;
import net.Byebye007x.firstprotomod.enchantment.ModEnchantments;
import net.Byebye007x.firstprotomod.entity.custom.SwordWaveEntity;
import net.Byebye007x.firstprotomod.magic.PlayerMagicProvider;
import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UseDashEnchantmentC2SPacket {
    public UseDashEnchantmentC2SPacket() {

    }

    public UseDashEnchantmentC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // ON the server
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel().getLevel();


            //Use Dash
            player.getCapability(PlayerMagicProvider.PLAYER_MP).ifPresent(playerMagic -> {
                if (playerMagic.getMp() > 0) {
                    if (DashEnchantment.canDash(player)) {
                        playerMagic.subMp(1);
                        level.playSeededSound(null, player.getX(), player.getY(), player.getZ(),
                                SoundEvents.SAND_STEP, SoundSource.PLAYERS, 1.0F, 1.0F, 0);
                    }


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
