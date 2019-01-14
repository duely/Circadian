package com.noobanidus.circadian;

import com.noobanidus.circadian.config.Registrar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class CreativeTabCircadian extends CreativeTabs {
    public CreativeTabCircadian(int id, String id2) {
        super(id, id2);
    }

    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return new ItemStack(Registrar.fertilizer);
    }
}
