package com.noobanidus.circadian.compat.extrautilities2.blocks;

import com.rwtema.extrautils2.backend.entries.BlockEntry;
import com.rwtema.extrautils2.blocks.BlockCompressed;
import com.rwtema.extrautils2.crafting.CraftingHelper;
import com.rwtema.extrautils2.utils.helpers.StringHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCompressedStoneEntry extends BlockEntry<BlockCompressed> {
    private final IBlockState blockState;
    private final String texture;
    private final int max;

    public BlockCompressedStoneEntry(Block block, String texture, int max) {
        this(block.getDefaultState(), texture, max);
    }

    public BlockCompressedStoneEntry(IBlockState blockState, String texture, int max) {
        super("Compressed" + StringHelper.capFirst(texture, true), new Class[0]);
        this.blockState = blockState;
        this.texture = texture;
        this.max = max;
    }

    public BlockCompressed initValue() {
        return new BlockCompressed(this.blockState, this.texture, this.max);
    }

    public void addRecipes() {
        ItemStack base = new ItemStack(this.blockState.getBlock());
        base.setItemDamage(this.blockState.getBlock().getMetaFromState(this.blockState));
        CraftingHelper.addShaped("compressed_" + this.texture, this.newStack(1, 0), new Object[]{"BBB", "BBB", "BBB", 'B', base});
        CraftingHelper.addShapeless("compressed_" + this.texture + "_uncompress", new ItemStack(this.blockState.getBlock(), 9), new Object[]{this.newStack(1, 0)});

        for(int i = 0; i < this.max - 1; ++i) {
            CraftingHelper.addShaped("compressed_" + this.texture + "_" + (i + 1), this.newStack(1, i + 1), new Object[]{"BBB", "BBB", "BBB", 'B', this.newStack(1, i)});
            CraftingHelper.addShapeless("compressed_" + this.texture + "_" + (i + 1) + "_uncompress", this.newStack(9, i), new Object[]{this.newStack(1, i + 1)});
        }

    }

    public void registerOres() {
        for(int i = 0; i < this.max; ++i) {
            OreDictionary.registerOre("compressed" + (i + 1) + "x" + StringHelper.capFirst(this.texture, true), this.newStack(1, i));
        }

    }
}

