package dev.rupture.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.AddGuiOverlayLayersEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = RuptureReborn.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ClientHooks {
    private static KeyMapping menuKey;
    private ClientHooks() {}

    @Mod.EventBusSubscriber(modid = RuptureReborn.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class ModBus {
        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event) {
            menuKey = new KeyMapping("key.rupture_reborn.menu", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "key.categories.rupture_reborn");
            event.register(menuKey);
        }
        @SubscribeEvent
        public static void registerOverlays(AddGuiOverlayLayersEvent event) {
            event.getLayeredDraw().add(
                ResourceLocation.fromNamespaceAndPath(RuptureReborn.MOD_ID, "rupture_hud"),
                (graphics, deltaTracker) -> drawHud(graphics)
            );
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (menuKey != null && menuKey.consumeClick()) mc.setScreen(new RuptureMenuScreen());
        if (ClientFeatures.sprintAssist && mc.player.input.hasForwardImpulse()) mc.player.setSprinting(true);
    }

    private static void drawHud(GuiGraphics graphics) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;
        int y = 8;
        if (ClientFeatures.fps) {
            graphics.drawString(mc.font, Component.literal("FPS " + mc.getFps()), 8, y, 0xFF8BE9FD, true);
            y += 11;
        }
        if (ClientFeatures.coordinates) {
            var p = mc.player;
            String coords = String.format(java.util.Locale.ROOT, "XYZ %.1f / %.1f / %.1f", p.getX(), p.getY(), p.getZ());
            graphics.drawString(mc.font, Component.literal(coords), 8, y, 0xFFFFFFFF, true);
        }
    }
}
