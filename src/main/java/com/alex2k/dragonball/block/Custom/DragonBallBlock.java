package com.alex2k.dragonball.block.Custom;

import com.alex2k.dragonball.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DragonBallBlock extends Block {
    public DragonBallBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockPos[] matchedPattern = getHPattern(level, pos, this);

            if (matchedPattern != null) {

                // Remove blocks (set to air)
                for (BlockPos offset : matchedPattern) {
                    level.setBlock(pos.offset(offset), Fluids.EMPTY.defaultFluidState().createLegacyBlock(), 3);
                }

                // Spawn mob at center
                if (level instanceof ServerLevel serverLevel) {
                    EntityType.LIGHTNING_BOLT.spawn(serverLevel, pos, MobSpawnType.TRIGGERED);
                }

                level.playSound(player, pos, ModSounds.SUMMON_SHENRON.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
        return InteractionResult.SUCCESS;
    }

    /**
     * Returns the matching H pattern offsets if it matches, otherwise null.
     */
    private BlockPos[] getHPattern(Level level, BlockPos center, Block targetBlock) {
        BlockPos[] northSouth = new BlockPos[]{
                new BlockPos(-1, 0, -1), new BlockPos(1, 0, -1),
                new BlockPos(-1, 0, 0),  new BlockPos(0, 0, 0), new BlockPos(1, 0, 0),
                new BlockPos(-1, 0, 1),  new BlockPos(1, 0, 1)
        };

        BlockPos[] eastWest = new BlockPos[]{
                new BlockPos(-1, 0, -1), new BlockPos(-1, 0, 1),
                new BlockPos(0, 0, -1),  new BlockPos(0, 0, 0), new BlockPos(0, 0, 1),
                new BlockPos(1, 0, -1),  new BlockPos(1, 0, 1)
        };

        if (checkPattern(level, center, targetBlock, northSouth)) return northSouth;
        if (checkPattern(level, center, targetBlock, eastWest)) return eastWest;
        return null;
    }

    private boolean checkPattern(Level level, BlockPos center, Block targetBlock, BlockPos[] offsets) {
        for (BlockPos offset : offsets) {
            if (!level.getBlockState(center.offset(offset)).is(targetBlock)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // x1, y1, z1, x2, y2, z2 in units (0-16)
        return Block.box(5, 0, 5, 11, 6, 11);
    }

}
