package com.noobanidus.circadian.compat.cofh.thermalexpansion;

import cofh.core.gui.GuiContainerCore;
import cofh.core.gui.element.tab.TabInfo;
import cofh.core.init.CoreProps;
import cofh.core.util.helpers.MathHelper;
import cofh.core.util.helpers.StringHelper;
import cofh.thermalexpansion.gui.container.storage.ContainerSatchel;
import com.noobanidus.circadian.items.Satchels;
import net.minecraft.entity.player.InventoryPlayer;

// TODO: License notifications

public class GuiSatchel extends GuiContainerCore {

    public GuiSatchel(InventoryPlayer inventory, ContainerSatchel container) {

        super(container);

        int storageIndex = Satchels.getStorageIndex(container.getContainerStack());
        texture = CoreProps.TEXTURE_STORAGE[storageIndex];
        name = container.getInventoryName();

        allowUserInput = false;

        xSize = 14 + 18 * MathHelper.clamp(storageIndex, 9, 14);
        ySize = 112 + 18 * MathHelper.clamp(storageIndex, 2, 9);

        generateInfo("tab.thermalexpansion.storage.satchel");

        if (container.getContainerStack().isItemEnchantable() && !Satchels.hasHoldingEnchant(container.getContainerStack())) {
            myInfo += "\n\n" + StringHelper.localize("tab.thermalexpansion.storage.enchant");
        }
    }

    @Override
    public void initGui() {

        super.initGui();

        if (!myInfo.isEmpty()) {
            addTab(new TabInfo(this, myInfo));
        }
    }

}

