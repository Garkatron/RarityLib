package deus.rarity_lib;


import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class RarityLibMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = "rarity_lib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Rarity lib initialized.");
	}

	//public static Item testItem;

	@Override
	public void beforeGameStart() {

		//testItem = ItemHelper.createItem(MOD_ID, new Item(140), "testitem", "test.png");
		//CreativeHelper.setPriority(testItem,1000);

		//IItemMixin mixinItem = (IItemMixin) testItem;
		//mixinItem.rarityLib$setRarityLevel(RarityLevel.COMMON);


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
