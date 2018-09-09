package circadian;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.ModelRegistryEvent;

//import cofh.thermalfoundation.init.TFEquipment;

import circadian.config.Blocks;
//import circadian.items.Tools;

@Mod.EventBusSubscriber
public class Registrar {
    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        Blocks.registerItems(event.getRegistry());
        //Tools.INSTANCE.preInit();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        Blocks.registerModels();
        //Tools.INSTANCE.registerModels();
    }

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        Blocks.initBlocks();
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
