package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import com.noobanidus.circadian.core.asm.tweaks.Tweak;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Collection;

@SuppressWarnings("unused")
public class HarvestRodTweak extends Tweak {
	@Override
	protected Collection<String> computeAffectedClasses() {
		return ImmutableList.of("xreliquary.items.ItemHarvestRod");
	}
	
	@Override
	public String getLogMessage(String transformedName) {
		return "[CicadaTweaks] Patching Harvest Rod to consume fertilizer...";
	}
	
	@Override
	public void doPatch(String transformedName, ClassNode node) {
        int remove = -1;

        for (int i = 0; i < node.methods.size(); i++) {
            if (node.methods.get(i).name.equals(resolveDeobf("onUpdate", "func_77663_a"))) {
                remove = i;
                break;
            }
        }

        if (remove != -1) {
            MethodNode mnode = node.methods.get(remove);

            mnode.instructions.clear();
            Label label0 = new Label();
            mnode.visitLabel(label0);
            mnode.visitVarInsn(ALOAD, 2);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/world/World", resolveDeobf("isRemote", "field_72995_K"), "Z");
            Label label1 = new Label();
            mnode.visitJumpInsn(IFEQ, label1);
            Label label2 = new Label();
            mnode.visitLabel(label2);
            mnode.visitInsn(RETURN);
            mnode.visitLabel(label1);
            mnode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mnode.visitInsn(ACONST_NULL);
            mnode.visitVarInsn(ASTORE, 6);
            Label label3 = new Label();
            mnode.visitLabel(label3);
            mnode.visitVarInsn(ALOAD, 3);
            mnode.visitTypeInsn(INSTANCEOF, "net/minecraft/entity/player/EntityPlayer");
            Label label4 = new Label();
            mnode.visitJumpInsn(IFEQ, label4);
            Label label5 = new Label();
            mnode.visitLabel(label5);
            mnode.visitVarInsn(ALOAD, 3);
            mnode.visitTypeInsn(CHECKCAST, "net/minecraft/entity/player/EntityPlayer");
            mnode.visitVarInsn(ASTORE, 6);
            mnode.visitLabel(label4);
            mnode.visitFrame(Opcodes.F_APPEND,1, new Object[] {"net/minecraft/entity/player/EntityPlayer"}, 0, null);
            mnode.visitVarInsn(ALOAD, 6);
            Label label6 = new Label();
            mnode.visitJumpInsn(IFNONNULL, label6);
            Label label7 = new Label();
            mnode.visitLabel(label7);
            mnode.visitInsn(RETURN);
            mnode.visitLabel(label6);
            mnode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitVarInsn(ALOAD, 1);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "isEnabled", "(Lnet/minecraft/item/ItemStack;)Z", false);
            Label label8 = new Label();
            mnode.visitJumpInsn(IFEQ, label8);
            Label label9 = new Label();
            mnode.visitLabel(label9);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitVarInsn(ALOAD, 1);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "getBoneMealCount", "(Lnet/minecraft/item/ItemStack;)I", false);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "getBonemealWorth", "()I", false);
            mnode.visitInsn(IADD);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "getBonemealLimit", "()I", false);
            Label label10 = new Label();
            mnode.visitJumpInsn(IF_ICMPGT, label10);
            Label label11 = new Label();
            mnode.visitLabel(label11);
            mnode.visitTypeInsn(NEW, "net/minecraft/item/ItemStack");
            mnode.visitInsn(DUP);
            mnode.visitFieldInsn(GETSTATIC, "net/minecraft/init/Items", resolveDeobf("DYE", "field_151100_aR"), "Lnet/minecraft/item/Item;");
            mnode.visitInsn(ICONST_1);
            mnode.visitIntInsn(BIPUSH, 15);
            mnode.visitMethodInsn(INVOKESPECIAL, "net/minecraft/item/ItemStack", "<init>", "(Lnet/minecraft/item/Item;II)V", false);
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitMethodInsn(INVOKESTATIC, "xreliquary/util/InventoryHelper", "consumeItem", "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;)Z", false);
            Label label12 = new Label();
            mnode.visitJumpInsn(IFEQ, label12);
            Label label13 = new Label();
            mnode.visitLabel(label13);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitVarInsn(ALOAD, 1);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitVarInsn(ALOAD, 1);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "getBoneMealCount", "(Lnet/minecraft/item/ItemStack;)I", false);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "getBonemealWorth", "()I", false);
            mnode.visitInsn(IADD);
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "setBoneMealCount", "(Lnet/minecraft/item/ItemStack;ILnet/minecraft/entity/player/EntityPlayer;)V", false);
            mnode.visitJumpInsn(GOTO, label10);
            mnode.visitLabel(label12);
            mnode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mnode.visitTypeInsn(NEW, "net/minecraft/item/ItemStack");
            mnode.visitInsn(DUP);
            mnode.visitFieldInsn(GETSTATIC, "com/noobanidus/circadian/config/Registrar", "fertilizer", "Lnet/minecraft/item/Item;");
            mnode.visitInsn(ICONST_1);
            mnode.visitInsn(ICONST_0);
            mnode.visitMethodInsn(INVOKESPECIAL, "net/minecraft/item/ItemStack", "<init>", "(Lnet/minecraft/item/Item;II)V", false);
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitMethodInsn(INVOKESTATIC, "xreliquary/util/InventoryHelper", "consumeItem", "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;)Z", false);
            mnode.visitJumpInsn(IFEQ, label10);
            Label label14 = new Label();
            mnode.visitLabel(label14);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitVarInsn(ALOAD, 1);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitVarInsn(ALOAD, 1);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "getBoneMealCount", "(Lnet/minecraft/item/ItemStack;)I", false);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "getBonemealWorth", "()I", false);
            mnode.visitInsn(IADD);
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "setBoneMealCount", "(Lnet/minecraft/item/ItemStack;ILnet/minecraft/entity/player/EntityPlayer;)V", false);
            mnode.visitLabel(label10);
            mnode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitVarInsn(ALOAD, 1);
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemHarvestRod", "consumePlantables", "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;)V", false);
            mnode.visitLabel(label8);
            mnode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mnode.visitInsn(RETURN);
            Label label15 = new Label();
            mnode.visitLabel(label15);
            mnode.visitLocalVariable("this", "Lxreliquary/items/ItemHarvestRod;", null, label0, label15, 0);
            mnode.visitLocalVariable("ist", "Lnet/minecraft/item/ItemStack;", null, label0, label15, 1);
            mnode.visitLocalVariable("world", "Lnet/minecraft/world/World;", null, label0, label15, 2);
            mnode.visitLocalVariable("e", "Lnet/minecraft/entity/Entity;", null, label0, label15, 3);
            mnode.visitLocalVariable("slotNumber", "I", null, label0, label15, 4);
            mnode.visitLocalVariable("isSelected", "Z", null, label0, label15, 5);
            mnode.visitLocalVariable("player", "Lnet/minecraft/entity/player/EntityPlayer;", null, label3, label15, 6);
            mnode.visitMaxs(5, 7);
            mnode.visitEnd();
        }
	}
}
