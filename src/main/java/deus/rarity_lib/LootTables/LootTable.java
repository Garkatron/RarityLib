package deus.rarity_lib.LootTables;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class LootTable {

	private final ThreadLocalRandom rng = ThreadLocalRandom.current();
	private final Map<Integer, WeightedRandomLootObject> objectsWithProbability;

	public LootTable() {
		this.objectsWithProbability = new HashMap<Integer, WeightedRandomLootObject>();
	}

	public LootTable(Map<Integer, WeightedRandomLootObject> items) {
		this.objectsWithProbability = items;
	}

	public ItemStack getRandomItemStack() {
		if (objectsWithProbability.isEmpty()) {
			throw new IllegalStateException("No items in the loot table");
		}

		int randomIndex = rng.nextInt(objectsWithProbability.size());
		ItemStack stack =  ((WeightedRandomLootObject)objectsWithProbability.values().toArray()[randomIndex]).getItemStack();
		return new ItemStack(stack.itemID, 1, stack.getMetadata(), stack.getData());

	}

	public Item getRandomItem() {
		if (objectsWithProbability.isEmpty()) {
			throw new IllegalStateException("No items in the loot table");
		}

		int randomIndex = rng.nextInt(objectsWithProbability.size());
		ItemStack stack =  ((WeightedRandomLootObject)objectsWithProbability.values().toArray()[randomIndex]).getItemStack();
		return new ItemStack(stack.itemID, 1, stack.getMetadata(), stack.getData()).getItem();

	}

	public void addItemWithProbability(int probability, Item item, int min_quantity, int max_quantity) {
		if (probability <= 0 || probability > 100) {
			throw new IllegalArgumentException("Probability must be between 1 and 100");
		}
		int totalProbability = objectsWithProbability.keySet().stream().mapToInt(Integer::intValue).sum() + probability;

		if (totalProbability > 100) {
			throw new IllegalArgumentException("Total probability cannot exceed 100%");
		}
		objectsWithProbability.put(probability, new WeightedRandomLootObject(item.getDefaultStack(), min_quantity, max_quantity));
	}

	public ItemStack getRandomItemWithProbability() {
		double randomDouble = Math.random();
		double totalProbability = objectsWithProbability.keySet().stream()
			.mapToDouble(probability -> probability) // Assuming probabilities are already doubles
			.sum();
		double scaledRandom = randomDouble * totalProbability;
		double cumulativeProbability = 0;

		for (Map.Entry<Integer, WeightedRandomLootObject> entry : objectsWithProbability.entrySet()) {
			cumulativeProbability += entry.getKey();
			if (scaledRandom <= cumulativeProbability) {
				ItemStack stack = entry.getValue().getItemStack();
				return new ItemStack(stack.getItem(), 1, stack.getMetadata());
			}
		}

		// Si no se ha seleccionado ningún objeto, se devuelve un elemento por defecto
		return new ItemStack(Item.ammoArrow, 1);
	}

}
/*
if (objectsWithProbability.isEmpty()) {
			throw new IllegalStateException("No items in the loot table");
		}
	for (Map.Entry<Float, WeightedRandomLootObject> entry : objectsWithProbability.entrySet()) {
			cumulativeProbability += entry.getKey();
			if (n <= cumulativeProbability) {
				ItemStack stack = entry.getValue().getItemStack();
				return new ItemStack(stack.itemID, 1, stack.getMetadata(), stack.getData());
			}
		}

 */
