package com.noobanidus.circadian.core.asm.tweaks;

import com.noobanidus.circadian.core.asm.CicadaTweakerTransformer;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.util.Collection;

public abstract class Tweak implements Opcodes {
	protected abstract Collection<String> computeAffectedClasses();
	public abstract void doPatch(String transformedName, ClassNode node);
	public abstract String getLogMessage(String transformedName);
	
	private Collection<String> affectedClasses;
	
	public Collection<String> getAffectedClasses() {
		if(affectedClasses == null) affectedClasses = computeAffectedClasses();
		return affectedClasses;
	}
	
	public void patch(String transformedName, ClassNode node) {
		if(affectedClasses.contains(transformedName)) {
			log(getLogMessage(transformedName));
			doPatch(transformedName, node);
		}
	}
	
	public static String getHooksClass() {
		return CicadaTweakerTransformer.HOOKS;
	}

	public static Boolean deobf = null;

	public static String resolveDeobf (String deobfString, String obfString) {
		if (deobf == null) {
			deobf = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		}

		return (deobf) ? deobfString : obfString;
	}
	
	static void log(String message) {
		LogManager.getLogger("Cicada Tweaks ASM").info(message);
	}
}
