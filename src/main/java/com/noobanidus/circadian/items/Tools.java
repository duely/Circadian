package circadian.items;

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

import circadian.Circadian;

public class Tools {
    public static final Tools INSTANCE = new Tools();

    public static final ToolMaterial TOOL_MATERIAL_BRASS = EnumHelper.addToolMaterial("TC:ALCHEMICAL_BRASS", 2, 325, 6.0F, 2.0F, 10);

    public ItemSwordCore itemSword;
    public ItemShovelCore itemShovel;
    public ItemPickaxeCore itemPickaxe;
    public ItemAxeCore itemAxe;
    public ItemHoeCore itemHoe;
    public ItemHammerCore itemHammer;

    public ItemStack toolSword;
    public ItemStack toolShovel;
    public ItemStack toolPickaxe;
    public ItemStack toolAxe;
    public ItemStack toolHoe;
    public ItemStack toolHammer;

    private Tools () {
    }

    public void preInit () {
        Circadian.LOG.info("Circadian: Creating brass tools.");
        itemSword = new ItemSwordCore(TOOL_MATERIAL_BRASS);
        itemShovel = new ItemShovelCore(TOOL_MATERIAL_BRASS);
        itemPickaxe = new ItemPickaxeCore(TOOL_MATERIAL_BRASS);
        itemAxe = new ItemAxeCore(TOOL_MATERIAL_BRASS);
        itemHoe = new ItemHoeCore(TOOL_MATERIAL_BRASS);
        itemHammer = new ItemHammerCore(TOOL_MATERIAL_BRASS);

        itemSword.setRepairIngot("ingotBrass").setUnlocalizedName("Brass Sword");
        itemSword.setRegistryName("circadian", "brass_sword");
        
        Circadian.LOG.info("Circadian: Registering brass sword.");
        ForgeRegistries.ITEMS.register(itemSword);

        itemShovel.setRepairIngot("ingotBrass").setUnlocalizedName("Brass Shovel");
        itemShovel.setRegistryName("circadian", "brass_shovel");

        Circadian.LOG.info("Circadian: Registering brass shovel.");
        ForgeRegistries.ITEMS.register(itemShovel);

        itemPickaxe.setRepairIngot("ingotBrass").setUnlocalizedName("Brass Pickaxe");
        itemPickaxe.setRegistryName("circadian", "brass_pickaxe");

        Circadian.LOG.info("Circadian: Registering brass pickaxe.");
        ForgeRegistries.ITEMS.register(itemPickaxe);

        itemAxe.setRepairIngot("ingotBrass").setUnlocalizedName("Brass Axe");
        itemAxe.setRegistryName("circadian", "brass_axe");

        Circadian.LOG.info("Circadian: Registering brass axe.");
        ForgeRegistries.ITEMS.register(itemAxe);

        itemHoe.setRepairIngot("ingotBrass").setUnlocalizedName("Brass Hoe");
        itemHoe.setRegistryName("circadian", "brass_hoe");

        Circadian.LOG.info("Circadian: Registering brass hoe.");
        ForgeRegistries.ITEMS.register(itemHoe);

        itemHammer.setRepairIngot("ingotBrass").setUnlocalizedName("Brass Hammer");
        itemHammer.setRegistryName("circadian", "brass_hammer");

        Circadian.LOG.info("Circadian: Registering brass hammer.");
        ForgeRegistries.ITEMS.register(itemHammer);

        toolSword = new ItemStack(itemSword);
        toolShovel = new ItemStack(itemShovel);
        toolPickaxe = new ItemStack(itemPickaxe);
        toolAxe = new ItemStack(itemAxe);
        toolHoe = new ItemStack(itemHoe);
        toolHammer = new ItemStack(itemHammer);
    }

    @SideOnly(Side.CLIENT)
    public void registerModels () {
        Circadian.LOG.info("Circadian: Registering sword model.");
        ModelLoader.setCustomModelResourceLocation(itemSword, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_sword"), "inventory"));
        Circadian.LOG.info("Circadian: Registering shovel model.");
        ModelLoader.setCustomModelResourceLocation(itemShovel, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_shovel"), "inventory"));
        Circadian.LOG.info("Circadian: Registering pickaxe model.");
        ModelLoader.setCustomModelResourceLocation(itemPickaxe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_pickaxe"), "inventory"));
        Circadian.LOG.info("Circadian: Registering axe model.");
        ModelLoader.setCustomModelResourceLocation(itemAxe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_axe"), "inventory"));
        Circadian.LOG.info("Circadian: Registering hoe model.");
        ModelLoader.setCustomModelResourceLocation(itemHoe, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_hoe"), "inventory"));
        Circadian.LOG.info("Circadian: Registering hammer model.");
        ModelLoader.setCustomModelResourceLocation(itemHammer, 0, new ModelResourceLocation(new ResourceLocation("circadian", "brass_hammer"), "inventory"));
    }
}
