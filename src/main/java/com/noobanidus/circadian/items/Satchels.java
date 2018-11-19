package com.noobanidus.circadian.items;

import cofh.core.util.helpers.RecipeHelper;
import cofh.thermalexpansion.item.ItemSatchel;
import com.noobanidus.circadian.Circadian;
import thaumcraft.api.items.ItemsTC;
import vazkii.botania.common.item.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Satchels extends ItemSatchel {

    public static boolean enabled = Circadian.CONFIG.get("Items.Satchels", "Enable", true, "Enable magical satchels.");

    public Satchels() {
        super();

        this.modName = "circadian";

        setUnlocalizedName("satchel");
        setCreativeTab(Circadian.TAB);
        setRegistryName("circadian", "satchel");

        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int metadata : itemList) {
                items.add(setDefaultInventoryTag(new ItemStack(this, 1, metadata)));
        }
    }

    public boolean init() {
        ForgeRegistries.ITEMS.register(this);

        manasteel = addEntryItem(0, "standard0", 0, EnumRarity.COMMON);
        thaumium = addEntryItem(1, "standard1", 1, EnumRarity.COMMON);
        terrasteel = addEntryItem(2, "standard2", 2, EnumRarity.UNCOMMON);
        elementium = addEntryItem(3, "standard3", 3, EnumRarity.UNCOMMON);
        voidmetal = addEntryItem(4, "standard4", 4, EnumRarity.RARE);

        return true;
    }

    public void registerRecipes() {
        RecipeHelper.addShapedRecipe(manasteel,
                "IPI",
                "LCL",
                "LLL",
                'I', "ingotManasteel",
                'P', "plateManasteel",
                'L', "itemLeather",
                'C', "chestIron"
        );
        RecipeHelper.addShapedUpgradeRecipe(manasteel,
                "IPI",
                "SCS",
                "LLL",
                'I', "ingotThaumium",
                'P', "plateThaumium",
                'S', "quicksilver",
                'C', ItemSatchel.satchelBasic,
                'L', ItemsTC.fabric
        );

        RecipeHelper.addShapedUpgradeRecipe(terrasteel,
                "IPI",
                "SCS",
                "LLL",
                'I', "ingotTerrasteel",
                'P', "plateTerrasteel",
                'S', "manaDiamond",
                'C', ItemSatchel.satchelHardened,
                'L', "clothManaweave"
        );
        RecipeHelper.addShapedUpgradeRecipe(elementium,
                "IPI",
                "SCS",
                "LLL",
                'I', "ingotElvenElementium",
                'P', "plateElementium",
                'S', "gemResonating",
                'C', ItemSatchel.satchelReinforced,
                'L', ModItems.spellCloth
        );

        RecipeHelper.addShapedUpgradeRecipe(voidmetal,
                "IPI",
                "SCS",
                "LGL",
                'I', "ingotVoid",
                'P', "plateVoid",
                'S', "blockPurpur",
                'C', ItemSatchel.satchelSignalum,
                'L', "clothCrimson",
                'G', ItemsTC.primordialPearl
        );
    }

    public class TypeEntry {

        public final String name;
        public final int level;

        TypeEntry(String name, int level) {

            this.name = name;
            this.level = level;
        }
    }

    private ItemStack addEntryItem(int metadata, String name, int level, EnumRarity rarity) {

        typeMap.put(metadata, new TypeEntry(name, level));
        return addItem(metadata, name, rarity);
    }

    private static Int2ObjectOpenHashMap<TypeEntry> typeMap = new Int2ObjectOpenHashMap<>();

    /* REFERENCES */
    public static ItemStack manasteel;
    public static ItemStack thaumium;
    public static ItemStack terrasteel;
    public static ItemStack elementium;
    public static ItemStack voidmetal;
}
