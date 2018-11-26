package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import org.objectweb.asm.tree.*;

import java.util.Collection;

public class ThaumcraftMaturityTweak extends Tweak {
	@Override
	protected Collection<String> computeAffectedClasses() {
		return ImmutableList.of("thaumcraft.common.lib.utils.CropUtils");
	}
	
	@Override
	public String getLogMessage(String transformedName) {
		return "[CicadaTweaks] Patching Thaumcraft crop growth check for Agricraft compatibility...";
	}
	
	@Override
	public void doPatch(String transformedName, ClassNode node) {
	    for (MethodNode mnode : node.methods) {
	        if (!(mnode.name.equals("isGrownCrop"))) continue;

            mnode.instructions.clear();
            mnode.instructions.add(new VarInsnNode(ALOAD, 0));
            mnode.instructions.add(new VarInsnNode(ALOAD, 1));
            mnode.instructions.add(new MethodInsnNode(INVOKESTATIC, getHooksClass(), "isGrownCrop", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z", false));
            mnode.instructions.add(new InsnNode(IRETURN));
        }
	}
}
