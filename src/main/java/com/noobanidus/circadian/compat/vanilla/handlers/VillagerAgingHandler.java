package com.noobanidus.circadian.compat.vanilla.handlers;

import com.noobanidus.circadian.Circadian;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.common.block.tile.TileCocoon;

public class VillagerAgingHandler {
    public static boolean enabled = Circadian.CONFIG.get("Vanilla.Villagers", "EmeraldAging", true, "Feeding baby villagers emeralds will cause them to grow.").getBoolean(true);

    @SubscribeEvent
    public static void OnInteract (PlayerInteractEvent.EntityInteract event) {
        if (!enabled) return;

        EntityPlayer player = event.getEntityPlayer();
        if (player.world.isRemote) return;

        ItemStack item = event.getItemStack();

        if (item.isEmpty() || !(event.getTarget() instanceof EntityVillager)) return;

        Item type = item.getItem();
        EntityVillager villager = (EntityVillager) event.getTarget();

        if (villager.isChild() && type == Items.EMERALD) {
            // these don't work
            // villager.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, villager.posX + (double) (villager.rand.nextFloat() * villager.width * 2.0F) - (double) villager.width, villager.posY + 0.5D + (double) (villager.rand.nextFloat() * villager.height), villager.posZ + (double) (villager.rand.nextFloat() * villager.width * 2.0F) - (double) villager.width, 0.0D, 0.0D, 0.0D);

            // so I stole this from botania
            BlockPos pos = villager.getPos();
            ((WorldServer) villager.world).spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 2, 0, 0, 0, 0.5);

            villager.addGrowth(240);

            if (!player.capabilities.isCreativeMode) {
                item.shrink(1);
            }

            event.setCanceled(true);
        }
    }
}

