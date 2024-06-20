package name.bruhmod.recipe;

import com.google.gson.*;
import name.bruhmod.Mod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;
import java.util.stream.StreamSupport;

import static name.bruhmod.Mod.MOD_ID;

public class NaturalRecipe implements Recipe<NaturalRecipe.NaturalInventory> {

    public static void register() {

    }

    public static final RecipeSerializer<NaturalRecipe> SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "natural"), new Serializer());

    public static final RecipeType<NaturalRecipe> TYPE = registerRecipeType("natural");

    private final Identifier id;
    final RegistryKey<DamageType> damageType;
    final String group;
    final DefaultedList<Pair<Ingredient, Integer>> input;
    final ItemStack output;

    public NaturalRecipe(Identifier id, String group, RegistryKey<DamageType> damageType, DefaultedList<Pair<Ingredient, Integer>> input, ItemStack output) {
        this.id = id;
        this.group = group;
        this.damageType = damageType;
        this.input = input;
        this.output = output;
    }

    /**
     * Check if this recipe matches with the ingredients from the specified inventory.
     * @param inventory The inventory the recipe should compare ingredients against.
     * @return The list of slots of which items should be removed from when crafting, or an empty list specifying that there was no match.
     */
    public List<Pair<Integer, Integer>> matchWithRemovals(Inventory inventory) {
        ArrayList<Pair<Integer, Integer>> removals = new ArrayList<>();

        for (Pair<Ingredient, Integer> ing : this.input) {
            int remaining = ing.getRight();
            for (int slot = 0; slot < inventory.size(); slot++) {
                ItemStack invStack = inventory.getStack(slot);
                if (ing.getLeft().test(invStack)) {
                    int taken = Math.min(ing.getRight(), invStack.getCount());
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

        world.getRecipeManager().getAllMatches(TYPE, inventory, world).stream().sorted(Comparator.comparingInt(recipe -> -recipe.input.stream().mapToInt(Pair::getRight).sum())).forEachOrdered(recipe -> {
            List<Pair<Integer, Integer>> removals;
            int times = 0;
            while (!(removals = recipe.matchWithRemovals(inventory)).isEmpty()) {
                removals.forEach(pair -> inventory.removeStack(pair.getLeft(), pair.getRight()));
                times++;
            }
            ItemStack output = recipe.getOutput(world.getRegistryManager());
            output.setCount(output.getCount() * times);
            ItemEntity entity = new ItemEntity(world, pos.x, pos.y, pos.z, output, 0.0, 0.2, 0.0);
            entity.setInvulnerable(true);
            world.spawnEntity(entity);
        });
    }

    @Override
    public boolean matches(NaturalInventory inventory, World world) {
        return !matchWithRemovals(inventory).isEmpty();
    }

    @Override
    public ItemStack craft(NaturalInventory inventory, DynamicRegistryManager registryManager) {
        return this.getOutput(registryManager).copy();
    }

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String path) {
        Identifier id = new Identifier(MOD_ID, path);
        return Registry.register(Registries.RECIPE_TYPE, id, new RecipeType<T>(){

            public String toString() {
                return id.toString();
            }
        });
    }

    public static class NaturalInventory implements Inventory {

        public final DamageSource source;
        private final ArrayList<ItemEntity> items;

        public NaturalInventory(DamageSource source, List<ItemEntity> entities) {
            this.source = source;
            this.items = new ArrayList<>(entities);
        }

        @Override
        public int size() {
            return this.items.size();
        }

        @Override
        public boolean isEmpty() {
            return this.size() != 0;
        }

        @Override
        public ItemStack getStack(int slot) {
            ItemEntity e = this.items.get(slot);
            ItemStack stack = e.getStack();
            if (!e.isAlive() || stack == null) {
                return ItemStack.EMPTY;
            } else return stack;
        }

        @Override
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

        @Override
        public ItemStack removeStack(int slot) {
            ItemEntity e = this.items.get(slot);
            ItemStack stack = e.getStack();
            e.discard();
            return stack;
        }

        @Override
        public void setStack(int slot, ItemStack stack) {
            Mod.LOGGER.error("cannot add stack to natural inventory");
        }

        @Override
        public void markDirty() {

        }

        @Override
        public boolean canPlayerUse(PlayerEntity player) {
            return false;
        }

        @Override
        public void clear() {
            items.forEach(Entity::discard);
        }
    }

    public static class Serializer
            implements RecipeSerializer<NaturalRecipe> {

        private static DefaultedList<Pair<Ingredient, Integer>> getIngredients(JsonArray array) throws JsonParseException {
            DefaultedList<Pair<Ingredient, Integer>> defaultedList = DefaultedList.of();
            StreamSupport.stream(array.spliterator(), true).filter(JsonElement::isJsonObject).forEach(e -> {
                JsonObject obj = e.getAsJsonObject();
                Ingredient i = Ingredient.fromJson(obj, false);
                JsonElement count = obj.get("count");
                int c;
                if (count == null) {
                    c = 1;
                } else if (!count.isJsonPrimitive() || !count.getAsJsonPrimitive().isNumber()) {
                    throw new JsonSyntaxException("Expected item count number");
                } else {
                    c = count.getAsInt();
                    if (c <= 0) {
                        throw new JsonSyntaxException("Expected positive item count");
                    }
                }
                defaultedList.add(new Pair<>(i, c));
            });
            return defaultedList;
        }

        @Override
        public NaturalRecipe read(Identifier identifier, JsonObject jsonObject) {
            String group = JsonHelper.getString(jsonObject, "group", "");
            RegistryKey<DamageType> damageType = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(JsonHelper.getString(jsonObject, "damageType")));
//            CraftingRecipeCategory craftingRecipeCategory = CraftingRecipeCategory.CODEC.byId(JsonHelper.getString(jsonObject, "category", null), CraftingRecipeCategory.MISC);
            DefaultedList<Pair<Ingredient, Integer>> defaultedList = getIngredients(JsonHelper.getArray(jsonObject, "ingredients"));
            if (defaultedList.isEmpty()) {
                throw new JsonParseException("No ingredients for lightning recipe");
            }
            ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
            return new NaturalRecipe(identifier, group, damageType, defaultedList, itemStack);
        }

        @Override
        public NaturalRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            String group = packetByteBuf.readString();
            RegistryKey<DamageType> damageType = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(packetByteBuf.readString()));
            int i = packetByteBuf.readVarInt();
            DefaultedList<Pair<Ingredient, Integer>> defaultedList = DefaultedList.ofSize(i, new Pair<>(Ingredient.EMPTY, 1));
            defaultedList.replaceAll(ignored -> new Pair<>(Ingredient.fromPacket(packetByteBuf), packetByteBuf.readInt()));
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new NaturalRecipe(identifier, group, damageType, defaultedList, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, NaturalRecipe recipe) {
            packetByteBuf.writeString(recipe.group);
            packetByteBuf.writeString(recipe.damageType.getValue().toString());
//            packetByteBuf.writeEnumConstant(recipe.category);
            packetByteBuf.writeVarInt(recipe.input.size());
            for (Pair<Ingredient, Integer> ingredient : recipe.input) {
                ingredient.getLeft().write(packetByteBuf);
                packetByteBuf.writeLong(ingredient.getRight());
            }
            packetByteBuf.writeItemStack(recipe.output);
        }
    }

    @Override
    public boolean fits(int width, int height) { return true; }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }


}
