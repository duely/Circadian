package com.noobanidus.circadian.core.asm;

import com.noobanidus.circadian.Circadian;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.ClassNode;
import com.noobanidus.circadian.core.asm.tweaks.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class CicadaTweakerTransformer implements IClassTransformer, Opcodes {
	public static final String HOOKS = "com/noobanidus/cicadatweaks/asm/CicadaTweakerHooks";
	
	static List<Tweak> tweaks = new ArrayList<>();
	static List<String> allPatchedClasses = new ArrayList<>();

	static {
	    if (Circadian.CONFIG.get("Thaumcraft.Agricraft", "CropMaturity", true, "Patch Thaumcraft to properly check for crop maturity before golems harvest.").getBoolean(true)) {
            tweaks.add(new ThaumcraftMaturityTweak());
        }
        if (Circadian.CONFIG.get("Oreberries.Harvest", "DropBerries", true, "Patch Oreberries to drop berries instead of placing them in inventories (Thaumcraft Golem compatibility)").getBoolean(true)) {
            tweaks.add(new OreberriesHarvestTweak());
        }
        if (Circadian.CONFIG.get("Vanilla.Animals", "SilverBreeding", true, "Patch vanilla Horse breeding to use silvered versions of carrots, potatos and apples.").getBoolean(true)) {
            tweaks.add(new VanillaHorseBreedingTweak());
        }
		if (Circadian.CONFIG.get("Vanilla.Animals", "NoSaddledWandering", true, "Prevent saddled horses from wandering away.").getBoolean(true)) {
            tweaks.add(new VanillaHorseWanderingTweak());
        }
        if (Circadian.CONFIG.get("Reliquary.Items", "HarvestRodFertilizer", true, "Patch HarvestRod to consume Circadian fertilizer as well as bone meal.").getBoolean(true)) {
            tweaks.add(new HarvestRodTweak());
        }
        if (Circadian.CONFIG.get("Reliquary.Items", "BetterLanternTorchPlacement", true, "Patch Lantern of Paranoia to allow placement of torches on snow, and to place torches on unlit blocks during daylight.").getBoolean(true)) {
            tweaks.add(new LanternOfParanoiaTweak());
        }
        if (Circadian.CONFIG.get("Agricraft.Info", "RemoveAlphaWarning", true, "Remove Agricraft 'still in alpha!' warning message from spamming on login.").getBoolean(true)) {
            tweaks.add(new DeCancerAgriCraftTweak());
        }
        if (Circadian.CONFIG.get("FTB.Utilities", "TrashCanSize", true, "Increase FTB Utilities trash can size to be equal to that of the inventory.").getBoolean(true)) {
            tweaks.add(new IncreaseFTBUtilitiesTrashCanSizeTweak());
        }
        if (Circadian.CONFIG.get("TwilightForest.Animals", "PenguinLoot", true, "Patch EntityTFPenguin to have its own loot file instead of using default bird loot file.").getBoolean(true)) {
            tweaks.add(new PenguinLoot());
        }
        if (Circadian.CONFIG.get("BloodMagic.Rituals", "AdjustCosts", true, "Adjust costs of Blood Magic rituals.").getBoolean(true)) {
            tweaks.add(new BloodMagicRitualTweak());
        }
        if (Circadian.CONFIG.get("BloodMagic.Rituals", "MeteorPlatform", true, "Automatically generate a platform for meteor to land on.").getBoolean(true)) {
            tweaks.add(new MeteorTweak());
        }
        if (Circadian.CONFIG.get("ExtraUtils2.DemonMetal", "ChangeInput", true, "Change Extrautils2 input material for Demon Metal from gold to thaumium, alchemical brass or void metal.").getBoolean(true)) {
            tweaks.add(new DemonMetalTweak());
        }

		for(Tweak t : tweaks) {
			allPatchedClasses.addAll(t.getAffectedClasses());
		}
	}
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if(!allPatchedClasses.contains(transformedName)) return basicClass;
		
		ClassReader reader = new ClassReader(basicClass);
		ClassNode node = new ClassNode();
		reader.accept(node, 0);



		for(Tweak t : tweaks) {
			t.patch(transformedName, node);
		}

		ClassWriter writer = new WorkaroundClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		node.accept(writer);

		return writer.toByteArray();
	}
}
