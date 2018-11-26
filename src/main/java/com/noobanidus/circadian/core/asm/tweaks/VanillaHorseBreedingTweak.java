package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import com.noobanidus.circadian.core.asm.tweaks.Tweak;
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
        int remove = -1;

        for (int i = 0; i < node.methods.size(); i++) {
            if (node.methods.get(i).name.equals(resolveDeobf("handleEating", "func_190678_b"))) {
                remove = i;
                break;
            }
        }

        if (remove != -1) {
            MethodNode mnode = node.methods.get(remove);

            mnode.instructions.clear();
            mnode.instructions.add(new VarInsnNode(ALOAD, 0));
            mnode.instructions.add(new VarInsnNode(ALOAD, 1));
            mnode.instructions.add(new VarInsnNode(ALOAD, 2));
            mnode.instructions.add(new MethodInsnNode(INVOKESTATIC, getHooksClass(), "handleEating", "(Lnet/minecraft/entity/passive/AbstractHorse;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;)Z", false));
            mnode.instructions.add(new InsnNode(IRETURN));
        }
	}
}
