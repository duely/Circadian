package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import org.objectweb.asm.tree.*;

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
        int remove = -1;

        for (int i = 0; i < node.methods.size(); i++) {
            if (node.methods.get(i).name.equals("chooseMessage")) {
                remove = i;
                break;
            }
        }

        if (remove != -1) {
            MethodNode mnode = node.methods.get(remove);

            mnode.instructions.clear();
            mnode.instructions.add(new InsnNode(RETURN));
        }
    }
}

