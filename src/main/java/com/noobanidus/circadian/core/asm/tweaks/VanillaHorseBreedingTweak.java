package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import org.objectweb.asm.tree.*;

import java.util.Collection;

public class VanillaHorseBreedingTweak extends Tweak {
	@Override
	protected Collection<String> computeAffectedClasses() {
		return ImmutableList.of("net.minecraft.entity.passive.AbstractHorse");
	}
	
	@Override
	public String getLogMessage(String transformedName) {
		return "[CicadaTweaks] Patching Horses to eat silver food...";
	}
	
	@Override
	public void doPatch(String transformedName, ClassNode node) {
	    for (MethodNode mnode : node.methods) {
            if (!(mnode.name.equals(resolveDeobf("handleEating", "func_190678_b")))) continue;

            mnode.instructions.clear();
            mnode.instructions.add(new VarInsnNode(ALOAD, 0));
            mnode.instructions.add(new VarInsnNode(ALOAD, 1));
            mnode.instructions.add(new VarInsnNode(ALOAD, 2));
            mnode.instructions.add(new MethodInsnNode(INVOKESTATIC, getHooksClass(), "handleEating", "(Lnet/minecraft/entity/passive/AbstractHorse;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;)Z", false));
            mnode.instructions.add(new InsnNode(IRETURN));
        }
	}
}
