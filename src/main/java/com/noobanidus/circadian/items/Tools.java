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
    private static final ToolMaterial TOOL_MATERIAL_COMPRESSED_COBBLESTONE = EnumHelper.addToolMaterial("EX:COMPRESSED_COBBLESTONE", 1, 750, 3.5f, 1.0f, 5);
    private static final ToolMaterial TOOL_MATERIAL_DOUBLE_COMPRESSED_COBBLESTONE = EnumHelper.addToolMaterial("EX:DOUBLE_COMPRESSED_COBBLESTONE", 1, 1500, 3.5f, 1.0f, 5);
    public static boolean enabledCompressed = Circadian.CONFIG.get("Tools.Compressed", "Enable", true, "Enable tools made of compressed cobblestone and stone.").getBoolean(true);
    public static boolean enabledDoubleCompressed = Circadian.CONFIG.get("Tools.DoubleCompressed", "Enable", true, "Enable tools made out of double-compressed cobblestone and stone.").getBoolean(true);
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
