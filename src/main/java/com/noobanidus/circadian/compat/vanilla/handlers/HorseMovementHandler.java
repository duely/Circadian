package com.noobanidus.circadian.compat.vanilla.handlers;

// Leaf-walking/swimming code taken from MIT-licensed HorseTweaks
// https://github.com/blay09/HorseTweaks/blob/master/src/main/java/net/blay09/mods/horsetweaks/tweaks/LeafWalkerHandler.java

import com.noobanidus.circadian.Circadian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class HorseMovementHandler {
    public static boolean leafEnabled = Circadian.CONFIG.get("Vanilla.Horse", "WalkThroughLeaves", true, "Set to false to disable walking through leaves on horseback by default.").getBoolean(true);
    public static boolean swimmingEnabled = Circadian.CONFIG.get("Vanilla.Horse", "AllowSwimming", true, "Set to false to prevent horses from gaining vertical motion while in water (allowing swimming).").getBoolean(true);

    @SubscribeEvent
    public static void onLeafEvent(GetCollisionBoxesEvent event) {
        if (!leafEnabled || event.getEntity() == null || !event.getEntity().isBeingRidden() || !(event.getEntity() instanceof AbstractHorse)) {
            return;
        }

        List<AxisAlignedBB> collisions = event.getCollisionBoxesList();
        for (int i = collisions.size() - 1; i >= 0; i--) {
            AxisAlignedBB aabb = collisions.get(i);
            BlockPos pos = new BlockPos(aabb.minX + (aabb.maxX - aabb.minX) * 0.5f, aabb.minY + (aabb.maxY - aabb.minY) * 0.5f, aabb.minZ + (aabb.maxZ - aabb.minZ) * 0.5f);
            IBlockState state = event.getWorld().getBlockState(pos);
            if (state.getBlock().isLeaves(state, event.getWorld(), pos) && event.getEntity().posY < aabb.maxY) {
                event.getCollisionBoxesList().remove(i);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onSwimEvent(TickEvent.ClientTickEvent event) {
        if (!swimmingEnabled || FMLClientHandler.instance().getClient().isGamePaused()) {
            return;
        }

        EntityPlayerSP player = FMLClientHandler.instance().getClientPlayerEntity();
        if (player != null && player.getRidingEntity() instanceof AbstractHorse) {
            AbstractHorse horse = (AbstractHorse) player.getRidingEntity();
            if (horse.isInLava() || horse.isInWater()) {
                horse.addVelocity(0f, 0.0125f, 0f);
            }
        }
    }
}
