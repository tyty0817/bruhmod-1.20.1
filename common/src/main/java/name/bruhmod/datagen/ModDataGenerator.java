package name.bruhmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import java.util.concurrent.CompletableFuture;

public class ModDataGenerator {

	public static void generate(DataGenerator.PackGenerator pack, CompletableFuture<HolderLookup.Provider> future) {
		var blockTagProvider = pack.addProvider((output) -> new ModBlockTagProvider(output, future));
		pack.addProvider((output) -> new ModItemTagProvider(output, future, blockTagProvider.contentsGetter()));
		pack.addProvider((output) -> new ModRecipeProvider(output, future));
		pack.addProvider((output) -> new ModPoiTagProvider(output, future));
		pack.addProvider((output) -> new ModLootTableProvider(output, future));
		pack.addProvider((output) -> ModAdvancementProvider.provider(output, future));
	}

}
