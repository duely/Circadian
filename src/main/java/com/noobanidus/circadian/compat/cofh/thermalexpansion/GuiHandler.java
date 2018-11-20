package com.noobanidus.circadian.compat.cofh.thermalexpansion;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.gui.client.storage.GuiSatchelFilter;
import cofh.thermalexpansion.gui.container.storage.ContainerSatchel;
import cofh.thermalexpansion.gui.container.storage.ContainerSatchelFilter;
import com.noobanidus.circadian.config.Registrar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

// TODO: License notifications

public class GuiHandler implements IGuiHandler {
    public static final int SATCHEL_ID = 16;
    public static final int SATCHEL_FILTER_ID = 17;

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

        switch (id) {
            case SATCHEL_ID:
                if (ItemHelper.isPlayerHoldingMainhand(Registrar.satchels, player)) {
                    return new GuiSatchel(player.inventory, new ContainerSatchel(player.getHeldItemMainhand(), player.inventory));
                }
                return null;
            case SATCHEL_FILTER_ID:
                if (ItemHelper.isPlayerHoldingMainhand(Registrar.satchels, player)) {
                    return new GuiSatchelFilter(player.inventory, new ContainerSatchelFilter(player.getHeldItemMainhand(), player.inventory));
                }
                return null;
            default:
                return null;
        }
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

        switch (id) {
            case SATCHEL_ID:
                if (ItemHelper.isPlayerHoldingMainhand(Registrar.satchels, player)) {
                    return new ContainerSatchel(player.getHeldItemMainhand(), player.inventory);
                }
                return null;
            case SATCHEL_FILTER_ID:
                if (ItemHelper.isPlayerHoldingMainhand(Registrar.satchels, player)) {
                    return new ContainerSatchelFilter(player.getHeldItemMainhand(), player.inventory);
                }
                return null;
            default:
                return null;
        }
    }

}
