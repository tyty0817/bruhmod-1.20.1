package name.bruhmod.recipe.util;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class WorldRecipeInput implements RecipeInput {

    public final ArrayList<ItemEntity> items;

    public WorldRecipeInput(Level level, AABB bounds) {
        this.items = new ArrayList<>(level.getEntitiesOfClass(ItemEntity.class, bounds));
    }

    public Stream<ItemStack> getItems() {
        return this.items.stream().map(ItemEntity::getItem);
    }

    @Override
    public @NotNull ItemStack getItem(int slot) {
        ItemEntity e = this.items.get(slot);
        ItemStack stack = e.getItem();
        if (!e.isAlive()) {
            return ItemStack.EMPTY;
        } else return stack;
    }

    @Override
    public int size() {
        return this.items.size();
    }

    public ItemStack removeStack(int slot, int amount) {
        ItemEntity e = this.items.get(slot);
        if (!e.isAlive()) {
            return ItemStack.EMPTY;
        }
        ItemStack newStack = e.getItem().split(amount);
        if (e.getItem().isEmpty()) {
            e.discard();
        }
        return newStack;
    }
}