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
        int remove = -1;

        for (int i = 0; i < node.methods.size(); i++) {
            if (node.methods.get(i).name.equals("harvest")) {
                remove = i;
                break;
            }
        }

        if (remove != -1) {
            MethodNode mnode = node.methods.get(remove);

            mnode.instructions.clear();
            //mnode.instructions.add(new VarInsnNode(ALOAD, 0));
            mnode.instructions.add(new VarInsnNode(ALOAD, 1));
            mnode.instructions.add(new VarInsnNode(ALOAD, 2));
            mnode.instructions.add(new VarInsnNode(ALOAD, 3));
            mnode.instructions.add(new VarInsnNode(ALOAD, 4));
            mnode.instructions.add(new MethodInsnNode(INVOKESTATIC, getHooksClass(), "harvest", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/entity/player/EntityPlayer;)Z", false));
            mnode.instructions.add(new InsnNode(IRETURN));
        }
    }
}

