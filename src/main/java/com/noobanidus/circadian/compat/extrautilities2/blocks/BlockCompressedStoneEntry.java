package com.noobanidus.circadian.compat.extrautilities2.blocks;

import com.noobanidus.circadian.Circadian;
import com.rwtema.extrautils2.backend.entries.BlockEntry;
import com.rwtema.extrautils2.blocks.BlockCompressed;
import com.rwtema.extrautils2.crafting.CraftingHelper;
import com.rwtema.extrautils2.utils.helpers.StringHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCompressedStoneEntry extends BlockEntry<BlockCompressed> {
    public static boolean enabled = Circadian.CONFIG.get("Items.CompressedStones", "Enable", true, "Enable compressed sandstone.").getBoolean(true);
    private final IBlockState blockState;
    private final String texture;
    private final int max;

    public BlockCompressedStoneEntry(Block block, String texture, int max) {
        this(block.getDefaultState(), texture, max);
    }

    @SuppressWarnings("unchecked")
    public BlockCompressedStoneEntry(IBlockState blockState, String texture, int max) {
        super("Compressed" + StringHelper.capFirst(texture, true));
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
        CraftingHelper.addShaped("compressed_" + this.texture, this.newStack(1, 0), "BBB", "BBB", "BBB", 'B', base);
        CraftingHelper.addShapeless("compressed_" + this.texture + "_uncompress", new ItemStack(this.blockState.getBlock(), 9), this.newStack(1, 0));

        for (int i = 0; i < this.max - 1; ++i) {
            CraftingHelper.addShaped("compressed_" + this.texture + "_" + (i + 1), this.newStack(1, i + 1), "BBB", "BBB", "BBB", 'B', this.newStack(1, i));
            CraftingHelper.addShapeless("compressed_" + this.texture + "_" + (i + 1) + "_uncompress", this.newStack(9, i), this.newStack(1, i + 1));
        }

    }

    public void registerOres() {
        for (int i = 0; i < this.max; ++i) {
            OreDictionary.registerOre("compressed" + (i + 1) + "x" + StringHelper.capFirst(this.texture, true), this.newStack(1, i));
        }

    }
}

