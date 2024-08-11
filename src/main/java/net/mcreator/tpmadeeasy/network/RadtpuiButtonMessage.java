
package net.mcreator.tpmadeeasy.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.tpmadeeasy.world.inventory.RadtpuiMenu;
import net.mcreator.tpmadeeasy.procedures.UiCloseLogicProcedure;
import net.mcreator.tpmadeeasy.procedures.TpaLogicManagerProcedure;
import net.mcreator.tpmadeeasy.procedures.TpaLogicDenyProcedure;
import net.mcreator.tpmadeeasy.procedures.ReturntpherestatusProcedure;
import net.mcreator.tpmadeeasy.TpMadeEasyMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RadtpuiButtonMessage {
	private final int buttonID, x, y, z;

	public RadtpuiButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public RadtpuiButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(RadtpuiButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(RadtpuiButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = RadtpuiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			UiCloseLogicProcedure.execute(entity);
		}
		if (buttonID == 1) {

			ReturntpherestatusProcedure.execute(entity);
		}
		if (buttonID == 2) {

			TpaLogicManagerProcedure.execute(entity);
		}
		if (buttonID == 3) {

			TpaLogicDenyProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		TpMadeEasyMod.addNetworkMessage(RadtpuiButtonMessage.class, RadtpuiButtonMessage::buffer, RadtpuiButtonMessage::new, RadtpuiButtonMessage::handler);
	}
}
