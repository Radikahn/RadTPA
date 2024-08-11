
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.tpmadeeasy.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.tpmadeeasy.network.GuiopenpressMessage;
import net.mcreator.tpmadeeasy.network.CloseUIMessage;
import net.mcreator.tpmadeeasy.TpMadeEasyMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class TpMadeEasyModKeyMappings {
	public static final KeyMapping GUIOPENPRESS = new KeyMapping("key.tp_made_easy.guiopenpress", GLFW.GLFW_KEY_PERIOD, "key.categories.ui") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				TpMadeEasyMod.PACKET_HANDLER.sendToServer(new GuiopenpressMessage(0, 0));
				GuiopenpressMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping CLOSE_UI = new KeyMapping("key.tp_made_easy.close_ui", GLFW.GLFW_KEY_RIGHT_CONTROL, "key.categories.ui") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				TpMadeEasyMod.PACKET_HANDLER.sendToServer(new CloseUIMessage(0, 0));
				CloseUIMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(GUIOPENPRESS);
		event.register(CLOSE_UI);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				GUIOPENPRESS.consumeClick();
				CLOSE_UI.consumeClick();
			}
		}
	}
}
