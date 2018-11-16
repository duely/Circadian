package com.noobanidus.circadian.enchantment;

import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraft.item.ItemStack;

import com.noobanidus.circadian.Circadian;

public class EnchantmentManabound extends Enchantment {
    public static boolean enabled = enabled = Circadian.CONFIG.get("Enchantments.Manabound", "Enable", true, "Enable manabound enchantment.");

	public EnchantmentManabound(String id) {
		super(Rarity.VERY_RARE, EnumEnchantmentType.BREAKABLE, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND, EntityEquipmentSlot.FEET, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.HEAD });
		setRegistryName(id);
	}

	@Override
	public int getMinEnchantability(int level) {
		return 25;
	}

	@Override
	public int getMaxEnchantability(int level) {
		return 75;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public String getName() {
		return "enchantment.circadian.manabound";
	}

	@Override
	public boolean canApply(ItemStack stack) {
		Item item = stack.getItem();
        return (item instanceof ItemArmor || item instanceof ItemTool) && !(item instanceof IManaUsingItem);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return canApply(stack);
	}

	@Override
	public boolean isAllowedOnBooks() {
		return true;
	}

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

}
