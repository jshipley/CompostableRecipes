package com.jship.compostablerecipes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.slf4j.Logger;

import com.jship.compostablerecipes.recipe.CompostableRecipe;
import com.mojang.logging.LogUtils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.ComposterBlock;

public final class CompostableRecipes {
    public static final String MOD_ID = "compostablerecipes";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static Supplier<RecipeType<CompostableRecipe>> COMPOSTABLE_RECIPE;
    public static Supplier<RecipeSerializer<CompostableRecipe>> COMPOSTABLE_RECIPE_SERIALIZER;
    public static Supplier<RecipeBookCategory> COMPOSTABLE_CATEGORY;

    // original chance, current weight. Used for undoing changes that this mod made
    // to composter while leaving vanilla or other mod changes in place
    public record ChancePair(float chance, int weight) {
    }

    private static Map<Item, ChancePair> ADDED_ITEMS = new HashMap<>();

    public static void init() {
    }

    public static void loadRecipes(MinecraftServer server) {
        resetComposter();

        server.getRecipeManager().getRecipes()
            .stream().filter((recipe) -> recipe.value().getType() == CompostableRecipes.COMPOSTABLE_RECIPE.get())
            .forEach(recipeHolder -> {
            addToComposter((CompostableRecipe)recipeHolder.value());
        });

        LOGGER.info("{} items added to composter", ADDED_ITEMS.size());
    }

    private static void resetComposter() {
        ADDED_ITEMS.forEach((item, weightedChance) -> {
            if (weightedChance.chance() > 0) {
                ComposterBlock.COMPOSTABLES.put(item, weightedChance.chance());
            } else {
                ComposterBlock.COMPOSTABLES.removeFloat(item);
            }
        });
        ADDED_ITEMS.clear();
    }

    private static void addToComposter(CompostableRecipe recipe) {
        recipe.ingredient().items().forEach(itemHolder ->
            addToComposter(itemHolder.value(), recipe.chance(), recipe.weight()));
    }

    private static void addToComposter(Item item, float chance, int weight) {
        ChancePair existingChance = ADDED_ITEMS.getOrDefault(item, new ChancePair(0.0f, -1));
        if (!ComposterBlock.COMPOSTABLES.containsKey(item) // not in composter
                || (existingChance.weight() < 0 && weight > 10) // or in composter but no recorded weight
                || (existingChance.weight() >= 0 & weight > existingChance.weight())) { // or in composter but should be
                                                                                        // replaced
            float oldChance = ComposterBlock.COMPOSTABLES.put(item, chance);
            // only update chance in ADDED_ITEMS if it was previously unset
            ADDED_ITEMS.put(item,
                    new ChancePair(existingChance.chance() > 0 ? existingChance.chance() : oldChance, weight));
        }
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
