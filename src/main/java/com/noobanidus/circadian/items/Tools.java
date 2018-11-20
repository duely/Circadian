package com.noobanidus.circadian.items;

import cofh.core.item.tool.*;
import com.noobanidus.circadian.Circadian;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("WeakerAccess")
public class Tools {
    public static final ToolMaterial TOOL_MATERIAL_BRASS = EnumHelper.addToolMaterial("TC:ALCHEMICAL_BRASS", 2, 325, 6.0F, 2.0F, 10);
    private static final ToolMaterial TOOL_MATERIAL_COMPRESSED_COBBLESTONE = EnumHelper.addToolMaterial("EX:COMPRESSED_COBBLESTONE", 1, 750, 3.5f, 1.0f, 5);
    private static final ToolMaterial TOOL_MATERIAL_DOUBLE_COMPRESSED_COBBLESTONE = EnumHelper.addToolMaterial("EX:DOUBLE_COMPRESSED_COBBLESTONE", 1, 1500, 3.5f, 1.0f, 5);
    public static boolean enabledBrass = Circadian.CONFIG.get("Tools.Brass", "Enable", false, "Enable tools made of alchemical brass.");
    public static boolean enabledCompressed = Circadian.CONFIG.get("Tools.Compressed", "Enable", true, "Enable tools made of compressed cobblestone and stone.");
    public static boolean enabledDoubleCompressed = Circadian.CONFIG.get("Tools.DoubleCompressed", "Enable", true, "Enable tools made out of double-compressed cobblestone and stone.");
    public ItemSwordCore itemBrassSword;
    public ItemShovelCore itemBrassShovel;
    public ItemPickaxeCore itemBrassPickaxe;
    public ItemAxeCore itemBrassAxe;
    public ItemHoeCore itemBrassHoe;
    public ItemHammerCore itemBrassHammer;
    public ItemStack toolBrassSword;
    public ItemStack toolBrassShovel;
    public ItemStack toolBrassPickaxe;
    public ItemStack toolBrassAxe;
    public ItemStack toolBrassHoe;
    public ItemStack toolBrassHammer;
    public ItemSwordCore itemCCSword;
    public ItemShovelCore itemCCShovel;
    public ItemPickaxeCore itemCCPickaxe;
    public ItemAxeCore itemCCAxe;
    public ItemHoeCore itemCCHoe;
    public ItemHammerCore itemCCHammer;
    public ItemExcavatorCore itemCCExcavator;
    public ItemStack toolCCSword;
    public ItemStack toolCCShovel;
    public ItemStack toolCCPickaxe;
    public ItemStack toolCCAxe;
    public ItemStack toolCCHoe;
    public ItemStack toolCCHammer;
    public ItemStack toolCCExcavator;
    public ItemSwordCore itemDCCSword;
    public ItemShovelCore itemDCCShovel;
    public ItemPickaxeCore itemDCCPickaxe;
    public ItemAxeCore itemDCCAxe;
    public ItemHoeCore itemDCCHoe;
    public ItemHammerCore itemDCCHammer;
    public ItemExcavatorCore itemDCCExcavator;
    public ItemStack toolDCCSword;
    public ItemStack toolDCCShovel;
    public ItemStack toolDCCPickaxe;
    public ItemStack toolDCCAxe;
    public ItemStack toolDCCHoe;
    public ItemStack toolDCCHammer;
    public ItemStack toolDCCExcavator;

    public Tools() {
    }

    @SuppressWarnings("ConstantConditions")
    public void init() {
        if (enabledBrass) {
            itemBrassSword = new ItemSwordCore(TOOL_MATERIAL_BRASS);
            itemBrassShovel = new ItemShovelCore(TOOL_MATERIAL_BRASS);
            itemBrassPickaxe = new ItemPickaxeCore(TOOL_MATERIAL_BRASS);
            itemBrassAxe = new ItemAxeCore(TOOL_MATERIAL_BRASS);
            itemBrassHoe = new ItemHoeCore(TOOL_MATERIAL_BRASS);
            itemBrassHammer = new ItemHammerCore(TOOL_MATERIAL_BRASS);

            itemBrassSword.setRepairIngot("ingotBrass").setUnlocalizedName("brass_sword").setRegistryName("circadian", "brass_sword");
            itemBrassShovel.setRepairIngot("ingotBrass").setUnlocalizedName("brass_shovel").setRegistryName("circadian", "brass_shover");
            itemBrassPickaxe.setRepairIngot("ingotBrass").setUnlocalizedName("brass_pickaxe").setRegistryName("circadian", "brass_pickaxe");
            itemBrassAxe.setRepairIngot("ingotBrass").setUnlocalizedName("brass_axe").setRegistryName("circadian", "brass_axe");
            itemBrassHoe.setRepairIngot("ingotBrass").setUnlocalizedName("brass_hoe").setRegistryName("circadian", "brass_hoe");
            itemBrassHammer.setRepairIngot("ingotBrass").setUnlocalizedName("brass_hammer").setRegistryName("circadian", "brass_hammer");

            ForgeRegistries.ITEMS.register(itemBrassSword);
            ForgeRegistries.ITEMS.register(itemBrassShovel);
            ForgeRegistries.ITEMS.register(itemBrassPickaxe);
            ForgeRegistries.ITEMS.register(itemBrassAxe);
            ForgeRegistries.ITEMS.register(itemBrassHoe);
            ForgeRegistries.ITEMS.register(itemBrassHammer);

            toolBrassSword = new ItemStack(itemBrassSword);
            toolBrassShovel = new ItemStack(itemBrassShovel);
            toolBrassPickaxe = new ItemStack(itemBrassPickaxe);
            toolBrassAxe = new ItemStack(itemBrassAxe);
            toolBrassHoe = new ItemStack(itemBrassHoe);
            toolBrassHammer = new ItemStack(itemBrassHammer);

            itemBrassSword.setCreativeTab(Circadian.TAB);
            itemBrassShovel.setCreativeTab(Circadian.TAB);
            itemBrassPickaxe.setCreativeTab(Circadian.TAB);
            itemBrassAxe.setCreativeTab(Circadian.TAB);
            itemBrassHoe.setCreativeTab(Circadian.TAB);
            itemBrassHammer.setCreativeTab(Circadian.TAB);
        }

        if (enabledCompressed) {
            itemCCSword = new ItemSwordCore(TOOL_MATERIAL_COMPRESSED_COBBLESTONE);
            itemCCShovel = new ItemShovelCore(TOOL_MATERIAL_COMPRESSED_COBBLESTONE);
            itemCCPickaxe = new ItemPickaxeCore(TOOL_MATERIAL_COMPRESSED_COBBLESTONE);
            itemCCAxe = new ItemAxeCore(TOOL_MATERIAL_COMPRESSED_COBBLESTONE);
            itemCCHoe = new ItemHoeCore(TOOL_MATERIAL_COMPRESSED_COBBLESTONE);
            itemCCHammer = new ItemHammerCore(TOOL_MATERIAL_COMPRESSED_COBBLESTONE);
            itemCCExcavator = new ItemExcavatorCore(TOOL_MATERIAL_COMPRESSED_COBBLESTONE);

            itemCCSword.setRepairIngot("").setUnlocalizedName("compressed_stone_sword").setRegistryName("circadian", "compressed_stone_sword");
            itemCCShovel.setRepairIngot("").setUnlocalizedName("compressed_stone_shovel").setRegistryName("circadian", "compressed_stone_shovel");
            itemCCPickaxe.setRepairIngot("").setUnlocalizedName("compressed_stone_pick").setRegistryName("circadian", "compressed_stone_pick");
            itemCCAxe.setRepairIngot("").setUnlocalizedName("compressed_stone_axe").setRegistryName("circadian", "compressed_stone_axe");
            itemCCHoe.setRepairIngot("").setUnlocalizedName("compressed_stone_hoe").setRegistryName("circadian", "compressed_stone_hoe");
            itemCCHammer.setRepairIngot("").setUnlocalizedName("compressed_stone_hammer").setRegistryName("circadian", "compressed_stone_hammer");
            itemCCExcavator.setRepairIngot("").setUnlocalizedName("compressed_stone_excavator").setRegistryName("circadian", "compressed_stone_excavator");

            ForgeRegistries.ITEMS.register(itemCCSword);
            ForgeRegistries.ITEMS.register(itemCCShovel);
            ForgeRegistries.ITEMS.register(itemCCPickaxe);
            ForgeRegistries.ITEMS.register(itemCCAxe);
            ForgeRegistries.ITEMS.register(itemCCHoe);
            ForgeRegistries.ITEMS.register(itemCCHammer);
            ForgeRegistries.ITEMS.register(itemCCExcavator);

            toolCCSword = new ItemStack(itemCCSword);
            toolCCShovel = new ItemStack(itemCCShovel);
            toolCCPickaxe = new ItemStack(itemCCPickaxe);
            toolCCAxe = new ItemStack(itemCCAxe);
            toolCCHoe = new ItemStack(itemCCHoe);
            toolCCHammer = new ItemStack(itemCCHammer);
            toolCCExcavator = new ItemStack(itemCCExcavator);

            itemCCSword.setCreativeTab(Circadian.TAB);
            itemCCShovel.setCreativeTab(Circadian.TAB);
            itemCCPickaxe.setCreativeTab(Circadian.TAB);
            itemCCAxe.setCreativeTab(Circadian.TAB);
            itemCCHoe.setCreativeTab(Circadian.TAB);
            itemCCHammer.setCreativeTab(Circadian.TAB);
            itemCCExcavator.setCreativeTab(Circadian.TAB);
        }

        if (enabledDoubleCompressed) {
            itemDCCSword = new ItemSwordCore(TOOL_MATERIAL_DOUBLE_COMPRESSED_COBBLESTONE);
            itemDCCShovel = new ItemShovelCore(TOOL_MATERIAL_DOUBLE_COMPRESSED_COBBLESTONE);
            itemDCCPickaxe = new ItemPickaxeCore(TOOL_MATERIAL_DOUBLE_COMPRESSED_COBBLESTONE);
            itemDCCAxe = new ItemAxeCore(TOOL_MATERIAL_DOUBLE_COMPRESSED_COBBLESTONE);
            itemDCCHoe = new ItemHoeCore(TOOL_MATERIAL_DOUBLE_COMPRESSED_COBBLESTONE);
            itemDCCHammer = new ItemHammerCore(TOOL_MATERIAL_DOUBLE_COMPRESSED_COBBLESTONE);
            itemDCCExcavator = new ItemExcavatorCore(TOOL_MATERIAL_DOUBLE_COMPRESSED_COBBLESTONE);

            itemDCCSword.setRepairIngot("").setUnlocalizedName("double_compressed_stone_sword").setRegistryName("circadian", "double_compressed_stone_sword");
            itemDCCShovel.setRepairIngot("").setUnlocalizedName("double_compressed_stone_shovel").setRegistryName("circadian", "double_compressed_stone_shovel");
            itemDCCPickaxe.setRepairIngot("").setUnlocalizedName("double_compressed_stone_pick").setRegistryName("circadian", "double_compressed_stone_pick");
            itemDCCAxe.setRepairIngot("").setUnlocalizedName("double_compressed_stone_axe").setRegistryName("circadian", "double_compressed_stone_axe");
            itemDCCHoe.setRepairIngot("").setUnlocalizedName("double_compressed_stone_hoe").setRegistryName("circadian", "double_compressed_stone_hoe");
            itemDCCHammer.setRepairIngot("").setUnlocalizedName("double_compressed_stone_hammer").setRegistryName("circadian", "double_compressed_stone_hammer");
            itemDCCExcavator.setRepairIngot("").setUnlocalizedName("double_compressed_stone_excavator").setRegistryName("circadian", "double_compressed_stone_excavator");

            ForgeRegistries.ITEMS.register(itemDCCSword);
            ForgeRegistries.ITEMS.register(itemDCCShovel);
            ForgeRegistries.ITEMS.register(itemDCCPickaxe);
            ForgeRegistries.ITEMS.register(itemDCCAxe);
            ForgeRegistries.ITEMS.register(itemDCCHoe);
            ForgeRegistries.ITEMS.register(itemDCCHammer);
            ForgeRegistries.ITEMS.register(itemDCCExcavator);

            toolDCCSword = new ItemStack(itemDCCSword);
            toolDCCShovel = new ItemStack(itemDCCShovel);
            toolDCCPickaxe = new ItemStack(itemDCCPickaxe);
            toolDCCAxe = new ItemStack(itemDCCAxe);
            toolDCCHoe = new ItemStack(itemDCCHoe);
            toolDCCHammer = new ItemStack(itemDCCHammer);
            toolDCCExcavator = new ItemStack(itemDCCExcavator);

            itemDCCSword.setCreativeTab(Circadian.TAB);
            itemDCCShovel.setCreativeTab(Circadian.TAB);
            itemDCCPickaxe.setCreativeTab(Circadian.TAB);
            itemDCCAxe.setCreativeTab(Circadian.TAB);
            itemDCCHoe.setCreativeTab(Circadian.TAB);
            itemDCCHammer.setCreativeTab(Circadian.TAB);
            itemDCCExcavator.setCreativeTab(Circadian.TAB);
        }

    }

    @SideOnly(Side.CLIENT)
    public void registerModels() {
        if (enabledBrass) {
            ModelLoader.setCustomModelResourceLocation(itemBrassSword, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_sword"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemBrassShovel, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_shovel"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemBrassPickaxe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_pickaxe"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemBrassAxe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_axe"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemBrassHoe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_hoe"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemBrassHammer, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_hammer"), "inventory"));
        }

        if (enabledCompressed) {
            ModelLoader.setCustomModelResourceLocation(itemCCSword, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_stone_sword"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemCCShovel, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_stone_shovel"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemCCPickaxe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_stone_pick"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemCCAxe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_stone_axe"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemCCHoe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_stone_hoe"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemCCHammer, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_stone_hammer"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemCCExcavator, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_stone_excavator"), "inventory"));
        }

        if (enabledDoubleCompressed) {
            ModelLoader.setCustomModelResourceLocation(itemDCCSword, 0, new ModelResourceLocation(new ResourceLocation("circadian", "double_compressed_stone_sword"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemDCCShovel, 0, new ModelResourceLocation(new ResourceLocation("circadian", "double_compressed_stone_shovel"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemDCCPickaxe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "double_compressed_stone_pick"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemDCCAxe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "double_compressed_stone_axe"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemDCCHoe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "double_compressed_stone_hoe"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemDCCHammer, 0, new ModelResourceLocation(new ResourceLocation("circadian", "double_compressed_stone_hammer"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(itemDCCExcavator, 0, new ModelResourceLocation(new ResourceLocation("circadian", "double_compressed_stone_excavator"), "inventory"));
        }
    }
}
