package name.bruhmod.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RegistryHelper<T> {

    public final LinkedHashMap<ResourceLocation, T> values = new LinkedHashMap<>();
    private final Registry<T> registry;

    public RegistryHelper(Registry<T> registry) {
        this.registry = registry;
    }

    public <U extends T> U add(ResourceLocation id, U item) {
        if (this.values.put(id, item) != null) {
            throw new IllegalArgumentException("Typo? Duplicate id " + id + " for " + item.getClass().getName());
        }
        return item;
    }

    public void registerAll(RegistryConsumer registerer) {
        registerer.register(registry, this.values::forEach);
    }

    public ResourceLocation getKey(T item) throws NoSuchElementException {
        return this.values.entrySet().stream().filter(e -> e.getValue().equals(item)).findFirst().orElseThrow(() -> new NoSuchElementException("No element for " + item)).getKey();
    }

    public interface RegistryConsumer {
        <T> void register(Registry<T> registry, Consumer<BiConsumer<ResourceLocation, T>> consumer);

        default <T> void register(Registry<? super T> registry, ResourceLocation l, T t) {
            this.register(registry, single -> single.accept(l, t));
        }
    }

}
