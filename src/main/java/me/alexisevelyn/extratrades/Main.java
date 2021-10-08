package me.alexisevelyn.extratrades;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {
	public static final String MOD_ID = "extratrades";
	public static final Logger LOGGER = LogManager.getLogger("Extra Trades");
	public Potion UNLUCK;
	public Potion DECAY;
	public Potion DULLNESS;
	public Potion BLINDNESS;
	public Potion CONFUSION;
	public Potion LEVITATION;
	public Potion HEALTH_BOOST;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		this.registerPVPPotions();
		this.registerPotionCraftingRecipes();

//		LOGGER.info("Hello Fabric world!");
	}

	/**
	 * For Potions Which Can Be Useful In PVP,
	 * But May Not Be Possible To Get In Survival (Barring Certain Mobs Such As Elder Guardians)
	 */
	public void registerPVPPotions() {
		// Useful For Preventing Enemies From Getting Better Loot
		//   Only problem is, fishing is the only vanilla loot that is affected by luck at the moment.
		//   However, loot tables can be modified for chests in order to allow luck/unluck to be useful.
		// 5 Minutes (300 Seconds) * 20 Ticks = 6000 Ticks
		UNLUCK = Registry.register(Registry.POTION, new Identifier(MOD_ID, "unluck"), new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 6000)));

		// Useful For Giving Enemies The Wither Effect
		// 40 Seconds * 20 Ticks = 800 Ticks
		DECAY = Registry.register(Registry.POTION, new Identifier(MOD_ID, "decay"), new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, 800)));

		// Useful For Preventing Enemies From Mining
		//    Used The Elder Guardian For The Basis For Time
		// 5 Minutes (300 Seconds) * 20 Ticks = 6000 Ticks
		DULLNESS = Registry.register(Registry.POTION, new Identifier(MOD_ID, "dullness"), new Potion("dullness", new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 6000)));

		// Useful For Blinding Enemies (Even Better When Combined With Night Vision)
		//    Used Illusioner As The Basis For Time
		// 20 Seconds * 20 Ticks = 400 Ticks
		BLINDNESS = Registry.register(Registry.POTION, new Identifier(MOD_ID, "blindness"), new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, 400)));

		// Useful For Giving Enemies Nausea (The Nether Portal Effect)
		//    So, apparently eating Pufferfish can give this effect, but it has to be self-inflicted.
		// 15 Seconds * 20 Ticks = 300 Ticks
		CONFUSION = Registry.register(Registry.POTION, new Identifier(MOD_ID, "confusion"), new Potion("confusion", new StatusEffectInstance(StatusEffects.NAUSEA, 300)));

		// Useful For Forcibly Lifting Enemies Off The Ground So They Can Take Fall Damage
		//   Used Shulkers' Levitation For The Basis For Time
		// 10 Seconds * 20 Ticks = 200 Ticks
		LEVITATION = Registry.register(Registry.POTION, new Identifier(MOD_ID, "levitation"), new Potion("levitation", new StatusEffectInstance(StatusEffects.LEVITATION, 200)));

		// Useful For Gaining Some Extra Hearts (Different From Absorption)
		//   I'm using Golden Apples' Absorption As The Basis For Time
		// 2 Minutes (120 Seconds) * 20 Ticks = 2400 Ticks
		HEALTH_BOOST = Registry.register(Registry.POTION, new Identifier(MOD_ID, "health_boost"), new Potion("health_boost", new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2400)));
	}

	public void registerPotionCraftingRecipes() {
		// Awkward (Nether Wart) - Makes Complex Potions
		// Mundane (Redstone As Well As Many Other Alternatives) - Extends Duration Of Potion
		// Thick (Glowstone) - Makes Potion Stronger
		// Weakness (Fermented Spider Eye) - Corrupts Potion (Negatively)
		// Splash (Gunpowder) - Makes Potion Throwable
		// Lingering (Dragon Breath) - Makes Throwable Potions That Last A While On The Ground
		// ---
		// Consider Using Existing Usable Potions For Base For Some Of These Potions

//		BrewingRecipeRegistry.registerPotionRecipe(null, null, UNLUCK);  // Not Vanilla Survival
		BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.WITHER_ROSE, DECAY);
//		BrewingRecipeRegistry.registerPotionRecipe(null, null, DULLNESS);  // Elder Guardian
		BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.AZURE_BLUET, BLINDNESS);
//		BrewingRecipeRegistry.registerPotionRecipe(null, null, CONFUSION);  // Eating Puffer Fish
//		BrewingRecipeRegistry.registerPotionRecipe(null, null, LEVITATION);  // Shulker Bullet
//		BrewingRecipeRegistry.registerPotionRecipe(null, Items.APPLE, HEALTH_BOOST);  // Not Vanilla Survival
	}
}
