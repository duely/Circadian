package com.noobanidus.circadian.compat.vanilla.handlers;

import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.config.Registrar;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HorseBreedingHandler {
    public static boolean enabled = Circadian.CONFIG.get("Vanilla.Animals", "SilverBreeding", true, "Patch vanilla Horse breeding to use silvered versions of carrots, potatos and apples.").getBoolean(true);

    @SubscribeEvent
    public static void OnInteract(PlayerInteractEvent.EntityInteract event) {
        if (!enabled) return;

        EntityPlayer player = event.getEntityPlayer();
        if (player.world.isRemote) return;

        ItemStack item = event.getItemStack();

        if (item.isEmpty() || !(event.getTarget() instanceof AbstractHorse)) return;

        Item type = item.getItem();
        AbstractHorse horse = (AbstractHorse) event.getTarget();

        float f;
        int i;
        int j = 0;

        if (type == Registrar.silveredCarrot || type == Registrar.silveredPotato) {
            f = 4.0F;
            i = 60;
            j = 5;
        } else if (type == Registrar.silveredApple || type == Registrar.goldenPotato) {
            f = 10.0F;
            i = 240;
            j = 10;
        } else {
            return;
        }

        boolean didStuff = false;

        if (horse.isTame() && horse.getGrowingAge() == 0 && !horse.isInLove()) {
            horse.setInLove(player);
            didStuff = true;
        }

        if (horse.getHealth() < horse.getMaxHealth()) {
            horse.heal(f);
            didStuff = true;
        }

        if (horse.isChild()) {
            horse.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, horse.posX + (double) (horse.rand.nextFloat() * horse.width * 2.0F) - (double) horse.width, horse.posY + 0.5D + (double) (horse.rand.nextFloat() * horse.height), horse.posZ + (double) (horse.rand.nextFloat() * horse.width * 2.0F) - (double) horse.width, 0.0D, 0.0D, 0.0D);

            horse.addGrowth(i);
            didStuff = true;
        }

        if (didStuff) {
            horse.eatingHorse();

            if (!player.capabilities.isCreativeMode) {
                item.shrink(1);
            }

            event.setCanceled(true);
        }
    }
}
