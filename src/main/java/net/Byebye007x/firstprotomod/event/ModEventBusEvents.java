package net.Byebye007x.firstprotomod.event;

import net.Byebye007x.firstprotomod.ProtoMod;
import net.Byebye007x.firstprotomod.enchantment.DashEnchantment;
import net.Byebye007x.firstprotomod.entity.ModEntities;
import net.Byebye007x.firstprotomod.entity.custom.GFEntity;
import net.Byebye007x.firstprotomod.entity.custom.RhinoEntity;
import net.Byebye007x.firstprotomod.magic.PlayerMagic;
import net.Byebye007x.firstprotomod.magic.PlayerMagicProvider;
import net.Byebye007x.firstprotomod.networking.ModPackages;
import net.Byebye007x.firstprotomod.networking.packet.MagicDataSyncC2SPacket;
import net.Byebye007x.firstprotomod.networking.packet.UseDashEnchantmentC2SPacket;
import net.Byebye007x.firstprotomod.networking.packet.UseMagicC2SPacket;
import net.Byebye007x.firstprotomod.particle.ModParticles;
import net.Byebye007x.firstprotomod.particle.custom.GlitterLightParticle;
import net.Byebye007x.firstprotomod.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

public class ModEventBusEvents {

    @Mod.EventBusSubscriber(modid = ProtoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
            event.put(ModEntities.GF.get(), GFEntity.createAttributes().build());
        }

        @SubscribeEvent
        public static void registerParticleFactories (final RegisterParticleProvidersEvent event) {
            Minecraft.getInstance().particleEngine.register(ModParticles.GLITTER_LIGHT.get(),
                    GlitterLightParticle.Provider::new);
        }



        @SubscribeEvent
        public static void onRegisterCapabilities (RegisterCapabilitiesEvent event) {
            event.register(PlayerMagic.class);
        }


    }

    @Mod.EventBusSubscriber(modid = ProtoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onPlayerCloned (PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                event.getOriginal().getCapability(PlayerMagicProvider.PLAYER_MP).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerMagicProvider.PLAYER_MP).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer (AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                if (!event.getObject().getCapability(PlayerMagicProvider.PLAYER_MP).isPresent()) {
                    event.addCapability(new ResourceLocation(ProtoMod.MOD_ID, "properties"), new PlayerMagicProvider());
                }
            }
        }

        private static int tick = 0;
        @SubscribeEvent
        public static void onPlayerTick (TickEvent.PlayerTickEvent event) {
            if (event.side == LogicalSide.SERVER) {
                tick++;

                if (event.player instanceof ServerPlayer player) {
//                    System.out.println("tick.ModBus: " + tick + "\n");

                    event.player.getCapability(PlayerMagicProvider.PLAYER_MP).ifPresent(playerMagic -> {
                        ModPackages.sendToPlayer(new MagicDataSyncC2SPacket(playerMagic.getMp(), playerMagic.getMAX_MP(), playerMagic.getMpRegen()), player);
                        int maxMp = playerMagic.getMAX_MP();
                        int mp = playerMagic.getMp();

                        if (mp < maxMp && tick % 40 == 0) {
                            int mpRegen = playerMagic.getMpRegen();
                            playerMagic.addMp(mpRegen);
                            tick = 0;

                        } else if (tick > 80) {
                            tick = 0;
                        }
                    });

                }
            }
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld (EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide()) {
                if (event.getEntity() instanceof ServerPlayer player) {
                    player.getCapability(PlayerMagicProvider.PLAYER_MP).ifPresent(playerMagic -> {
//                        float mp = playerMagic.getMp();
//                        System.out.println("MP.ModBus: " + mp + "\n");
                        ModPackages.sendToPlayer(new MagicDataSyncC2SPacket(playerMagic.getMp(), playerMagic.getMAX_MP(), playerMagic.getMpRegen()), player);
                    });
                }
            }
        }

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Player player = Minecraft.getInstance().player;
            if (KeyBinding.USE_DASH.consumeClick()) {
                if (DashEnchantment.canDash(player)) {
                    DashEnchantment.doDash(player, DashEnchantment.getExistingLevel(player));
                    ModPackages.sendToServer(new UseDashEnchantmentC2SPacket());
                }
            }

        }


    }
}
