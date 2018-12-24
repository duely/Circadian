package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import org.objectweb.asm.tree.*;

import java.util.Collection;

public class WaterStrainerSizeTweak extends Tweak {
    @Override
    protected Collection<String> computeAffectedClasses() {
        return ImmutableList.of("mods.waterstrainer.tileentity.TileEntityStrainer");
    }

    @Override
    public String getLogMessage(String transformedName) {
        return "[CicadaTweaks] Increasing the stack size of the water strainer...";
    }

    @Override
    public void doPatch(String transformedName, ClassNode node) {
        for (MethodNode mnode : node.methods) {
            if (mnode.name.equals(resolveDeobf("getInventoryStackLimit", "func_70297_j_"))) {
                mnode.instructions.clear();
                mnode.instructions.add(new IntInsnNode(BIPUSH, 256));
                mnode.instructions.add(new InsnNode(IRETURN));
            }
        }
    }
}

