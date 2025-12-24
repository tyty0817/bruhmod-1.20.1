package name.bruhmod.util;

import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

public class RegistryHelper<T> {

    public final LinkedHashMap<ResourceLocation, T> values = new LinkedHashMap<>();

    public <U extends T> U add(ResourceLocation id, U item) {
        if (this.values.put(id, item) != null) {
            throw new IllegalArgumentException("Duplicate " + item.getClass().getName() + " id " + id);
        }
        return item;
    }

    public void registerAll(BiConsumer<ResourceLocation, T> registerer) {
        this.values.forEach(registerer::accept);
    }

    public ResourceLocation getKey(T item) throws NoSuchElementException {
        return this.values.entrySet().stream().filter(e -> e.getValue().equals(item)).findFirst().orElseThrow(() -> new NoSuchElementException("No element for " + item)).getKey();
    }

}
