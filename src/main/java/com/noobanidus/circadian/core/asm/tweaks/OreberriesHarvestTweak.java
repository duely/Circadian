package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import org.objectweb.asm.tree.*;

import java.util.Collection;

public class OreberriesHarvestTweak extends Tweak {
    @Override
    protected Collection<String> computeAffectedClasses() {
        return ImmutableList.of("josephcsible.oreberries.BlockOreberryBush");
    }

    @Override
    public String getLogMessage(String transformedName) {
        return "[CicadaTweaks] Patching Oreberries FakePlayer handling for bushes...";
    }

    @Override
    public void doPatch(String transformedName, ClassNode node) {
        for (MethodNode mnode : node.methods) {
            if (mnode.name.equals("harvest")) {
                mnode.instructions.clear();
                mnode.instructions.add(new VarInsnNode(ALOAD, 1));
                mnode.instructions.add(new VarInsnNode(ALOAD, 2));
                mnode.instructions.add(new VarInsnNode(ALOAD, 3));
                mnode.instructions.add(new VarInsnNode(ALOAD, 4));
                mnode.instructions.add(new MethodInsnNode(INVOKESTATIC, getHooksClass(), "harvest", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/entity/player/EntityPlayer;)Z", false));
                mnode.instructions.add(new InsnNode(IRETURN));
            } else if (mnode.name.equals(resolveDeobf("canGrow", "func_176473_a"))) {
                mnode.instructions.clear();
                mnode.instructions.add(new InsnNode(ICONST_1));
                mnode.instructions.add(new InsnNode(IRETURN));
            }
        }
    }
}

