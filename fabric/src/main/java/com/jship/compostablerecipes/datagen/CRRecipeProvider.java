package com.jship.compostablerecipes.datagen;

import java.util.concurrent.CompletableFuture;

import com.jship.compostablerecipes.CompostableRecipes;
import com.jship.compostablerecipes.recipe.CompostableRecipe;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.fabricmc.fabric.impl.resource.conditions.conditions.TagsPopulatedResourceCondition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class CRRecipeProvider extends FabricRecipeProvider {

    public CRRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput output) {
        return new RecipeProvider(registryLookup, output) {
            @Override
            public void buildRecipes() {
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/foods/cookies")), new CompostableRecipe(of(ConventionalItemTags.COOKIE_FOODS), 0.85f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/crops")), new CompostableRecipe(of(ConventionalItemTags.CROPS), 0.3f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/crops/cactus")), new CompostableRecipe(of(ConventionalItemTags.CACTUS_CROPS), 0.5f, 2), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/crops/pumpkin")), new CompostableRecipe(of(ConventionalItemTags.PUMPKIN_CROPS), 0.65f, 2), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/flowers/tall")), new CompostableRecipe(of(ConventionalItemTags.TALL_FLOWERS), 0.65f, 2), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/foods/berries")), new CompostableRecipe(of(ConventionalItemTags.BERRY_FOODS), 0.3f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/foods/breads")), new CompostableRecipe(of(ConventionalItemTags.BREAD_FOODS), 0.85f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/foods/fruits")), new CompostableRecipe(of(ConventionalItemTags.FRUIT_FOODS), 0.3f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/foods/pies")), new CompostableRecipe(of(ConventionalItemTags.PIE_FOODS), 1.0f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/foods/vegetables")), new CompostableRecipe(of(ConventionalItemTags.VEGETABLE_FOODS), 0.3f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/mushrooms")), new CompostableRecipe(of(ConventionalItemTags.MUSHROOMS), 0.3f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/seeds")), new CompostableRecipe(of(ConventionalItemTags.SEEDS), 0.3f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("storage_blocks/dried_kelp")), new CompostableRecipe(of(ConventionalItemTags.STORAGE_BLOCKS_DRIED_KELP), 1.0f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("storage_blocks/wheat")), new CompostableRecipe(of(ConventionalItemTags.STORAGE_BLOCKS_WHEAT), 1.0f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("minecraft/flowers")), new CompostableRecipe(of(ItemTags.FLOWERS), 0.3f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("minecraft/leaves")), new CompostableRecipe(of(ItemTags.LEAVES), 0.3f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("minecraft/saplings")), new CompostableRecipe(of(ItemTags.SAPLINGS), 0.3f), null);
                output.accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("minecraft/wart_blocks")), new CompostableRecipe(of(ItemTags.WART_BLOCKS), 0.85f), null);

                withConditions(output, new TagsPopulatedResourceCondition(cTag("common/grass")))
                    .accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/grass")), new CompostableRecipe(of(cTag("grass")), 0.3f), null);
                withConditions(output, new TagsPopulatedResourceCondition(cTag("common/grass_like")))
                    .accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/grass_like")), new CompostableRecipe(of(cTag("grass_like")), 0.3f), null);
                withConditions(output, new TagsPopulatedResourceCondition(cTag("common/grass_variants")))
                    .accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("common/grass_variants")), new CompostableRecipe(of(cTag("grass_variants")), 0.3f), null);
                withConditions(output, new TagsPopulatedResourceCondition(cTag("storage_blocks/rice")))
                    .accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("storage_blocks/rice")), new CompostableRecipe(of(cTag("storage_blocks/rice")), 1.0f), null);
                withConditions(output, new TagsPopulatedResourceCondition(cTag("storage_blocks/sugar_cane")))
                    .accept(ResourceKey.create(Registries.RECIPE, CompostableRecipes.id("storage_blocks/sugar_cane")), new CompostableRecipe(of(cTag("storage_blocks/sugar_cane")), 1.0f), null);
            }

            private TagKey<Item> cTag(String path) {
                return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TagUtil.C_TAG_NAMESPACE, path));
            }

            private Ingredient of(TagKey<Item> tag) {
                return Ingredient.of(registryLookup.lookupOrThrow(Registries.ITEM).get(tag).get());
            }

            private Ingredient of(ItemLike... items) {
                return Ingredient.of(items);
            }
        };
    }

    @Override
    public String getName() {
        return "[CompostableRecipes] CRRecipeProvider";
    }


}
