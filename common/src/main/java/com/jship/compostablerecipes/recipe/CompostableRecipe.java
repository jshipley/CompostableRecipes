package com.jship.compostablerecipes.recipe;

import com.jship.compostablerecipes.CompostableRecipes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

public record CompostableRecipe(Ingredient ingredient, float chance, int weight) implements Recipe<SingleRecipeInput> {

    public CompostableRecipe(Ingredient ingredient, float chance) {
        this(ingredient, chance, 1);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return CompostableRecipes.COMPOSTABLE_CATEGORY.get();
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input, Provider registries) {
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return CompostableRecipes.COMPOSTABLE_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return CompostableRecipes.COMPOSTABLE_RECIPE.get();
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return ingredient.test(input.item());
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(ingredient);
    }

    public static class Serializer implements RecipeSerializer<CompostableRecipe> {

        public static final MapCodec<CompostableRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance
                .group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(CompostableRecipe::ingredient),
                    Codec.FLOAT.fieldOf("chance").forGetter(CompostableRecipe::chance),
                    Codec.INT.optionalFieldOf("weight", 1).forGetter(CompostableRecipe::weight)
                )
                .apply(instance, CompostableRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, CompostableRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CompostableRecipe::ingredient,
            ByteBufCodecs.FLOAT, CompostableRecipe::chance,
            ByteBufCodecs.INT, CompostableRecipe::weight,
            CompostableRecipe::new
        );

        @Override
        public MapCodec<CompostableRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CompostableRecipe> streamCodec() {
            return STREAM_CODEC;
        }

    }
}
