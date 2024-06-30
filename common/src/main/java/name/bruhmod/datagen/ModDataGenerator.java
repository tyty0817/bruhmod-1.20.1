package name.bruhmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;

import java.util.concurrent.CompletableFuture;

public class ModDataGenerator {

	public interface AddProvider {
		<T extends DataProvider> T addProvider(DataProvider.Factory<T> factory);
	}

	public static <T extends DataProvider> void generate(AddProvider pack, CompletableFuture<HolderLookup.Provider> future) {
		var blockTagProvider = pack.addProvider((output) -> new ModBlockTagProvider(output, future));
		pack.addProvider((output) -> new ModItemTagProvider(output, future, blockTagProvider.contentsGetter()));
		pack.addProvider((output) -> new ModRecipeProvider(output, future));
		pack.addProvider((output) -> new ModPoiTagProvider(output, future));
		pack.addProvider(ModBlockStateProvider::new);
		pack.addProvider(ModItemModelProvider::new);
		pack.addProvider((output) -> new ModBlockLootProvider(output, future));
		pack.addProvider((output) -> ModAdvancementProvider.provider(output, future));
	}

}
