package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Collection;

public class DeCancerAgriCraftTweak extends Tweak {
    @Override
    protected Collection<String> computeAffectedClasses() {
        return ImmutableList.of("com.infinityraider.agricraft.reference.AgriAlphaWarnings");
    }

    @Override
    public String getLogMessage(String transformedName) {
        return "[CicadaTweaks] Removing AgriCraft alpha warning message spam...";
    }

    @Override
    public void doPatch(String transformedName, ClassNode node) {
        for (MethodNode mnode : node.methods) {
            if (!(mnode.name.equals("chooseMessage"))) continue;

            mnode.instructions.clear();
            mnode.instructions.add(new InsnNode(RETURN));
        }
    }
}

