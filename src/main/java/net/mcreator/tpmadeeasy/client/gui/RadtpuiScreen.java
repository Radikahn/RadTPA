package net.mcreator.tpmadeeasy.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.tpmadeeasy.world.inventory.RadtpuiMenu;
import net.mcreator.tpmadeeasy.network.RadtpuiButtonMessage;
import net.mcreator.tpmadeeasy.TpMadeEasyMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class RadtpuiScreen extends AbstractContainerScreen<RadtpuiMenu> {
	private final static HashMap<String, Object> guistate = RadtpuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_x;
	Button button_check_tphere_status;
	ImageButton imagebutton_allow;
	ImageButton imagebutton_block;

	public RadtpuiScreen(RadtpuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 209;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("tp_made_easy:textures/screens/radtpui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 169 && mouseX < leftPos + 193 && mouseY > topPos + 71 && mouseY < topPos + 95)
			guiGraphics.renderTooltip(font, Component.translatable("gui.tp_made_easy.radtpui.tooltip_allow_players_to_teleport_you_to"), mouseX, mouseY);
		if (mouseX > leftPos + 178 && mouseX < leftPos + 202 && mouseY > topPos + 0 && mouseY < topPos + 24)
			guiGraphics.renderTooltip(font, Component.translatable("gui.tp_made_easy.radtpui.tooltip_exit_window"), mouseX, mouseY);
		if (mouseX > leftPos + 169 && mouseX < leftPos + 193 && mouseY > topPos + 99 && mouseY < topPos + 123)
			guiGraphics.renderTooltip(font, Component.translatable("gui.tp_made_easy.radtpui.tooltip_prevent_players_from_teleporting"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("tp_made_easy:textures/screens/radtp.png"), this.leftPos + 83, this.topPos + 8, 0, 0, 50, 50, 50, 50);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.tp_made_easy.radtpui.label_empty"), 179, 78, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.tp_made_easy.radtpui.label_empty1"), 179, 105, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_x = Button.builder(Component.translatable("gui.tp_made_easy.radtpui.button_x"), e -> {
			if (true) {
				TpMadeEasyMod.PACKET_HANDLER.sendToServer(new RadtpuiButtonMessage(0, x, y, z));
				RadtpuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 175, this.topPos + 2, 30, 20).build();
		guistate.put("button:button_x", button_x);
		this.addRenderableWidget(button_x);
		button_check_tphere_status = Button.builder(Component.translatable("gui.tp_made_easy.radtpui.button_check_tphere_status"), e -> {
			if (true) {
				TpMadeEasyMod.PACKET_HANDLER.sendToServer(new RadtpuiButtonMessage(1, x, y, z));
				RadtpuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 43, this.topPos + 128, 124, 20).build();
		guistate.put("button:button_check_tphere_status", button_check_tphere_status);
		this.addRenderableWidget(button_check_tphere_status);
		imagebutton_allow = new ImageButton(this.leftPos + 56, this.topPos + 71, 100, 25, 0, 0, 25, new ResourceLocation("tp_made_easy:textures/screens/atlas/imagebutton_allow.png"), 100, 50, e -> {
			if (true) {
				TpMadeEasyMod.PACKET_HANDLER.sendToServer(new RadtpuiButtonMessage(2, x, y, z));
				RadtpuiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:imagebutton_allow", imagebutton_allow);
		this.addRenderableWidget(imagebutton_allow);
		imagebutton_block = new ImageButton(this.leftPos + 56, this.topPos + 98, 100, 25, 0, 0, 25, new ResourceLocation("tp_made_easy:textures/screens/atlas/imagebutton_block.png"), 100, 50, e -> {
			if (true) {
				TpMadeEasyMod.PACKET_HANDLER.sendToServer(new RadtpuiButtonMessage(3, x, y, z));
				RadtpuiButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:imagebutton_block", imagebutton_block);
		this.addRenderableWidget(imagebutton_block);
	}
}
