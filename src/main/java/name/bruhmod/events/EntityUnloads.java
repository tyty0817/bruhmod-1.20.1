package name.bruhmod.events;

import name.bruhmod.items.ModItems;
import name.bruhmod.potion.ModPotions;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class EntityUnloads implements ServerEntityEvents.Unload {

    @Override
    public void onUnload(Entity entity, ServerWorld world) {

        if(entity instanceof PotionEntity potion){
            ItemStack emptyGemPresent = null;
            ItemStack cryingObsidianPresent = null;

            ItemStack itemStack = potion.getStack();
            PotionContentsComponent potionContentsComponent = (PotionContentsComponent)itemStack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
            Potion type = potionContentsComponent.potion().get().value();
            Vec3d pos = entity.getPos();
            if(type.equals(ModPotions.POTION_OF_CORRUPTION)) {
                List<Entity> list = world.getOtherEntities(null,
                        new Box(pos.getX() - 2.0, pos.getY() - 1, pos.getZ() - 2.0, pos.getX() + 2.0, pos.getY() + 1.0, pos.getZ() + 2.0));
                for (int i = 0; i < list.size(); i++) {
                    Entity element = list.get(i);
                    if (element.getType() == EntityType.ITEM) {
                        ItemEntity item = (ItemEntity) element;
                        if(item.getStack().getItem().equals(ModItems.EMPTY_GEM)) {
                            emptyGemPresent = item.getStack();
                            item.getPos();
                        }
                        if(item.getStack().getItem().equals(Items.CRYING_OBSIDIAN)){
                            cryingObsidianPresent = item.getStack();
                            item.getPos();
                        }
                    }
                }
                if(cryingObsidianPresent != null && emptyGemPresent != null){
                    cryingObsidianPresent.decrement(1);
                    emptyGemPresent.decrement(1);
                    world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, new ItemStack(ModItems.JEWEL_OF_CORRUPTION)));
                }
            }
        }
    }
}