package deus.rarity_lib;


import deus.rarity_lib.Config.ModConfig;
import deus.rarity_lib.Interfaces.mixin.IItemRarityMixin;
import deus.rarity_lib.Tools.Debug.Debug;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.lang.management.ManagementFactory;

import static deus.rarity_lib.Tools.Debug.Debug.isDebug;


public class RarityLibMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = "rarity_lib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Rarity lib initialized.");
	}

	public static ModConfig config = new ModConfig();

	@Override
	public void beforeGameStart() {

		// Set it to debugExecuteIt can execute code / if its in debug mode this is executed
		isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
			getInputArguments().toString().contains("-agentlib:jdwp");

		// Debug Item
		Debug.debugExecuteIt(() -> {
			Debug.println("RarityLib Debug mode");
		});
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
