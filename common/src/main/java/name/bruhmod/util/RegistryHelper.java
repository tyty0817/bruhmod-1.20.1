package name.bruhmod.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public void registerAll(Provider registerer) {
        this.values.forEach((k, v) -> registerer.register(registry, k, v));
    }

    public ResourceLocation getKey(T item) throws NoSuchElementException {
        return this.values.entrySet().stream().filter(e -> e.getValue().equals(item)).findFirst().orElseThrow(() -> new NoSuchElementException("No element for " + item)).getKey();
    }

    public interface Provider {
        <T> Holder<T> register(Registry<T> registry, ResourceLocation id, T entry);
    }

}
