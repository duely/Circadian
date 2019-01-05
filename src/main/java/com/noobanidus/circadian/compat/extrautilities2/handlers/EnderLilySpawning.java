package com.noobanidus.circadian.compat.extrautilities2.handlers;

import com.rwtema.extrautils2.blocks.BlockEnderLilly;
import com.rwtema.extrautils2.compatibility.CompatHelper112;
import com.rwtema.extrautils2.worldgen.SingleChunkGen;
import com.rwtema.extrautils2.worldgen.SingleChunkWorldGenManager;
import fluke.stygian.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class EnderLilySpawning {
    public static void preInit() {
        SingleChunkWorldGenManager.register(new SingleChunkGen("StygianEnderLillies", 0) {
            BlockEnderLilly lily = null;

            @Override
            public void genChunk(Chunk chunk, Object provider, Random random) {
                if (lily == null) {
                    lily = (BlockEnderLilly) Block.REGISTRY.getObject(new ResourceLocation("extrautils2:enderlilly"));
                }

                if (!(CompatHelper112.isChunkProviderEnd(provider)))
                    return;

                BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

                int dx = random.nextInt(16);
                int dz = random.nextInt(16);
                int dy = chunk.getHeightValue(dx, dz) - 1;
                if (dy <= 0) return;
                pos.setPos(dx, dy + 1, dz);
                IBlockState blockState = chunk.getBlockState(pos.down());
                Block block = blockState.getBlock();
                if ((block == ModBlocks.endGrass || block == ModBlocks.endObsidian || block == ModBlocks.endMagma) && isAir(chunk, pos)) {
                    setBlockState(chunk, pos, lily.FULLY_GROWN_STATE);
                }
            }
        });
    }
}
