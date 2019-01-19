package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Collection;

public class AmaranthHornTweak extends Tweak {
	@Override
	protected Collection<String> computeAffectedClasses() {
		return ImmutableList.of("hellfirepvp.astralsorcery.common.block.BlockCustomFlower");
	}

	@Override
	public String getLogMessage(String transformedName) {
		return "Patching Amaranth...";
	}

	@Override
	public void doPatch(String transformedName, ClassNode node) {
		String hornHarvestable = "vazkii/botania/api/item/IHornHarvestable";
		if(node.interfaces.contains(hornHarvestable)) {
			return;
		}

		//Add the interface
		node.interfaces.add(hornHarvestable);

		//Override the methods of said interface
		MethodNode mv = new MethodNode(ACC_PUBLIC, "canHornHarvest", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;Lvazkii/botania/api/item/IHornHarvestable$EnumHornType;)Z", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 4);
        mv.visitFieldInsn(GETSTATIC, "vazkii/botania/api/item/IHornHarvestable$EnumHornType", "WILD", "Lvazkii/botania/api/item/IHornHarvestable$EnumHornType;");
        Label first = new Label();
        mv.visitJumpInsn(IF_ACMPNE, first);
        mv.visitInsn(ICONST_1);
        Label second = new Label();
        mv.visitJumpInsn(GOTO, second);
        mv.visitLabel(first);
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        mv.visitInsn(ICONST_0);
        mv.visitLabel(second);
        mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {Opcodes.INTEGER});
        mv.visitInsn(IRETURN);
        mv.visitMaxs(2, 5);
        mv.visitEnd();

        node.methods.add(mv);

		mv = new MethodNode(ACC_PUBLIC, "hasSpecialHornHarvest", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;Lvazkii/botania/api/item/IHornHarvestable$EnumHornType;)Z", null, null);
        mv.visitCode();
        mv.visitInsn(ICONST_0);
        mv.visitInsn(IRETURN);
        mv.visitMaxs(1, 5);
        mv.visitEnd();

		node.methods.add(mv);

		mv = new MethodNode(ACC_PUBLIC, "harvestByHorn", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;Lvazkii/botania/api/item/IHornHarvestable$EnumHornType;)V", null, null);
        mv.visitCode();
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 5);
        mv.visitEnd();

		node.methods.add(mv);
	}


}
