package name.bruhmod.entity;

import com.mojang.datafixers.util.Pair;
import name.bruhmod.recipe.staff.StaffRecipe;
import name.bruhmod.recipe.staff.StaffRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public final class StaffRecipeTicker extends Entity {

    private static final EntityDataAccessor<BlockPos> NOTE_BLOCK = SynchedEntityData.defineId(StaffRecipeTicker.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<ItemStack> RETURN_STACK = SynchedEntityData.defineId(StaffRecipeTicker.class, EntityDataSerializers.ITEM_STACK);
    private int age = 0;

    public StaffRecipeTicker(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public StaffRecipeTicker(Level level, Pair<StaffRecipeInput, StaffRecipe> recipePair) {
        super(ModEntities.STAFF_RECIPE, level);
        StaffRecipeInput input = recipePair.getFirst();
        this.setNoteBlock(input.pos);
        this.setReturnStack(recipePair.getSecond().getOutput());
        this.noPhysics = true;
    }

    private void setReturnStack(ItemStack output) {
        this.entityData.set(RETURN_STACK, output);
    }

    public void setNoteBlock(BlockPos pos) {
        this.entityData.set(NOTE_BLOCK, pos);
    }

    public BlockPos getNoteBlock() {
        return this.entityData.get(NOTE_BLOCK);
    }

    public ItemStack getReturnStack() {
        return this.entityData.get(RETURN_STACK);
    }

    @Override
    public void tick() {

        BlockPos pos = this.getNoteBlock();
        Level level = this.level();

        if (this.age % 4 == 0) {
            BlockState state = level.getBlockState(pos);
            state.setValue(NoteBlock.NOTE, this.age / 4);
            level.blockEvent(pos, level.getBlockState(pos).getBlock(), 0, 0);
            level.gameEvent(null, GameEvent.NOTE_BLOCK_PLAY, pos);
        }

        if (this.age >= 4 * 24) {
            Vec3 itemPos = Vec3.atCenterOf(pos.above());
            ItemEntity drop = new ItemEntity(level, itemPos.x, itemPos.y, itemPos.z, this.getReturnStack());
            level.addFreshEntity(drop);
            this.discard();
        }

        this.age++;

        super.tick();

    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(NOTE_BLOCK, BlockPos.ZERO);
        builder.define(RETURN_STACK, ItemStack.EMPTY);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {

    }
}
