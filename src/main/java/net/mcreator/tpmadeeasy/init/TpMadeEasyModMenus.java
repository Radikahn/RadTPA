
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.tpmadeeasy.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.tpmadeeasy.world.inventory.RadtpuiMenu;
import net.mcreator.tpmadeeasy.TpMadeEasyMod;

public class TpMadeEasyModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TpMadeEasyMod.MODID);
	public static final RegistryObject<MenuType<RadtpuiMenu>> RADTPUI = REGISTRY.register("radtpui", () -> IForgeMenuType.create(RadtpuiMenu::new));
}
