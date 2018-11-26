package com.noobanidus.circadian.core.asm.tweaks;

import com.google.common.collect.ImmutableList;
import com.noobanidus.circadian.core.asm.tweaks.Tweak;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Collection;

public class VanillaHorseWanderingTweak extends Tweak {
	@Override
	protected Collection<String> computeAffectedClasses() {
		return ImmutableList.of("net.minecraft.entity.ai.EntityAIWanderAvoidWater");
	}
	
	@Override
	public String getLogMessage(String transformedName) {
		return "[CicadaTweaks] Patching saddled horses not to wander...";
	}
	
	@Override
	public void doPatch(String transformedName, ClassNode node) {
        int remove = -1;

        for (int i = 0; i < node.methods.size(); i++) {
            if (node.methods.get(i).name.equals("getPosition")) {
                remove = i;
                break;
            }
        }

        if (remove != -1) {
            MethodNode mnode = node.methods.get(remove);

            mnode.instructions.clear();
            AnnotationVisitor av0 = mnode.visitAnnotation("Ljavax/annotation/Nullable;", true);
            av0.visitEnd();
            mnode.visitCode();
            mnode.visitVarInsn(ALOAD, 0);
            String entity = "field_75457_a";
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/ai/EntityAIWanderAvoidWater", resolveDeobf("entity", entity), "Lnet/minecraft/entity/EntityCreature;");
            mnode.visitTypeInsn(INSTANCEOF, "net/minecraft/entity/passive/AbstractHorse");
            Label l0 = new Label();
            mnode.visitJumpInsn(IFEQ, l0);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/ai/EntityAIWanderAvoidWater", resolveDeobf("entity", entity), "Lnet/minecraft/entity/EntityCreature;");
            mnode.visitTypeInsn(CHECKCAST, "net/minecraft/entity/passive/AbstractHorse");
            mnode.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/passive/AbstractHorse", resolveDeobf("isHorseSaddled", "func_110257_ck"), "()Z", false);
            mnode.visitJumpInsn(IFEQ, l0);
            mnode.visitInsn(ACONST_NULL);
            mnode.visitInsn(ARETURN);
            mnode.visitLabel(l0);
            mnode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/ai/EntityAIWanderAvoidWater", resolveDeobf("entity", entity), "Lnet/minecraft/entity/EntityCreature;");
            mnode.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/EntityCreature", resolveDeobf("isInWater", "func_70090_H"), "()Z", false);
            Label l1 = new Label();
            mnode.visitJumpInsn(IFEQ, l1);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/ai/EntityAIWanderAvoidWater", resolveDeobf("entity", entity), "Lnet/minecraft/entity/EntityCreature;");
            mnode.visitIntInsn(BIPUSH, 15);
            mnode.visitIntInsn(BIPUSH, 7);
            mnode.visitMethodInsn(INVOKESTATIC, "net/minecraft/entity/ai/RandomPositionGenerator", resolveDeobf("getLandPos", "func_191377_b"), "(Lnet/minecraft/entity/EntityCreature;II)Lnet/minecraft/util/math/Vec3d;", false);
            mnode.visitVarInsn(ASTORE, 1);
            mnode.visitVarInsn(ALOAD, 1);
            Label l2 = new Label();
            mnode.visitJumpInsn(IFNONNULL, l2);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKESPECIAL, "net/minecraft/entity/ai/EntityAIWander", resolveDeobf("getPosition", "func_190864_f"), "()Lnet/minecraft/util/math/Vec3d;", false);
            Label l3 = new Label();
            mnode.visitJumpInsn(GOTO, l3);
            mnode.visitLabel(l2);
            mnode.visitFrame(Opcodes.F_APPEND,1, new Object[] {"net/minecraft/util/math/Vec3d"}, 0, null);
            mnode.visitVarInsn(ALOAD, 1);
            mnode.visitLabel(l3);
            mnode.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"net/minecraft/util/math/Vec3d"});
            mnode.visitInsn(ARETURN);
            mnode.visitLabel(l1);
            mnode.visitFrame(Opcodes.F_CHOP,1, null, 0, null);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/ai/EntityAIWanderAvoidWater", resolveDeobf("entity", entity), "Lnet/minecraft/entity/EntityCreature;");
            mnode.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/EntityCreature", resolveDeobf("getRNG", "func_70681_au"), "()Ljava/util/Random;", false);
            mnode.visitMethodInsn(INVOKEVIRTUAL, "java/util/Random", "nextFloat", "()F", false);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/ai/EntityAIWanderAvoidWater", "probability", "F");
            mnode.visitInsn(FCMPL);
            Label l4 = new Label();
            mnode.visitJumpInsn(IFLT, l4);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitFieldInsn(GETFIELD, "net/minecraft/entity/ai/EntityAIWanderAvoidWater", resolveDeobf("entity", entity), "Lnet/minecraft/entity/EntityCreature;");
            mnode.visitIntInsn(BIPUSH, 10);
            mnode.visitIntInsn(BIPUSH, 7);
            mnode.visitMethodInsn(INVOKESTATIC, "net/minecraft/entity/ai/RandomPositionGenerator", resolveDeobf("getLandPos", "func_191377_b"), "(Lnet/minecraft/entity/EntityCreature;II)Lnet/minecraft/util/math/Vec3d;", false);
            Label l5 = new Label();
            mnode.visitJumpInsn(GOTO, l5);
            mnode.visitLabel(l4);
            mnode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mnode.visitVarInsn(ALOAD, 0);
            mnode.visitMethodInsn(INVOKESPECIAL, "net/minecraft/entity/ai/EntityAIWander", resolveDeobf("getPosition", "func_190864_f"), "()Lnet/minecraft/util/math/Vec3d;", false);
            mnode.visitLabel(l5);
            mnode.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"net/minecraft/util/math/Vec3d"});
            mnode.visitInsn(ARETURN);
            mnode.visitMaxs(3, 2);
            mnode.visitEnd();
        }
	}
}
