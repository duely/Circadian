package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import com.noobanidus.circadian.core.asm.tweaks.Tweak;
import org.objectweb.asm.tree.*;

import java.util.Collection;

public class IncreaseFTBUtilitiesTrashCanSizeTweak extends Tweak {
	@Override
	protected Collection<String> computeAffectedClasses() {
		return ImmutableList.of("com.feed_the_beast.ftbutilities.command.CmdTrashCan");
	}
	
	@Override
	public String getLogMessage(String transformedName) {
		return "[CicadaTweaks] Taking out more trash than you can carry...";
	}
	
	@Override
	public void doPatch(String transformedName, ClassNode node) {
        int remove = -1;

        for (int i = 0; i < node.methods.size(); i++) {
            if (node.methods.get(i).name.equals("execute")) {
                remove = i;
                break;
            }
        }

        if (remove != -1) {
            MethodNode mnode = node.methods.get(remove);

            mnode.instructions.clear();
            mnode.visitCode();
            mnode.visitVarInsn(ALOAD, 2);
            mnode.visitMethodInsn(INVOKESTATIC, "com/feed_the_beast/ftbutilities/command/CmdTrashCan", resolveDeobf("getCommandSenderAsPlayer", "func_71521_c"), "(Lnet/minecraft/command/ICommandSender;)Lnet/minecraft/entity/player/EntityPlayerMP;", false);
            mnode.visitTypeInsn(NEW, "net/minecraft/inventory/InventoryBasic");
            mnode.visitInsn(DUP);
            mnode.visitLdcInsn("Trash Can");
            mnode.visitInsn(ICONST_1);
            mnode.visitIntInsn(BIPUSH, 36);
            mnode.visitMethodInsn(INVOKESPECIAL, "net/minecraft/inventory/InventoryBasic", "<init>", "(Ljava/lang/String;ZI)V", false);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/player/EntityPlayerMP", resolveDeobf("displayGUIChest", "func_71007_a"), "(Lnet/minecraft/inventory/IInventory;)V", false);
            mnode.visitInsn(RETURN);
            mnode.visitMaxs(6, 4);
            mnode.visitEnd();
        }
	}
}
