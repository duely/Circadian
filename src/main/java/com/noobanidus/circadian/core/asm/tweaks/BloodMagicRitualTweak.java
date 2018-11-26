package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import org.objectweb.asm.tree.*;

import java.util.Collection;

public class BloodMagicRitualTweak extends Tweak {
	@Override
	protected Collection<String> computeAffectedClasses() {
		return ImmutableList.of("WayofTime.bloodmagic.ritual.Ritual");
	}
	
	@Override
	public String getLogMessage(String transformedName) {
		return "[CicadaTweaks] Adjusting costs of Blood Magic rituals...";
	}
	
	@Override
	public void doPatch(String transformedName, ClassNode node) {
        for (MethodNode mnode : node.methods) {
            if (!(mnode.name.equals("<init>"))) continue;

            if (!(mnode.desc.equals("(Ljava/lang/String;IILjava/lang/String;)V "))) continue;

            AbstractInsnNode before = mnode.instructions.get(mnode.instructions.size()-2);

            mnode.instructions.insertBefore(before, new VarInsnNode(ALOAD, 0));
            mnode.instructions.insertBefore(before, new VarInsnNode(ALOAD, 4));
            mnode.instructions.insertBefore(before, new VarInsnNode(ILOAD, 3));
            mnode.instructions.insertBefore(before, new MethodInsnNode(INVOKESTATIC, getHooksClass(), "modifyActivationCost", "(Ljava/lang/String;I)I", false));
            mnode.instructions.insertBefore(before, new FieldInsnNode(PUTFIELD, "WayofTime/bloodmagic/ritual/Ritual", "activationCost", "I"));
        }
	}
}
