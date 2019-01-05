package com.noobanidus.circadian.compat.agricraft.handlers;

import com.infinityraider.agricraft.api.v1.items.IAgriClipperItem;
import com.infinityraider.agricraft.api.v1.items.IAgriRakeItem;
import com.infinityraider.agricraft.api.v1.items.IAgriTrowelItem;
import com.infinityraider.agricraft.blocks.BlockCrop;
import com.infinityraider.agricraft.items.ItemDebugger;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.items.WateringCan;
import com.rwtema.extrautils2.items.ItemWateringCan;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import xreliquary.items.ItemHarvestRod;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class RightClickHandler {
    public static boolean enabled = Circadian.CONFIG.get("Agricraft.Harvesting", "WateringCanHarvesting", true, "Disable harvesting with a watering can, allowing you to water crop sticks.").getBoolean();

    public static void init() {
        if (!enabled) return;

        try {
            modifyFields();
        } catch (ReflectiveOperationException e) {
            Circadian.LOG.error("[Circadian] Error magicifying Agricraft.");
            e.printStackTrace();
        }
    }

    public static void modifyFields () throws ReflectiveOperationException {
        Field field = ObfuscationReflectionHelper.findField(BlockCrop.class, "ITEM_EXCLUDES");
        field.setAccessible(true);

        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);


        Class[] newExcludes = new Class[]{
                IAgriRakeItem.class,
                IAgriClipperItem.class,
                IAgriTrowelItem.class,
                ItemDebugger.class,
                ItemWateringCan.class,
                cofh.thermalcultivation.item.ItemWateringCan.class,
                WateringCan.class,
                ItemHarvestRod.class,
        };

        field.set(null, newExcludes);

        Circadian.LOG.info("[Circadian] Patched Agricraft not to harvest with watering cans.");
    }
}