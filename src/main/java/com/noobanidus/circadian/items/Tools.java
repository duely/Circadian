package com.noobanidus.circadian.items;

import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import cofh.core.item.tool.*;

import com.noobanidus.circadian.Circadian;

public class Tools {
    public static final Tools INSTANCE = new Tools();

    public static final ToolMaterial TOOL_MATERIAL_BRASS = EnumHelper.addToolMaterial("TC:ALCHEMICAL_BRASS", 2, 325, 6.0F, 2.0F, 10);

    private static final ToolMaterial TOOL_MATERIAL_COMPRESSED_COBBLESTONE = EnumHelper.addToolMaterial("EX:COMPRESSED_COBBLESTONE", 1, 1179, 4.0f, 1.0f, 5);

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

    public ItemStack toolCCSword;
    public ItemStack toolCCShovel;
    public ItemStack toolCCPickaxe;
    public ItemStack toolCCAxe;
    public ItemStack toolCCHoe;
    public ItemStack toolCCHammer;

    private boolean enabledBrass;
    private boolean enabledCompressed;

    private Tools () {
    }

    public void preInit () {
        enabledBrass = (boolean) Circadian.CONFIG.get("Tools.Brass", "Enable", false, "Enable tools made of alchemical brass.");

        enabledCompressed = (boolean) Circadian.CONFIG.get("Tools.Compressed", "Enable", true, "Enable tools made of compressed cobblestone.");

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
        }

        if (enabledCompressed) {
            itemCCSword = new ItemSwordCore(TOOL_MATERIAL_BRASS);
            itemCCShovel = new ItemShovelCore(TOOL_MATERIAL_BRASS);
            itemCCPickaxe = new ItemPickaxeCore(TOOL_MATERIAL_BRASS);
            itemCCAxe = new ItemAxeCore(TOOL_MATERIAL_BRASS);
            itemCCHoe = new ItemHoeCore(TOOL_MATERIAL_BRASS);
            itemCCHammer = new ItemHammerCore(TOOL_MATERIAL_BRASS);

            itemCCSword.setRepairIngot("").setUnlocalizedName("compressed_stone_sword").setRegistryName("circadian", "compressed_stone_sword");
            itemCCShovel.setRepairIngot("").setUnlocalizedName("compressed_stone_shovel").setRegistryName("circadian", "compressed_stone_shovel");
            itemCCPickaxe.setRepairIngot("").setUnlocalizedName("compressed_stone_pick").setRegistryName("circadian", "compressed_stone_pick");
            itemCCAxe.setRepairIngot("").setUnlocalizedName("compressed_stone_axe").setRegistryName("circadian", "compressed_stone_axe");
            itemCCHoe.setRepairIngot("").setUnlocalizedName("compressed_stone_hoe").setRegistryName("circadian", "compressed_stone_hoe");
            itemCCHammer.setRepairIngot("").setUnlocalizedName("compressed_stone_hammer").setRegistryName("circadian", "compressed_stone_hammer");

            ForgeRegistries.ITEMS.register(itemCCSword);
            ForgeRegistries.ITEMS.register(itemCCShovel);
            ForgeRegistries.ITEMS.register(itemCCPickaxe);
            ForgeRegistries.ITEMS.register(itemCCAxe);
            ForgeRegistries.ITEMS.register(itemCCHoe);
            ForgeRegistries.ITEMS.register(itemCCHammer);

            toolCCSword = new ItemStack(itemCCSword);
            toolCCShovel = new ItemStack(itemCCShovel);
            toolCCPickaxe = new ItemStack(itemCCPickaxe);
            toolCCAxe = new ItemStack(itemCCAxe);
            toolCCHoe = new ItemStack(itemCCHoe);
            toolCCHammer = new ItemStack(itemCCHammer);
        }

    }

    @SideOnly(Side.CLIENT)
    public void registerModels () {
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
        }
    }
}
