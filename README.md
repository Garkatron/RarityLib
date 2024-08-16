# Rarity Lib
## Description
Help you implement rarity level to your items.

## Import
##### build.gradle
```gradle
repositories {
   maven { url = "https://jitpack.io" }
}
dependencies {
   modImplementation "com.github.Garkatron:RarityLib:${project.rarity_lib_version}"
}
```
##### gradle.properties
```gradle
mod_version = release_tag_name
```

## Usage

### Example: How to add item with rarity level
##### BTA 7.2pre1
```java
public static final ItemBuilder GenericItemBuilder = new ItemBuilder(MOD_ID);
public static Item makeItem(int id, String name, RarityLevel rarity) {
		Item item = GenericItemBuilder
			.build(new Item(name, id));

		((IItemRarityMixin) item).rarityLib$setRarityLevel(rarity);
		return item;
	}
```

### Example: How to add item with rarity level
##### BTA 7.1_01
```java
public static final ItemBuilder GenericItemBuilder = new ItemBuilder(MOD_ID);
public static Item makeItem(int id, String name, RarityLevel rarity) {
		Item item = GenericItemBuilder
			.build(new Item(name, id));

		((IItemRarityMixin) item).rarityLib$setRarityLevel(rarity);
		return item;
	}
```
