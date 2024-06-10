package deus.rarity_lib;

import deus.rarity_lib.Config.ModConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static deus.rarity_lib.Tools.ItemUtils.makeItem;


public class RarityLibMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "rarity_lib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Rarity Lib initialized.");
    }

	public static ModConfig config = new ModConfig();
	public static final ItemBuilder GenericItemBuilder = new ItemBuilder(MOD_ID);
	public static Item magnifying_glass;



	@Override
	public void beforeGameStart() {

		//magnifying_glass = makeItem(new ItemMagnifyingGlass("magnifying_glass", config.newItemID()),RarityLevel.COMMON);
		//CreativeHelper.setPriority(magnifying_glass,1000);


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
