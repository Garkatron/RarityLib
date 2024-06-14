package deus.electrika.blocks;

//import static dev.deus.fishing_additions.Config.ModConfig.newBlockID;

import deus.electrika.blocks.BlockModel.CustomBlockModel;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

import static deus.electrika.ElectrikaMod.config;


public class BlockInitializer {

	public static Block battery_block;
	public static Block discharged_battery_block;
	public void MakeBlocks(String id) {

		BlockBuilder standard_block_builder = new BlockBuilder(id);

		// Creating blocks
		BlockBuilder battery_block_builder = standard_block_builder
			.setBlockModel(CustomBlockModel::new)
			.setBlockSound(BlockSounds.METAL)
			.setHardness(1.0F)
			.setResistance(1.0F)
			.setTextures("electrika:block/battery")
			.setTags(BlockTags.MINEABLE_BY_PICKAXE);
		;

		// Creating blocks
		BlockBuilder discharged_battery_block_builder = standard_block_builder
			.setBlockModel(CustomBlockModel::new)
			.setBlockSound(BlockSounds.METAL)
			.setHardness(1.0F)
			.setResistance(1.0F)
			.setTextures("electrika:block/battery")
			.setTags(BlockTags.MINEABLE_BY_PICKAXE);
		;

		battery_block = new BatteryBlock("BatteryBlock", config.newBlockID(), Material.metal);
		battery_block_builder.build(battery_block);

		discharged_battery_block = new DischargedBatteryBlock("DischargedBatteryBlock", config.newBlockID(), Material.metal);
		discharged_battery_block_builder.build(discharged_battery_block);

	}
}
