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
        int remove = -1;

        for (int i = 0; i < node.methods.size(); i++) {
            if (node.methods.get(i).name.equals("<init>")) {
                remove = i;
                break;
            }
        }

        if (remove != -1) {
            MethodNode mnode = node.methods.get(remove);

            AbstractInsnNode before = mnode.instructions.get(mnode.instructions.size()-2);

            mnode.instructions.insertBefore(before, new VarInsnNode(ALOAD, 0));
            mnode.instructions.insertBefore(before, new VarInsnNode(ALOAD, 5));
            mnode.instructions.insertBefore(before, new VarInsnNode(ILOAD, 3));
            mnode.instructions.insertBefore(before, new MethodInsnNode(INVOKESTATIC, getHooksClass(), "modifyActivationCost", "(Ljava/lang/String;I)I", false));
            mnode.instructions.insertBefore(before, new FieldInsnNode(PUTFIELD, "WayofTime/bloodmagic/ritual/Ritual", "activationCost", "I"));
        }
	}
}
