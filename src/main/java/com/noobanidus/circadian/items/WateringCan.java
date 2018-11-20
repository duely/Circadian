package com.noobanidus.circadian.items;

import cofh.core.init.CoreEnchantments;
import cofh.core.util.helpers.ItemHelper;
import cofh.thermalcultivation.item.ItemWateringCan;
import com.noobanidus.circadian.Circadian;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@SuppressWarnings("WeakerAccess")
public class WateringCan extends ItemWateringCan {

    public static final int CAPACITY_BASE = 8000;
    public static boolean enabled = Circadian.CONFIG.get("Items.WateringCans", "Enable", true, "Enable magical watering cans.");
    public static Int2ObjectOpenHashMap<TypeEntry> typeMap = new Int2ObjectOpenHashMap<>();
    public static ItemStack manasteel;
    public static ItemStack thaumium;
    public static ItemStack terrasteel;
    public static ItemStack elementium;
    public static ItemStack voidmetal;

    public WateringCan() {
        this.modName = "circadian";

        setUnlocalizedName("watering_can");
        setCreativeTab(Circadian.TAB);

        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int metadata : itemList) {
            items.add(setDefaultTag(new ItemStack(this, 1, metadata), 0));
        }
    }

    @Override
    public int getCapacity(ItemStack stack) {

        if (!typeMap.containsKey(ItemHelper.getItemDamage(stack))) {
            return 0;
        }
        int capacity = typeMap.get(ItemHelper.getItemDamage(stack)).capacity;
        int enchant = EnchantmentHelper.getEnchantmentLevel(CoreEnchantments.holding, stack);

        return capacity + capacity * enchant / 2;
    }

    public boolean init() {
        setRegistryName("circadian", "watering_can");

        ForgeRegistries.ITEMS.register(this);

        typeMap.put(0, new TypeEntry("standard0", CAPACITY_BASE, 40, 1));
        typeMap.put(1, new TypeEntry("standard1", CAPACITY_BASE * 2, 50, 2));
        typeMap.put(2, new TypeEntry("standard2", CAPACITY_BASE * 6, 60, 3));
        typeMap.put(3, new TypeEntry("standard3", CAPACITY_BASE * 10, 70, 4));
        typeMap.put(4, new TypeEntry("standard4", CAPACITY_BASE * 15, 80, 5));

        manasteel = addItem(0, "standard0", EnumRarity.COMMON);
        thaumium = addItem(1, "standard1", EnumRarity.COMMON);
        terrasteel = addItem(2, "standard2", EnumRarity.UNCOMMON);
        elementium = addItem(3, "standard3", EnumRarity.UNCOMMON);
        voidmetal = addItem(4, "standard4", EnumRarity.RARE);

        allowFakePlayers = true;
        removeSourceBlocks = false;

        return true;
    }

    @SuppressWarnings("WeakerAccess")
    public class TypeEntry {

        public final String name;
        public final int capacity;
        public final int chance;
        public final int radius;

        TypeEntry(String name, int capacity, int chance, int radius) {
            this.name = name;
            this.capacity = capacity;
            this.chance = chance;
            this.radius = radius;
        }
    }
}
