package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import com.noobanidus.circadian.core.asm.tweaks.Tweak;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Collection;

@SuppressWarnings("unused")
public class LanternOfParanoiaTweak extends Tweak {
	@Override
	protected Collection<String> computeAffectedClasses() {
		return ImmutableList.of("xreliquary.items.ItemLanternOfParanoia");
	}

	@Override
	public String getLogMessage (String transformedName) {
		return "[CicadaTweaks] Patching Lantern of Paranoia to place torches even during the day and on snow!...";
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
            mnode.visitCode();
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitVarInsn(ALOAD, 1);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemLanternOfParanoia", "isEnabled", "(Lnet/minecraft/item/ItemStack;)Z", false);
            Label l0 = new Label();
            mnode.visitJumpInsn(IFNE, l0);
            mnode.visitInsn(RETURN);
            mnode.visitLabel(l0);
            mnode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mnode.visitVarInsn(ALOAD, 2);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/world/World", resolveDeobf("isRemote", "field_72995_K"), "Z");
            Label l1 = new Label();
            mnode.visitJumpInsn(IFEQ, l1);
            mnode.visitInsn(RETURN);
            mnode.visitLabel(l1);
            mnode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mnode.visitVarInsn(ALOAD, 3);
            mnode.visitTypeInsn(INSTANCEOF, "net/minecraft/entity/player/EntityPlayer");
            Label l2 = new Label();
            mnode.visitJumpInsn(IFEQ, l2);
            mnode.visitVarInsn(ALOAD, 3);
            mnode.visitTypeInsn(CHECKCAST, "net/minecraft/entity/player/EntityPlayer");
            mnode.visitVarInsn(ASTORE, 6);
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", resolveDeobf("posX", "field_70165_t"), "D");
            mnode.visitMethodInsn(INVOKESTATIC, "net/minecraft/util/math/MathHelper", resolveDeobf("floor", "func_76128_c"), "(D)I", false);
            mnode.visitVarInsn(ISTORE, 7);
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/player/EntityPlayer", resolveDeobf("getEntityBoundingBox", "func_174813_aQ"), "()Lnet/minecraft/util/math/AxisAlignedBB;", false);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/util/math/AxisAlignedBB", resolveDeobf("minY", "field_70161_v"), "D");
            mnode.visitMethodInsn(INVOKESTATIC, "net/minecraft/util/math/MathHelper", resolveDeobf("floor", "func_76128_c"), "(D)I", false);
            mnode.visitVarInsn(ISTORE, 8);
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", "posZ", "D");
            mnode.visitMethodInsn(INVOKESTATIC, "net/minecraft/util/math/MathHelper", "floor", "(D)I", false);
            mnode.visitVarInsn(ISTORE, 9);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemLanternOfParanoia", "getRange", "()I", false);
            mnode.visitInsn(INEG);
            mnode.visitVarInsn(ISTORE, 13);
            Label l3 = new Label();
            mnode.visitLabel(l3);
            mnode.visitFrame(Opcodes.F_FULL, 14, new Object[] {"xreliquary/items/ItemLanternOfParanoia", "net/minecraft/item/ItemStack", "net/minecraft/world/World", "net/minecraft/entity/Entity", Opcodes.INTEGER, Opcodes.INTEGER, "net/minecraft/entity/player/EntityPlayer", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.TOP, Opcodes.TOP, Opcodes.TOP, Opcodes.INTEGER}, 0, new Object[] {});
            mnode.visitVarInsn(ILOAD, 13);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemLanternOfParanoia", "getRange", "()I", false);
            mnode.visitJumpInsn(IF_ICMPGT, l2);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemLanternOfParanoia", "getRange", "()I", false);
            mnode.visitInsn(INEG);
            mnode.visitVarInsn(ISTORE, 14);
            Label l4 = new Label();
            mnode.visitLabel(l4);
            mnode.visitFrame(Opcodes.F_APPEND,1, new Object[] {Opcodes.INTEGER}, 0, null);
            mnode.visitVarInsn(ILOAD, 14);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemLanternOfParanoia", "getRange", "()I", false);
            Label l5 = new Label();
            mnode.visitJumpInsn(IF_ICMPGT, l5);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemLanternOfParanoia", "getRange", "()I", false);
            mnode.visitInsn(ICONST_2);
            mnode.visitInsn(IDIV);
            mnode.visitVarInsn(ISTORE, 15);
            Label l6 = new Label();
            mnode.visitLabel(l6);
            mnode.visitFrame(Opcodes.F_APPEND,1, new Object[] {Opcodes.INTEGER}, 0, null);
            mnode.visitVarInsn(ILOAD, 15);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "xreliquary/items/ItemLanternOfParanoia", "getRange", "()I", false);
            mnode.visitInsn(INEG);
            mnode.visitInsn(ICONST_2);
            mnode.visitInsn(IDIV);
            Label l7 = new Label();
            mnode.visitJumpInsn(IF_ICMPLT, l7);
            mnode.visitVarInsn(ILOAD, 7);
            mnode.visitVarInsn(ILOAD, 13);
            mnode.visitInsn(IADD);
            mnode.visitVarInsn(ISTORE, 16);
            mnode.visitVarInsn(ILOAD, 8);
            mnode.visitVarInsn(ILOAD, 15);
            mnode.visitInsn(IADD);
            mnode.visitVarInsn(ISTORE, 17);
            mnode.visitVarInsn(ILOAD, 9);
            mnode.visitVarInsn(ILOAD, 14);
            mnode.visitInsn(IADD);
            mnode.visitVarInsn(ISTORE, 18);
            mnode.visitTypeInsn(NEW, "net/minecraft/util/math/BlockPos");
            mnode.visitInsn(DUP);
            mnode.visitVarInsn(ILOAD, 16);
            mnode.visitVarInsn(ILOAD, 17);
            mnode.visitVarInsn(ILOAD, 18);
            mnode.visitMethodInsn(INVOKESPECIAL, "net/minecraft/util/math/BlockPos", "<init>", "(III)V", false);
            mnode.visitVarInsn(ASTORE, 10);
            mnode.visitVarInsn(ALOAD, 2);
            mnode.visitVarInsn(ALOAD, 10);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/world/World", resolveDeobf("getBlockState", "func_180495_p"), "(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;", false);
            mnode.visitVarInsn(ASTORE, 11);
            mnode.visitVarInsn(ALOAD, 11);
            mnode.visitMethodInsn(INVOKEINTERFACE, "net/minecraft/block/state/IBlockState", resolveDeobf("getBlock", "func_177230_c"), "()Lnet/minecraft/block/Block;", true);
            mnode.visitVarInsn(ASTORE, 12);
            mnode.visitVarInsn(ALOAD, 12);
            mnode.visitTypeInsn(INSTANCEOF, "net/minecraft/block/BlockLiquid");
            Label l8 = new Label();
            mnode.visitJumpInsn(IFNE, l8);
            mnode.visitVarInsn(ALOAD, 12);
            mnode.visitTypeInsn(INSTANCEOF, "net/minecraftforge/fluids/IFluidBlock");
            mnode.visitJumpInsn(IFNE, l8);
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", resolveDeobf("world", "field_70170_p"), "Lnet/minecraft/world/World;");
            mnode.visitTypeInsn(NEW, "net/minecraft/util/math/BlockPos");
            mnode.visitInsn(DUP);
            mnode.visitVarInsn(ILOAD, 16);
            mnode.visitVarInsn(ILOAD, 17);
            mnode.visitVarInsn(ILOAD, 18);
            mnode.visitMethodInsn(INVOKESPECIAL, "net/minecraft/util/math/BlockPos", "<init>", "(III)V", false);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/world/World", resolveDeobf("isAirBlock", "func_175623_d"), "(Lnet/minecraft/util/math/BlockPos;)Z", false);
            Label l9 = new Label();
            mnode.visitJumpInsn(IFNE, l9);
            mnode.visitVarInsn(ALOAD, 12);
            mnode.visitVarInsn(ALOAD, 2);
            mnode.visitVarInsn(ALOAD, 10);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/block/Block", resolveDeobf("isReplaceable", "func_176200_f"), "(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)Z", false);
            mnode.visitJumpInsn(IFNE, l9);
            mnode.visitJumpInsn(GOTO, l8);
            mnode.visitLabel(l9);
            mnode.visitFrame(Opcodes.F_FULL, 19, new Object[] {"xreliquary/items/ItemLanternOfParanoia", "net/minecraft/item/ItemStack", "net/minecraft/world/World", "net/minecraft/entity/Entity", Opcodes.INTEGER, Opcodes.INTEGER, "net/minecraft/entity/player/EntityPlayer", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/block/state/IBlockState", "net/minecraft/block/Block", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER}, 0, new Object[] {});
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", "world", "Lnet/minecraft/world/World;");
            mnode.visitFieldInsn(GETSTATIC, "net/minecraft/world/EnumSkyBlock", "BLOCK", "Lnet/minecraft/world/EnumSkyBlock;");
            mnode.visitTypeInsn(NEW, "net/minecraft/util/math/BlockPos");
            mnode.visitInsn(DUP);
            mnode.visitVarInsn(ILOAD, 16);
            mnode.visitVarInsn(ILOAD, 17);
            mnode.visitVarInsn(ILOAD, 18);
            mnode.visitMethodInsn(INVOKESPECIAL, "net/minecraft/util/math/BlockPos", "<init>", "(III)V", false);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/world/World", resolveDeobf("getLightFromNeighborsFor", "func_175705_a"), "(Lnet/minecraft/world/EnumSkyBlock;Lnet/minecraft/util/math/BlockPos;)I", false);
            mnode.visitVarInsn(ISTORE, 19);
            mnode.visitVarInsn(ILOAD, 19);
            mnode.visitFieldInsn(GETSTATIC, "xreliquary/reference/Settings", "Items", "Lxreliquary/reference/Settings$ItemSettings;");
            mnode.visitFieldInsn(GETFIELD, "xreliquary/reference/Settings$ItemSettings", "LanternOfParanoia", "Lxreliquary/reference/Settings$ItemSettings$LanternOfParanoiaSettings;");
            mnode.visitFieldInsn(GETFIELD, "xreliquary/reference/Settings$ItemSettings$LanternOfParanoiaSettings", "minLightLevel", "I");
            Label l10 = new Label();
            mnode.visitJumpInsn(IF_ICMPLE, l10);
            mnode.visitJumpInsn(GOTO, l8);
            mnode.visitLabel(l10);
            mnode.visitFrame(Opcodes.F_APPEND,1, new Object[] {Opcodes.INTEGER}, 0, null);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitVarInsn(ALOAD, 1);
            mnode.visitVarInsn(ILOAD, 16);
            mnode.visitVarInsn(ILOAD, 17);
            mnode.visitVarInsn(ILOAD, 18);
            mnode.visitVarInsn(ALOAD, 6);
            mnode.visitVarInsn(ALOAD, 2);
            mnode.visitMethodInsn(INVOKESPECIAL, "xreliquary/items/ItemLanternOfParanoia", "tryToPlaceTorchAround", "(Lnet/minecraft/item/ItemStack;IIILnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;)Z", false);
            mnode.visitJumpInsn(IFEQ, l8);
            mnode.visitJumpInsn(GOTO, l2);
            mnode.visitLabel(l8);
            mnode.visitFrame(Opcodes.F_FULL, 16, new Object[] {"xreliquary/items/ItemLanternOfParanoia", "net/minecraft/item/ItemStack", "net/minecraft/world/World", "net/minecraft/entity/Entity", Opcodes.INTEGER, Opcodes.INTEGER, "net/minecraft/entity/player/EntityPlayer", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/block/state/IBlockState", "net/minecraft/block/Block", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER}, 0, new Object[] {});
            mnode.visitIincInsn(15, -1);
            mnode.visitJumpInsn(GOTO, l6);
            mnode.visitLabel(l7);
            mnode.visitFrame(Opcodes.F_FULL, 15, new Object[] {"xreliquary/items/ItemLanternOfParanoia", "net/minecraft/item/ItemStack", "net/minecraft/world/World", "net/minecraft/entity/Entity", Opcodes.INTEGER, Opcodes.INTEGER, "net/minecraft/entity/player/EntityPlayer", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.TOP, Opcodes.TOP, Opcodes.TOP, Opcodes.INTEGER, Opcodes.INTEGER}, 0, new Object[] {});
            mnode.visitIincInsn(14, 1);
            mnode.visitJumpInsn(GOTO, l4);
            mnode.visitLabel(l5);
            mnode.visitFrame(Opcodes.F_CHOP,1, null, 0, null);
            mnode.visitIincInsn(13, 1);
            mnode.visitJumpInsn(GOTO, l3);
            mnode.visitLabel(l2);
            mnode.visitFrame(Opcodes.F_FULL, 6, new Object[] {"xreliquary/items/ItemLanternOfParanoia", "net/minecraft/item/ItemStack", "net/minecraft/world/World", "net/minecraft/entity/Entity", Opcodes.INTEGER, Opcodes.INTEGER}, 0, new Object[] {});
            mnode.visitInsn(RETURN);
            mnode.visitMaxs(7, 20);
            mnode.visitEnd();
        }
	}
}
