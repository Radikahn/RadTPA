package net.mcreator.tpmadeeasy.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.tpmadeeasy.network.TpMadeEasyModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class MoveplayerProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		boolean preventTPA = false;
		Entity player = null;
		if (((new Object() {
			public Entity getEntity() {
				try {
					return EntityArgument.getEntity(arguments, "Player_Name");
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
					return null;
				}
			}
		}.getEntity()).getCapability(TpMadeEasyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TpMadeEasyModVariables.PlayerVariables())).tpaLogic) {
			{
				Entity _ent = (new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "Player_Name");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity());
				_ent.teleportTo((entity.getX()), (entity.getY()), (entity.getZ()));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((entity.getX()), (entity.getY()), (entity.getZ()), _ent.getYRot(), _ent.getXRot());
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(((new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "Player_Name");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity()).getDisplayName().getString() + " has disabled tpHere!")), false);
		}
	}
}
