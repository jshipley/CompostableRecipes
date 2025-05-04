package com.jship.compostablerecipes.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import com.jship.compostablerecipes.CompostableRecipes;
import com.jship.compostablerecipes.recipe.CompostableRecipe;

public final class CompostableRecipesFabric implements ModInitializer {
    private static final RecipeType<CompostableRecipe> COMPOSTABLE_RECIPE = Registry.register(BuiltInRegistries.RECIPE_TYPE, CompostableRecipes.id("compostable"), new RecipeType<CompostableRecipe>() {
        @Override
        public String toString() {
            return CompostableRecipes.MOD_ID + ":compostable";
        }
    });
    private static final RecipeSerializer<CompostableRecipe> COMPOSTABLE_RECIPE_SERIALIZER = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, CompostableRecipes.id("compostable"), new CompostableRecipe.Serializer());
    private static final RecipeBookCategory COMPOSTABLE_CATEGORY = Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, CompostableRecipes.id("compostable"), new RecipeBookCategory());

    @Override
    public void onInitialize() {
        CompostableRecipes.COMPOSTABLE_RECIPE = () -> COMPOSTABLE_RECIPE;
        CompostableRecipes.COMPOSTABLE_RECIPE_SERIALIZER = () -> COMPOSTABLE_RECIPE_SERIALIZER;
        CompostableRecipes.COMPOSTABLE_CATEGORY = () -> COMPOSTABLE_CATEGORY;

        // Run our common setup.
        CompostableRecipes.init();

        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            CompostableRecipes.loadRecipes(server);
        });

        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
            if (success) CompostableRecipes.loadRecipes(server);
        });
    }
}
