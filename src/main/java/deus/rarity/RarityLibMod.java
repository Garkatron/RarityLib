package deus.rarity;


import deus.rarity.interfaces.IItemMixin;
import deus.rarity.mixin.MixinRarityItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.Item;
import org.lwjgl.Sys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class RarityLibMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "rarity_levels";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("ExampleMod initialized.");
    }

	public static Item testItem;

	@Override
	public void beforeGameStart() {

		testItem = ItemHelper.createItem(MOD_ID, new Item(140), "testitem", "test.png");
		CreativeHelper.setPriority(testItem,1000);

		IItemMixin mixinItem = (IItemMixin) testItem;
		mixinItem.rarityLib$setRarityLevel(RarityLevel.COMMON);


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
