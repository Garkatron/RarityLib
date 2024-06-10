package deus.rarity_lib.mixin;

import deus.rarity_lib.Interfaces.IItemRarityMixin;
import deus.rarity_lib.RarityLevel;
import net.minecraft.core.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class MixinRarityItem implements IItemRarityMixin {

	@Unique
	private RarityLevel rarityLevel = RarityLevel.COMMON;

	@Inject(method = "<init>(I)V", at = @At("TAIL"))
	private void onConstruct(int id, CallbackInfo ci) {
		System.out.println("Constructor de Item (int id) invocado con id: " + id);
	}

	@Override
	public void rarityLib$setRarityLevel(RarityLevel rarityLevel) {
		this.rarityLevel = rarityLevel;
	}

	@Override
	public RarityLevel rarityLib$getRarityLevel() {
		return this.rarityLevel;
	}

	//@Override
	//public void rarityLib$setRarityLevel(RarityLevel rarityLevel) {
		//this.rarityLevel = rarityLevel;
	//}

	//@Override
	//public RarityLevel rarityLib$getRarityLevel() {
		//
	//}

}
