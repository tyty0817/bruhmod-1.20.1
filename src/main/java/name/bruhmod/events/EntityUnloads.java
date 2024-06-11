package name.bruhmod.events;

import name.bruhmod.items.ModItems;
import name.bruhmod.potion.ModPotions;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class EntityUnloads implements ServerEntityEvents.Unload {

    @Override
    public void onUnload(Entity entity, ServerWorld world) {
        if(entity instanceof LightningEntity && EntityLoads.getlightningInABottleLightningID() == entity.getId()){
            Vec3d pos = entity.getPos();
            world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, new ItemStack(ModItems.LIGHTNING_IN_A_BOTTLE)));
        }
        if(entity instanceof LightningEntity && EntityLoads.getWindGemLightningID() == entity.getId()){
            Vec3d pos = entity.getPos();
            world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, new ItemStack(ModItems.WIND_GEM)));
        }

        if(entity instanceof PotionEntity potion){
            ItemStack emptyGemPresent = null;
            ItemStack cryingObsidianPresent = null;

            ItemStack itemStack = potion.getStack();
            Potion type = PotionUtil.getPotion(itemStack);
            Vec3d pos = entity.getPos();
            if(type.equals(ModPotions.POTION_OF_CORRUPTION)) {//|| type.equals(Potions.LONG_POISON) || type.equals(Potions.STRONG_POISON)){
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