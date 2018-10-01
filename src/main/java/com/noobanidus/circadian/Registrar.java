package com.noobanidus.circadian;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.ModelRegistryEvent;


import com.noobanidus.circadian.config.BlockConfig;
import com.noobanidus.circadian.items.Tools;
import com.noobanidus.circadian.config.Items;

@Mod.EventBusSubscriber
public class Registrar {
    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        BlockConfig.registerItems(event.getRegistry());
        Tools.INSTANCE.preInit();
        Items.initItems();
        Items.registerItems(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        BlockConfig.registerModels();
        Tools.INSTANCE.registerModels();
        Items.registerModels();
    }

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        BlockConfig.initBlocks();
        BlockConfig.registerBlocks(event.getRegistry());

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public static void registerBlocksClient(Register<Block> event) {
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public static void registerItemsClient(Register<Item> event) {
    }

    @SubscribeEvent
    public static void registerVanillaRecipes(Register<IRecipe> event) {
    }
}
