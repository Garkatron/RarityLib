package deus.rarity_lib.LootTables;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.monster.EntityZombie;
import net.minecraft.core.item.Item;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.ThreadLocalRandom;

public class LootTable {

	private Map<Integer, WeightedRandomLootObject> objectsWithProbability;
	private final ThreadLocalRandom rng = ThreadLocalRandom.current();

	public LootTable() {
		this.objectsWithProbability = new HashMap<>();
	}

	public LootTable(Map<Integer, WeightedRandomLootObject> items) {
		this.objectsWithProbability = items;
	}

	public Item getRandomItem() {
		if (objectsWithProbability.isEmpty()) {
			throw new IllegalStateException("No items in the loot table");
		}

		int randomIndex = rng.nextInt(objectsWithProbability.size());
		return (Item) objectsWithProbability.values().toArray()[randomIndex];
	}


	public void addItemWithProbability(int probability, Item item, int min_quenty, int max_quenty) {
		if (probability <= 0 || probability > 100) {
			throw new IllegalArgumentException("Probability must be between 1 and 100");
		}
		int totalProbability = objectsWithProbability.keySet().stream().mapToInt(Integer::intValue).sum() + probability;
		if (totalProbability > 100) {
			throw new IllegalArgumentException("Total probability cannot exceed 100%");
		}
		objectsWithProbability.put(probability, new WeightedRandomLootObject( item.getDefaultStack() ,min_quenty, max_quenty));
	}

	public ItemStack getRandomItemWithProbability() {
		if (objectsWithProbability.isEmpty()) {
			throw new IllegalStateException("No items in the loot table");
		}
		int n = rng.nextInt(1, 101);
		int cumulativeProbability = 0;
		for (int probability : objectsWithProbability.keySet()) {
			cumulativeProbability += probability;
			if (n <= cumulativeProbability) {
				ItemStack stack = objectsWithProbability.get(probability).getItemStack();
				return new ItemStack(stack.itemID, 1, stack.getMetadata(), stack.getData();
			}
		}
		return null;
	}
}
