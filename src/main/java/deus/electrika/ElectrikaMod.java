package deus.electrika;


import deus.electrika.Config.ModConfig;
import deus.electrika.blocks.BlockInitializer;
import deus.electrika.blocks.TileEntityBatteryBlock;
import deus.electrika.blocks.TileEntityDischargedBatteryBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ElectrikaMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = "Electrika";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ModConfig config = new ModConfig();

	@Override
	public void onInitialize() {
		LOGGER.info("Electrika lib initialized.");
		EntityHelper.createTileEntity(TileEntityBatteryBlock.class, "TileEntityBatteryBlock");
		EntityHelper.createTileEntity(TileEntityDischargedBatteryBlock.class, "TileEntityBatteryBlock");

		new BlockInitializer().MakeBlocks(MOD_ID);
	}


	@Override
	public void beforeGameStart() {
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
