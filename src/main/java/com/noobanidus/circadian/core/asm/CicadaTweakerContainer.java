package com.noobanidus.circadian.core.asm;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;
import com.noobanidus.circadian.Circadian;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

@SuppressWarnings("unused")
public class CicadaTweakerContainer extends DummyModContainer {
    public CicadaTweakerContainer() {
        super(new ModMetadata());
        ModMetadata mod = getMetadata();
        mod.name = "Circadian Tweaks Core";
        mod.modId = "circadian_tweaks_core";
        mod.description = "Various ASM hackery.";
        mod.authorList = ImmutableList.of("Noobanidus");
        mod.version = "-100";
        mod.parent = Circadian.MODID;
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        return true;
    }
}
