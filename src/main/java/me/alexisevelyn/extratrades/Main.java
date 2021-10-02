package me.alexisevelyn.extratrades;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {
	public static final String MOD_ID = "extratrades";
	public static final Logger LOGGER = LogManager.getLogger("Extra Trades");
	public Potion UNLUCK;
	public Potion DECAY;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// 5 Minutes (300 Seconds) * 20 Ticks = 6000 Ticks
		UNLUCK = Registry.register(Registry.POTION, new Identifier(MOD_ID, "unluck"), new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 6000)));

		// 40 Seconds * 20 Ticks = 800 Ticks
		DECAY = Registry.register(Registry.POTION, new Identifier(MOD_ID, "decay"), new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, 800)));
//		LOGGER.info("Hello Fabric world!");
	}
}
