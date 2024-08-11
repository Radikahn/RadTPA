package net.mcreator.tpmadeeasy.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.tpmadeeasy.network.TpMadeEasyModVariables;

public class ReturntpherestatusProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(TpMadeEasyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TpMadeEasyModVariables.PlayerVariables())).tpaLogic) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("TpHere Status: Allow"), false);
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("TpHere Status: Blocked"), false);
		}
	}
}
