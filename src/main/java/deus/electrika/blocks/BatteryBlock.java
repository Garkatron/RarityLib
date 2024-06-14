package deus.electrika.blocks;

import deus.electrika.core.interfaces.IProvider;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.World;

public class BatteryBlock extends BlockTileEntityRotatable {


	public BatteryBlock(String key, int id, Material material) {
		super(key, id, material);
	}

	@Override
	public void onBlockRemoved(World world, int x, int y, int z, int data) {
		super.onBlockRemoved(world, x, y, z, data);

	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z) {
		super.onBlockDestroyedByExplosion(world, x, y, z);

	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta, EntityPlayer player, Item item) {
		super.onBlockDestroyedByPlayer(world, x, y, z, meta, player, item);

	}

	@Override
	protected TileEntity getNewBlockEntity() {

		return new TileEntityBatteryBlock();
	}
}
