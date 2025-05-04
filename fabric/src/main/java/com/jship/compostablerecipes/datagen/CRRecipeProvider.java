package com.jship.compostablerecipes.datagen;

import java.util.concurrent.CompletableFuture;

import com.jship.compostablerecipes.CompostableRecipes;
import com.jship.compostablerecipes.recipe.CompostableRecipe;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CRRecipeProvider extends FabricRecipeProvider {

    public CRRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    public void buildRecipes(RecipeOutput output) {
	    output.accept(CompostableRecipes.id("common/foods/cookies"), new CompostableRecipe(ConventionalItemTags.COOKIE_FOODS, 0.85f), null);
        output.accept(CompostableRecipes.id("common/crops"), new CompostableRecipe(ConventionalItemTags.CROPS, 0.3f), null);
        output.accept(CompostableRecipes.id("common/crops/cactus"), new CompostableRecipe(ConventionalItemTags.CACTUS_CROPS, 0.5f, 2), null);
        output.accept(CompostableRecipes.id("common/crops/pumpkin"), new CompostableRecipe(ConventionalItemTags.PUMPKIN_CROPS, 0.65f, 2), null);
        output.accept(CompostableRecipes.id("common/foods/berries"), new CompostableRecipe(ConventionalItemTags.BERRY_FOODS, 0.3f), null);
        output.accept(CompostableRecipes.id("common/foods/breads"), new CompostableRecipe(ConventionalItemTags.BREAD_FOODS, 0.85f), null);
        output.accept(CompostableRecipes.id("common/foods/fruits"), new CompostableRecipe(ConventionalItemTags.FRUIT_FOODS, 0.3f), null);
        output.accept(CompostableRecipes.id("common/foods/pies"), new CompostableRecipe(ConventionalItemTags.PIE_FOODS, 1.0f), null);
        output.accept(CompostableRecipes.id("common/foods/vegetables"), new CompostableRecipe(ConventionalItemTags.VEGETABLE_FOODS, 0.3f), null);
        output.accept(CompostableRecipes.id("common/grass_like"), new CompostableRecipe(cTag("grass_like"), 0.3f), null);
        output.accept(CompostableRecipes.id("common/grass_variants"), new CompostableRecipe(cTag("grass_variants"), 0.3f), null);
        output.accept(CompostableRecipes.id("common/grass"), new CompostableRecipe(cTag("grass"), 0.3f), null);
        output.accept(CompostableRecipes.id("common/mushrooms"), new CompostableRecipe(ConventionalItemTags.MUSHROOMS, 0.3f), null);
        output.accept(CompostableRecipes.id("common/seeds"), new CompostableRecipe(ConventionalItemTags.SEEDS, 0.3f), null);
        output.accept(CompostableRecipes.id("storage_blocks/dried_kelp"), new CompostableRecipe(ConventionalItemTags.STORAGE_BLOCKS_DRIED_KELP, 1.0f), null);
        output.accept(CompostableRecipes.id("storage_blocks/rice"), new CompostableRecipe(cTag("storage_blocks/rice"), 1.0f), null);
        output.accept(CompostableRecipes.id("storage_blocks/sugar_cane"), new CompostableRecipe(cTag("storage_blocks/sugar_cane"), 1.0f), null);
        output.accept(CompostableRecipes.id("storage_blocks/wheat"), new CompostableRecipe(ConventionalItemTags.STORAGE_BLOCKS_WHEAT, 1.0f), null);
        output.accept(CompostableRecipes.id("minecraft/flowers"), new CompostableRecipe(ItemTags.FLOWERS, 0.3f), null);
        output.accept(CompostableRecipes.id("minecraft/leaves"), new CompostableRecipe(ItemTags.LEAVES, 0.3f), null);
        output.accept(CompostableRecipes.id("minecraft/saplings"), new CompostableRecipe(ItemTags.SAPLINGS, 0.3f), null);
        output.accept(CompostableRecipes.id("minecraft/tall_flowers"), new CompostableRecipe(ItemTags.TALL_FLOWERS, 0.65f, 2), null);
        output.accept(CompostableRecipes.id("minecraft/wart_blocks"), new CompostableRecipe(ItemTags.WART_BLOCKS, 0.85f), null);
    }

    private TagKey<Item> cTag(String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TagUtil.C_TAG_NAMESPACE, path));
    }
}
