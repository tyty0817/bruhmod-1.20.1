package name.bruhmod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;

import static name.bruhmod.Mod.MOD_ID;

public class NaturalRecipe implements Recipe<NaturalRecipe.NaturalInventory> {

    public static void register() {

    }

    public static final RecipeSerializer<NaturalRecipe> SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MOD_ID, "natural"), new Serializer());
    public static final RecipeType<NaturalRecipe> TYPE = registerRecipeType("natural");

    private final RegistryEntry<DamageType> damageType;
    private final DefaultedList<IngredientStack> input;
    private final ItemStack output;

    public NaturalRecipe(RegistryEntry<DamageType> damageType, DefaultedList<IngredientStack> input, ItemStack output) {
        this.damageType = damageType;
        this.input = input;
        this.output = output;
    }

    /**
     * Check if this recipe matches with the ingredients from the specified inventory.
     * @param inventory The inventory the recipe should compare ingredients against.
     * @return The list of slots of which items should be removed from when crafting, or an empty list specifying that there was no match.
     */
    public List<Pair<Integer, Integer>> matchWithRemovals(NaturalInventory inventory) {
        if (!inventory.source.getTypeRegistryEntry().matches(this.damageType)) {
            return new ArrayList<>();
        }

        ArrayList<Pair<Integer, Integer>> removals = new ArrayList<>();

        for (IngredientStack stack : this.input) {
            int remaining = stack.count();
            for (int slot = 0; slot < inventory.getSize(); slot++) {
                ItemStack invStack = inventory.getStackInSlot(slot);
                if (stack.ingredient.test(invStack)) {
                    int taken = Math.min(remaining, invStack.getCount());
                    removals.add(new Pair<>(slot, taken));
                    remaining -= taken;
                }
            }
            if (remaining > 0) {
                return new ArrayList<>();
            }
        }
        return removals;
    }

    public static void craftAtPosition(World world, Vec3d pos, DamageSource source) {
        NaturalInventory inventory = new NaturalInventory(
                source,
                world.getNonSpectatingEntities(ItemEntity.class, new Box(pos.subtract(3.0, 3.0, 3.0), pos.add(3.0, 3.0, 3.0)))
        );

        // get all matched recipes
        // then sort them by most ingredients first (in case of duplicate ingredients, the larger one should be used)
        // then, for each recipe, get the times it should be crafted, removing items used each time
        // then spawn the item after

        world.getRecipeManager().getAllMatches(TYPE, inventory, world).stream().sorted(Comparator.comparingInt(recipe -> -recipe.value().input.stream().mapToInt(IngredientStack::count).sum())).forEachOrdered(recipe -> {
            List<Pair<Integer, Integer>> removals;
            int times = 0;
            while (!(removals = recipe.value().matchWithRemovals(inventory)).isEmpty()) {
                removals.forEach(pair -> inventory.removeStack(pair.getLeft(), pair.getRight()));
                times++;
            }
            ItemStack output = recipe.value().getResult(world.getRegistryManager());
            output.setCount(output.getCount() * times);
            ItemEntity entity = new ItemEntity(world, pos.x, pos.y, pos.z, output, 0.0, 0.3, 0.0);
            entity.setInvulnerable(true);
            world.spawnEntity(entity);
        });
    }

    @Override
    public boolean matches(NaturalInventory inventory, World world) {
        return !matchWithRemovals(inventory).isEmpty();
    }

    @Override
    public ItemStack craft(NaturalInventory inventory, RegistryWrapper.WrapperLookup registryManager) {
        return this.getResult(registryManager).copy();
    }

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String path) {
        Identifier id = Identifier.of(MOD_ID, path);
        return Registry.register(Registries.RECIPE_TYPE, id, new RecipeType<T>(){

            public String toString() {
                return id.toString();
            }
        });
    }

    public static class NaturalInventory implements RecipeInput {

        public final DamageSource source;
        private final ArrayList<ItemEntity> items;

        public NaturalInventory(DamageSource source, List<ItemEntity> entities) {
            this.source = source;
            this.items = new ArrayList<>(entities);
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
            ItemEntity e = this.items.get(slot);
            ItemStack stack = e.getStack();
            if (!e.isAlive() || stack == null) {
                return ItemStack.EMPTY;
            } else return stack;
        }

        @Override
        public int getSize() {
            return this.items.size();
        }

        public ItemStack removeStack(int slot, int amount) {
            ItemEntity e = this.items.get(slot);
            if (!e.isAlive()) {
                return ItemStack.EMPTY;
            }
            ItemStack newStack = e.getStack().split(amount);
            if (e.getStack().isEmpty()) {
                e.discard();
            }
            return newStack;
        }
    }

    public record IngredientStack(Ingredient ingredient, int count) {

        public static final IngredientStack EMPTY = new IngredientStack(Ingredient.EMPTY, 0);

        public static final Codec<IngredientStack> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(IngredientStack::ingredient),
                        Codecs.rangedInt(1, 99).fieldOf("count").orElse(1).forGetter(IngredientStack::count)
                ).apply(instance, IngredientStack::new)
        );

        public static final PacketCodec<RegistryByteBuf, IngredientStack> PACKET_CODEC = new PacketCodec<>() {

            public IngredientStack decode(RegistryByteBuf buf) {
                int count = buf.readVarInt();
                if (count <= 0)
                    return EMPTY;
                else return new IngredientStack(Ingredient.PACKET_CODEC.decode(buf), count);
            }

            public void encode(RegistryByteBuf buf, IngredientStack stack) {
                buf.writeVarInt(stack.count);
                if (stack.count > 0) {
                    Ingredient.PACKET_CODEC.encode(buf, stack.ingredient);
                }
            }
        };
    }

    public static class Serializer
            implements RecipeSerializer<NaturalRecipe> {

        public static final MapCodec<NaturalRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                DamageType.ENTRY_CODEC.fieldOf("damageType").forGetter((recipe) -> recipe.damageType),
                IngredientStack.CODEC.listOf().fieldOf("ingredients").flatXmap(list -> {
                    DefaultedList<IngredientStack> new_list = DefaultedList.of();
                    new_list.addAll(list);
                    return DataResult.success(new_list);
                }, DataResult::success).forGetter((recipe) -> recipe.input),
                ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter((recipe) -> recipe.output)
        ).apply(instance, NaturalRecipe::new));

        public static final PacketCodec<RegistryByteBuf, NaturalRecipe> PACKET_CODEC = new PacketCodec<>() {

            @Override
            public void encode(RegistryByteBuf buf, NaturalRecipe value) {
                DamageType.ENTRY_PACKET_CODEC.encode(buf, value.damageType);
                buf.writeVarInt(value.input.size());
                value.input.forEach(stack -> IngredientStack.PACKET_CODEC.encode(buf, stack));
                ItemStack.PACKET_CODEC.encode(buf, value.output);
            }

            public NaturalRecipe decode(RegistryByteBuf buf) {
                RegistryEntry<DamageType> damageType = DamageType.ENTRY_PACKET_CODEC.decode(buf);
                int inputSize = buf.readVarInt();
                DefaultedList<IngredientStack> input = DefaultedList.ofSize(inputSize, IngredientStack.EMPTY);
                input.replaceAll((i) -> IngredientStack.PACKET_CODEC.decode(buf));
                ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
                return new NaturalRecipe(damageType, input, output);
            }
        };

        @Override
        public MapCodec<NaturalRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, NaturalRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }

    @Override
    public boolean fits(int width, int height) { return true; }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registryManager) {
        return getOutput();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }

    public DefaultedList<IngredientStack> getInput() {
        DefaultedList<IngredientStack> ing = DefaultedList.of();
        ing.addAll(input);
        return ing;
    }

    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> ing = DefaultedList.of();
        this.input.stream().map(IngredientStack::ingredient).forEach(ing::add);
        return ing;
    }
}
