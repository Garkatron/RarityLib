package deus.electrika.blocks;

import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;

public class DischargedBatteryBlock extends BlockTileEntityRotatable {
	public DischargedBatteryBlock(String key, int id, Material material) {
		super(key, id, material);
	}
	@Override
	protected TileEntity getNewBlockEntity() {
		return new TileEntityDischargedBatteryBlock();
	}
}
