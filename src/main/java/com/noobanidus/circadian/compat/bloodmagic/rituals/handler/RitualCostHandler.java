package com.noobanidus.circadian.compat.bloodmagic.rituals.handler;

import WayofTime.bloodmagic.BloodMagic;
import WayofTime.bloodmagic.ritual.Ritual;
import com.noobanidus.circadian.Circadian;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class RitualCostHandler {
    public static boolean enabled = Circadian.CONFIG.get("BloodMagic.Rituals", "AdjustCosts", true, "Adjust costs of Blood Magic rituals.").getBoolean(true);

    public static void init() {
        if (!enabled) return;

        try {
            modifyFields();
        } catch (ReflectiveOperationException e) {
            Circadian.LOG.error("[Circadian] Error modifying ritual costs.");
            e.printStackTrace();
        }
    }

 	public static int modifyActivationCost (String ritualName, int activationCost) {
		return Circadian.CONFIG.get("BloodMagic.Rituals", ritualName, activationCost, String.format("Modify activation cost for ritual %s", ritualName)).getInt(activationCost);
	}

    public static void modifyFields () throws ReflectiveOperationException {
        Field field = ReflectionHelper.findField(Ritual.class, "activationCost");
        field.setAccessible(true);

        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        for (Ritual ritual : BloodMagic.RITUAL_MANAGER.getRituals()) {
            int cost = modifyActivationCost(ritual.getName(), ritual.getActivationCost());
            field.set(ritual, cost);
        }
        Circadian.LOG.info("[Circadian] Patched Agricraft not to harvest with watering cans.");
    }
}