package com.noobanidus.circadian.events;

import cofh.core.util.helpers.BaublesHelper;
import com.google.common.collect.Iterables;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.config.Registrar;
import com.noobanidus.circadian.enchantment.EnchantmentManabound;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

import java.util.Arrays;

public class CircadianEvents {

    private static int manaCost = Circadian.CONFIG.get("Enchantments.Manabound", "Cost", 370, "Cost to repair a point of damage.").getInt(370);

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        if (EnchantmentManabound.enabled && event.getEntity() instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) event.getEntity();

            Iterable<ItemStack> equipment = Iterables.concat(Arrays.asList(player.inventory.mainInventory, player.inventory.armorInventory, player.inventory.offHandInventory, BaublesHelper.getBaubles(player)));

            for (ItemStack stack : equipment) {
                if (!(stack.getItem() instanceof IManaUsingItem) && stack.isItemDamaged() && stack.isItemEnchanted() && (EnchantmentHelper.getEnchantments(stack).get(Registrar.manabound) != null)) {
                    if (ManaItemHandler.requestManaExactForTool(stack, player, manaCost, true)) {
                        stack.setItemDamage(stack.getItemDamage() - 1);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTrampleEvent(BlockEvent.FarmlandTrampleEvent event) {
        if (Circadian.CONFIG.get("Farmland", "Trample", true, "Disable trampling of farmland.").getBoolean(true)) {
            event.setCanceled(true);
        }
    }
}
