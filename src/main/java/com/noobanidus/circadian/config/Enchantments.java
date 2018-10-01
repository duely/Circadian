package com.noobanidus.circadian.config;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.noobanidus.circadian.enchantment.EnchantmentManabound;

public class Enchantments {
	public static final Enchantments INSTANCE = new Enchantments();

	public static Enchantment manabound;

	private Enchantments() {
	}

	public static void preInit() {
		manabound = new EnchantmentManabound("circadian:manabound");

		MinecraftForge.EVENT_BUS.register(INSTANCE);
	}

	@SubscribeEvent
	public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        if (((EnchantmentManabound) manabound).enabled) {
    		event.getRegistry().register(manabound);
        }
	}
}
