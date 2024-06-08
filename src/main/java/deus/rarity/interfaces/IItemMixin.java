package deus.rarity.interfaces;

import deus.rarity.RarityLevel;

public interface IItemMixin {

	void rarityLib$setRarityLevel(RarityLevel rarityLevel);
	RarityLevel rarityLib$getRarityLevel();

}
