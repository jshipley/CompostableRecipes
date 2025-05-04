# Compostable Recipes

**This will only be released for Fabric, as Neoforge already has an easy data-driven way to add compostable items.**

Adds new compostable recipes that can make any item compostable.

Item tags are supported, and many minecraft and common item tags have already been added. Some seeds, crops, breads, and other things may just start working with the composter if this mod is installed.

The recipe format is pretty standard:

````
{
    "type": "compostablerecipes":"compostable",
    "ingredient": {
        "item": "minecraft:leather",
        "chance": "0.5"
    }
}
````

or with a tag:
````
{
    "type": "compostablerecipes":"compostable",
    "ingredient": {
        "tag": "c:seeds",
        "chance": "0.3"
    }
}
````

There's also an optional "weight" key that can be used to set a recipe's weight. This is mostly for conflict resolution. For example, if "c:crops" have a weight of 1 and "c:crops/pumpkin" has a weight of 2 then the chance value for pumpkins will be from the "c:crops/pumpkin" tag.

All vanilla compostable items have a default weight of 10, so if you want to change those values you can add a new recipe with a weight higher than 10.
