package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import org.objectweb.asm.tree.*;

import java.util.Collection;

public class StygianEnderLilyTweak extends Tweak {
    @Override
    protected Collection<String> computeAffectedClasses() {
        return ImmutableList.of("com.rwtema.extrautils2.blocks.BlockEnderLilly");
    }

    @Override
    public String getLogMessage(String transformedName) {
        return "[CicadaTweaks] Allowing Ender Lillies to spawn on Stygian biome features...";
    }

    @Override
    public void doPatch(String transformedName, ClassNode node) {
        for (MethodNode mnode : node.methods) {
            if (mnode.name.equals("validLocation")) {
                mnode.instructions.clear();
                mnode.instructions.add(new VarInsnNode(ALOAD, 1));
                mnode.instructions.add(new VarInsnNode(ALOAD, 2));
                mnode.instructions.add(new MethodInsnNode(INVOKESTATIC, getHooksClass(), "validLocation", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z", false));
                mnode.instructions.add(new InsnNode(IRETURN));
            }
        }
    }
}

