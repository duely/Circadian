package com.noobanidus.circadian.events;

import cofh.core.util.helpers.BaublesHelper;
import com.google.common.collect.Iterables;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.noobanidus.circadian.config.Registrar;
import com.noobanidus.circadian.enchantment.EnchantmentManabound;

import vazkii.botania.api.mana.ManaItemHandler;

import java.util.Arrays;

import com.noobanidus.circadian.Circadian;

@Mod.EventBusSubscriber
public class PlayerEvents {

    private static int manaCost = -1;

    @SubscribeEvent
    public static void onLivingTick (LivingEvent.LivingUpdateEvent event) {
        if (event.getEntity() instanceof EntityPlayer && ((EnchantmentManabound) Registrar.manabound).enabled) {

            if (manaCost == -1) { 
                manaCost = Circadian.CONFIG.get("Enchantments.Manabound", "Cost", 370, "Cost to repair a point of damage.");
            }

            EntityPlayer player = (EntityPlayer) event.getEntity();

            Iterable<ItemStack> equipment = Iterables.concat(Arrays.asList(player.inventory.mainInventory, player.inventory.armorInventory, player.inventory.offHandInventory, BaublesHelper.getBaubles(player)));

            for (ItemStack stack : equipment) {
                if (stack.isItemDamaged() && stack.isItemEnchanted() && (EnchantmentHelper.getEnchantments(stack).get(Registrar.manabound) != null)) {
                    if (ManaItemHandler.requestManaExactForTool(stack, player, 370, true)) {
            			stack.setItemDamage(stack.getItemDamage() - 1);
                    }
                }
            }
        }
    }
}
