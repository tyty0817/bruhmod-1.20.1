package name.bruhmod.datagen;

import com.google.gson.JsonElement;
import name.bruhmod.LeMod;
import name.bruhmod.item.ModItems;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelGenerators implements DataProvider {

    private final PackOutput packOutput;
    private final Map<ResourceLocation, Supplier<JsonElement>> models;

    public ModItemModelProvider(PackOutput packOutput) {
        this(packOutput, new HashMap<>());
    }

    private ModItemModelProvider(PackOutput packOutput, Map<ResourceLocation, Supplier<JsonElement>> models) {
        super(models::put);
        this.packOutput = packOutput;
        this.models = models;
    }

    public void generate() {

        /*
        ITEMS
         */
        this.generateFlatItem(ModItems.MYTHRIL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MYTHRIL_DUST, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MYTHRIL_FRAGMENT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MYTHRIL_UPGRADE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.EMPTY_GEM, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.EXPLOSIVE_JELLY, ModelTemplates.FLAT_ITEM);


        this.generateFlatItem(ModItems.BARRACKS_MAP, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.CARPENTER_MAP, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.WITCH_HUT_MAP, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.WIZARD_TOWER_MAP, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.PORTAL_TOWER_MAP, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MESS_HALL_MAP, ModelTemplates.FLAT_ITEM);


        this.generateFlatItem(ModItems.CORRUPTED_SLAG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MYSTERIOUS_CLUB, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.CORRUPTED_CROWN, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.JEWEL_OF_CORRUPTION, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BRANCH_OF_CORRUPTION, ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.LIGHTNING_IN_A_BOTTLE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.VOLATILE_CLAW, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.CHARGED_CLAW, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.VOLATILE_PILLAR, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MAELSTROM, ModelTemplates.FLAT_HANDHELD_ITEM);

        this.generateFlatItem(ModItems.SPIRITED_BLUDGEON, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.PRONGED_CROWN, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.WIND_GEM, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.WHIRLWIND_SASH, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MONKS_CUDGEL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.SHILLELAGH, ModelTemplates.FLAT_HANDHELD_ITEM);

        this.generateFlatItem(ModItems.CLOUD_IN_A_BOTTLE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.UNSTABLE_GEM, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.DYING_LIGHT, ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.MUSIC_DISC_FALLOUT, ModelTemplates.MUSIC_DISC);
        this.generateFlatItem(ModItems.MUSIC_DISC_BLACK_MOON, ModelTemplates.MUSIC_DISC);
        this.generateFlatItem(ModItems.MUSIC_DISC_DARK_WOODS, ModelTemplates.MUSIC_DISC);
        this.generateFlatItem(ModItems.MUSIC_DISC_NIGHT_OWL, ModelTemplates.MUSIC_DISC);
        this.generateFlatItem(ModItems.MUSIC_DISC_OLD_KING, ModelTemplates.MUSIC_DISC);
        this.generateFlatItem(ModItems.MUSIC_DISC_THE_RANGER, ModelTemplates.MUSIC_DISC);



        this.generateFlatItem(ModItems.BRITISH_MAN_SPAWN_EGG,
                new ModelTemplate(Optional.of(ResourceLocation.parse("item/template_spawn_egg")), Optional.empty()));


        /*
        ARMOR
         */
        ModItems.MYTHRIL_STUDDED_ARMOR.values().forEach(this::generateArmorTrims);
        ModItems.MYTHRIL_INFUSED_ARMOR.values().forEach(this::generateArmorTrims);



    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        generate();

        PackOutput.PathProvider modelPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");

        return CompletableFuture.allOf(models.entrySet().stream().map((e) -> DataProvider.saveStable(cache, e.getValue().get(), modelPathProvider.json(e.getKey()))).toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return LeMod.MOD_ID + " Item Models";
    }
}
