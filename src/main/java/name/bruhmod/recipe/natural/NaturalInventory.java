package name.bruhmod.recipe.natural;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NaturalInventory implements RecipeInput {

    public final NaturalSources source;
    private final ArrayList<ItemEntity> items;

    public NaturalInventory(NaturalSources source, List<ItemEntity> entities) {
        this.source = source;
        this.items = new ArrayList<>(entities);
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