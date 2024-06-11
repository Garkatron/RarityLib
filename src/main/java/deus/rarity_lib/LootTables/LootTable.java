package deus.rarity_lib.LootTables;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class LootTable {

	private final ThreadLocalRandom rng = ThreadLocalRandom.current();
	private final Map<Float, WeightedRandomLootObject> objectsWithProbability;

	public LootTable() {
		this.objectsWithProbability = new HashMap<Float, WeightedRandomLootObject>();
	}

	public LootTable(Map<Float, WeightedRandomLootObject> items) {
		this.objectsWithProbability = items;
	}

	public Item getRandomItem() {
		if (objectsWithProbability.isEmpty()) {
			throw new IllegalStateException("No items in the loot table");
		}

		int randomIndex = rng.nextInt(objectsWithProbability.size());
		return (Item) objectsWithProbability.values().toArray()[randomIndex];
	}

	public void addItemWithProbability(Float probability, Item item, int min_quantity, int max_quantity) {
		if (probability <= 0 || probability > 100) {
			throw new IllegalArgumentException("Probability must be between 1 and 100");
		}
		float totalProbability = (float) objectsWithProbability.keySet().stream()
			.mapToDouble(p -> p)
			.sum() + probability;
		if (totalProbability > 100) {
			throw new IllegalArgumentException("Total probability cannot exceed 100%");
		}
		objectsWithProbability.put(probability, new WeightedRandomLootObject(item.getDefaultStack(), min_quantity, max_quantity));
	}

	public ItemStack getRandomItemWithProbability() {
		if (objectsWithProbability.isEmpty()) {
			throw new IllegalStateException("No items in the loot table");
		}
		float n = rng.nextFloat() * 100;  // Adjusting to percentage scale

		float cumulativeProbability = 0;
		for (Map.Entry<Float, WeightedRandomLootObject> entry : objectsWithProbability.entrySet()) {
			cumulativeProbability += entry.getKey();
			if (n <= cumulativeProbability) {
				ItemStack stack = entry.getValue().getItemStack();
				return new ItemStack(stack.itemID, 1, stack.getMetadata(), stack.getData());
			}
		}
		return null;
	}
}
