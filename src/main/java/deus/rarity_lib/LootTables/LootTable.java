package deus.rarity_lib.LootTables;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LootTable {

	private final ThreadLocalRandom rng = ThreadLocalRandom.current();
	private final Map<Integer, WeightedRandomLootObject> objectsWithProbability;

	public LootTable() {
		this.objectsWithProbability = new HashMap<>();
	}

	public LootTable(Map<Integer, WeightedRandomLootObject> items) {
		this.objectsWithProbability = items;
	}

	/*
	public Item getRandomItem() {
		if (objectsWithProbability.isEmpty()) {
			throw new IllegalStateException("No items in the loot table");
		}


		int randomIndex = rng.nextInt(objectsWithProbability.size());
		Item p = objectsWithProbability.get();

		return
	}
	 */


	public void addItemWithProbability(int probability, Item item, int min_quenty, int max_quenty) {
		if (probability <= 0 || probability > 100) {
			throw new IllegalArgumentException("Probability must be between 1 and 100");
		}
		int totalProbability = objectsWithProbability.keySet().stream().mapToInt(Integer::intValue).sum() + probability;
		if (totalProbability > 100) {
			throw new IllegalArgumentException("Total probability cannot exceed 100%");
		}
		objectsWithProbability.put(probability, new WeightedRandomLootObject(item.getDefaultStack(), min_quenty, max_quenty));
	}

	public ItemStack getRandomItemWithProbability() {
		if (objectsWithProbability.isEmpty()) {
			throw new IllegalStateException("No items in the loot table");
		}
		int n = rng.nextInt(1, 101);
		int cumulativeProbability = 0;
		List<Integer> probabilities = new ArrayList<>(objectsWithProbability.keySet());
		Collections.shuffle(probabilities);
		for (int probability : probabilities) {
			cumulativeProbability += probability;
			if (n <= cumulativeProbability) {
				ItemStack stack = objectsWithProbability.get(probability).getItemStack();
				return new ItemStack(stack.itemID, 1, stack.getMetadata(), stack.getData());
			}
		}
		// Si no se ha seleccionado ningún elemento, devolvemos el último elemento de la loot table
		int lastProbability = probabilities.get(probabilities.size() - 1);
		ItemStack lastItemStack = objectsWithProbability.get(lastProbability).getItemStack();
		System.out.println("¡Atención! No se ha encontrado ningún elemento con probabilidad positiva en la loot table. Devolviendo el último elemento de la tabla.");
		return new ItemStack(lastItemStack.itemID, 1, lastItemStack.getMetadata(), lastItemStack.getData());
	}
}
