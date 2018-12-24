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
	public static final String HOOKS = "com/noobanidus/circadian/core/asm/CicadaTweakerHooks";
	
	static List<Tweak> tweaks = new ArrayList<>();
	static List<String> allPatchedClasses = new ArrayList<>();

	static {
	    if (Circadian.CONFIG.get("Thaumcraft.Agricraft", "CropMaturity", true, "Patch Thaumcraft to properly check for crop maturity before golems harvest.").getBoolean(true)) {
            tweaks.add(new ThaumcraftMaturityTweak());
        }
        if (Circadian.CONFIG.get("Oreberries.Harvest", "DropBerries", true, "Patch Oreberries to drop berries instead of placing them in inventories (Thaumcraft Golem compatibility)").getBoolean(true)) {
            tweaks.add(new OreberriesHarvestTweak());
        }
        if (Circadian.CONFIG.get("Reliquary.Items", "HarvestRodFertilizer", true, "Patch HarvestRod to consume Circadian fertilizer as well as bone meal.").getBoolean(true)) {
            tweaks.add(new HarvestRodTweak());
        }
        if (Circadian.CONFIG.get("Agricraft.Info", "RemoveAlphaWarning", true, "Remove Agricraft 'still in alpha!' warning message from spamming on login.").getBoolean(true)) {
            tweaks.add(new DeCancerAgriCraftTweak());
        }
        if (Circadian.CONFIG.get("FTB.Utilities", "TrashCanSize", true, "Increase FTB Utilities trash can size to be equal to that of the inventory.").getBoolean(true)) {
            tweaks.add(new IncreaseFTBUtilitiesTrashCanSizeTweak());
        }
        if (Circadian.CONFIG.get("ExtraUtils2.DemonMetal", "ChangeInput", true, "Change Extrautils2 input material for Demon Metal from gold to thaumium, alchemical brass or void metal.").getBoolean(true)) {
            tweaks.add(new DemonMetalTweak());
        }
        if (Circadian.CONFIG.get("ExtraUtils2.EnderLily", "StygianCompat", true, "Allow Ender Lily plants to spawn on Stygian end biome features.").getBoolean(true)) {
        	tweaks.add(new StygianEnderLilyTweak());
		}
		if (Circadian.CONFIG.get("WaterStrainers.Invetory", "StackSize", true, "Increase the maximum stack size of the Water Strainer's inventory.").getBoolean(true)) {
			tweaks.add(new WaterStrainerSizeTweak());
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
