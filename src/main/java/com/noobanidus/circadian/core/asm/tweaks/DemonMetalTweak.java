package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Collection;

public class DemonMetalTweak extends Tweak {
    @Override
    protected Collection<String> computeAffectedClasses() {
        return ImmutableList.of("com.rwtema.extrautils2.items.DemonicIngotHandler");
    }

    @Override
    public String getLogMessage(String transformedName) {
        return "[CicadaTweaks] Changing Demonic Metal input material to any Thaumcraft ingot...";
    }

    @Override
    public void doPatch(String transformedName, ClassNode node) {
        for (MethodNode mnode : node.methods) {
            for (int i = 0; i < mnode.instructions.size(); i++) {
                if (!(mnode.instructions.get(i) instanceof FieldInsnNode)) continue;

                FieldInsnNode n = (FieldInsnNode) mnode.instructions.get(i);
                if (n.getOpcode() == GETSTATIC) {
                    if (n.owner.equals("net/minecraft/init/Items") && (n.name.equals("field_151043_k") || n.name.equals("GOLD_INGOT"))) {
                        AbstractInsnNode next = mnode.instructions.get(i + 1);
                        mnode.instructions.remove(mnode.instructions.get(i));
                        mnode.instructions.insertBefore(next, new FieldInsnNode(GETSTATIC, "thaumcraft/api/items/ItemsTC", "ingots", "Lnet/minecraft/item/Item;"));
                        break;
                    }
                }
            }
        }
    }
}
