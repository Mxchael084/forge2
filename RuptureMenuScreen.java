package dev.rupture.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public final class RuptureMenuScreen extends Screen {
    public RuptureMenuScreen() { super(Component.literal("RUPTURE REBORN")); }

    @Override protected void init() {
        int w = 190, x = (this.width - w) / 2, y = this.height / 2 - 78;
        addRenderableWidget(toggle("Coordinates HUD", () -> ClientFeatures.coordinates, v -> ClientFeatures.coordinates = v, x, y)); y += 28;
        addRenderableWidget(toggle("FPS Counter", () -> ClientFeatures.fps, v -> ClientFeatures.fps = v, x, y)); y += 28;
        addRenderableWidget(toggle("Sprint Assist", () -> ClientFeatures.sprintAssist, v -> ClientFeatures.sprintAssist = v, x, y)); y += 28;
        addRenderableWidget(toggle("Fullbright (UI setting)", () -> ClientFeatures.fullbright, v -> ClientFeatures.fullbright = v, x, y)); y += 36;
        addRenderableWidget(Button.builder(Component.literal("Done"), b -> onClose()).bounds(x, y, w, 20).build());
    }

    private Button toggle(String label, java.util.function.BooleanSupplier get, java.util.function.Consumer<Boolean> set, int x, int y) {
        return Button.builder(toggleText(label, get.getAsBoolean()), b -> {
            boolean next = !get.getAsBoolean(); set.accept(next); b.setMessage(toggleText(label, next));
        }).bounds(x, y, 190, 20).build();
    }
    private Component toggleText(String label, boolean enabled) {
        return Component.literal(label + "  [" + (enabled ? "ON" : "OFF") + "]");
    }
    @Override public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(font, title, width / 2, height / 2 - 105, 0xFF8BE9FD);
        graphics.drawCenteredString(font, "Single-player QoL", width / 2, height / 2 - 92, 0xFFAAAAAA);
        super.render(graphics, mouseX, mouseY, partialTick);
    }
    @Override public boolean isPauseScreen() { return false; }
}
