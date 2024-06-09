package deus.rarity_lib.items;

import deus.rarity_lib.interfaces.IPlayerRarityMixin;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryBasic;
import net.minecraft.core.world.World;

public class ItemMagnifyingGlass extends Item {
	public ItemMagnifyingGlass(String name, int id) {
		super(name, id);
	}
	public ItemMagnifyingGlass(int id) {
		super(id);
	}

	private static IInventory inventory = new InventoryBasic("2",1);

	public void displayGui(EntityPlayer player, IInventory inventory) {
		((IPlayerRarityMixin)player).displayGUIMagnifyingGlass(inventory);

	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {



		if (!world.isClientSide) {

			this.displayGui(entityplayer, inventory);
		}


		//Debug.println(((IItemRarityMixin)itemstack.getItem()).rarityLib$getRarityLevel().toString());
		return super.onItemRightClick(itemstack, world, entityplayer);
	}


}
