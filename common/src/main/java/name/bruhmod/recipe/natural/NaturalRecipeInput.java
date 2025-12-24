package name.bruhmod.recipe.natural;

import name.bruhmod.recipe.util.WorldRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class NaturalRecipeInput extends WorldRecipeInput {

    public final NaturalSources source;

    public NaturalRecipeInput(NaturalSources source, Level level, AABB bounds) {
        super(level, bounds);
        this.source = source;
    }
}
