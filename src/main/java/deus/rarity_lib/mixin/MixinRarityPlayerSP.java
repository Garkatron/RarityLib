package deus.rarity_lib.mixin;

import deus.rarity_lib.GUIs.MagnifyingGlassGui;
import deus.rarity_lib.interfaces.IPlayerRarityMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.player.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EntityPlayerSP.class, remap = false)

public class MixinRarityPlayerSP implements IPlayerRarityMixin {


	@Shadow
	protected Minecraft mc;


	@Override
	public void displayGUIMagnifyingGlass(IInventory inventory) {
		mc.displayGuiScreen(new MagnifyingGlassGui(this.mc.thePlayer.inventory, inventory));

	}
}
