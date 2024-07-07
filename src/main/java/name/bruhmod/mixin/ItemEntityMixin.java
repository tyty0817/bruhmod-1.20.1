package name.bruhmod.mixin;

import name.bruhmod.items.ModItems;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.EntityPositionSource;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.PositionSource;
import net.minecraft.world.event.listener.EntityGameEventHandler;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity{
    int playTime = 0;
    boolean isplaying = false;
    private @Nullable BlockPos jukeboxPos;
    EntityPositionSource eps = null;
    private EntityGameEventHandler<JukeboxEventListener> jukeboxEventHandler;

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    public void updateJukeboxPos(BlockPos jukeboxPos, boolean playing) {
        if (playing) {
            if (!this.isplaying) {
                this.jukeboxPos = jukeboxPos;
                this.isplaying = true;
            }
        } else if (jukeboxPos.equals(this.jukeboxPos) || this.jukeboxPos == null) {
            this.jukeboxPos = null;
            this.isplaying = false;
        }
    }
    public void updateEventHandler(BiConsumer<EntityGameEventHandler<?>, ServerWorld> callback) {
        if(jukeboxEventHandler != null) {
            World var3 = this.getWorld();
            if (var3 instanceof ServerWorld serverWorld) {
                callback.accept(this.jukeboxEventHandler, serverWorld);
            }
        }
    }

    private class JukeboxEventListener implements GameEventListener {
        private final PositionSource positionSource;
        private final int range;

        public JukeboxEventListener(PositionSource positionSource, int range) {
            this.positionSource = positionSource;
            this.range = range;
        }

        public PositionSource getPositionSource() {
            return this.positionSource;
        }

        public int getRange() {
            return this.range;
        }

        public boolean listen(ServerWorld world, GameEvent event, GameEvent.Emitter emitter, Vec3d emitterPos) {
            if (event == GameEvent.JUKEBOX_PLAY) {
                ItemEntityMixin.this.updateJukeboxPos(BlockPos.ofFloored(emitterPos), true);
                return true;
            } else if (event == GameEvent.JUKEBOX_STOP_PLAY) {
                ItemEntityMixin.this.updateJukeboxPos(BlockPos.ofFloored(emitterPos), false);
                return true;
            } else {
                return false;
            }
        }
    }

    @Inject(method = "setStack", at = @At(value = "HEAD"))
    public void setStack(ItemStack stack, CallbackInfo ci) {
        if(stack.getItem().equals(ModItems.EMPTY_GEM)){
            eps = new EntityPositionSource(this, 0);
            jukeboxEventHandler = new EntityGameEventHandler(new JukeboxEventListener(new EntityPositionSource(this, 0), GameEvent.JUKEBOX_PLAY.getRange()));
        }
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void tick(CallbackInfo ci) {
        if(isplaying){
            playTime++;
            if(playTime % 20 == 0){
                if(this.getWorld() instanceof ServerWorld server){
                    server.spawnParticles(ParticleTypes.END_ROD, this.getX(), this.getY() + .5, this.getZ(), 1, 0, 0, 0, 0);
                }
            }
            if(playTime == 100){
                if(this.getWorld().getBlockEntity(jukeboxPos) instanceof JukeboxBlockEntity JBEntity){
                    System.out.println(JBEntity.getStack().getItem());
                }
                playTime = 0;
                this.getStack().decrement(1);
                this.getWorld().spawnEntity(new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.WIND_GEM)));
            }
        }else{
            playTime = 0;
        }
    }
    @Shadow
    public abstract ItemStack getStack();
    @Shadow
    public abstract Entity getOwner();
}