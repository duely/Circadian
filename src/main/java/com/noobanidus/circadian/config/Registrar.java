package com.noobanidus.circadian.config;

import cofh.core.util.helpers.ItemHelper;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.compat.chisel.blocks.BlockRedScribbles;
import com.noobanidus.circadian.compat.oreberries.blocks.BlockBerry;
import com.noobanidus.circadian.compat.extrautilities2.blocks.BlockCompressedStoneEntry;
import com.noobanidus.circadian.compat.astralsorcery.blocks.BlockStarmetal;
import com.noobanidus.circadian.enchantment.EnchantmentManabound;
import com.noobanidus.circadian.icons.Icons;
import com.noobanidus.circadian.items.*;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import com.noobanidus.circadian.compat.thaumcraft.blocks.BlockCompressedVisBattery;

import net.minecraft.init.Blocks;

@Mod.EventBusSubscriber
public class Registrar {
    public static BlockCompressedVisBattery compressed;
    public static BlockStarmetal starmetal;

    public static ItemBlock ib_compressed;
    public static ItemBlock ib_starmetal;
    public static ItemBlock ib_knightmetal;
    public static ItemBlock ib_liveroot;
    public static ItemBlock ib_naga;

    public static BlockBerry knightmetal_berry;
    public static BlockBerry liveroot_berry;
    public static BlockBerry naga_berry;

    public static Item fertilizer;
    public static Item fertilizerBag;
    public static Plate plates;
    public static MobIngredients mobIngredients;
    public static Clusters clusters;
    public static Nuggets nuggets;
    public static Tools tools;

    public static Satchels satchels;
    public static WateringCan wateringCan;

    public static Icons icons;

    public static BlockRedScribbles scribbles;
    public static ItemBlock ib_scribbles;

    public static Item goldenPotato;
    public static Item silveredPotato;
    public static Item silveredApple;
    public static Item silveredMelon;
    public static Item silveredCarrot;

 	public static Enchantment manabound;

    public static BlockCompressedStoneEntry[] compressedBlocks = new BlockCompressedStoneEntry[] { new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(0), "stone", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(1), "stone_granite", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(3), "stone_diorite", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(5), "stone_andesite", 2), new BlockCompressedStoneEntry(Blocks.SANDSTONE, "sandstone_normal", 2), new BlockCompressedStoneEntry(Blocks.RED_SANDSTONE, "red_sandstone_normal", 2)};

    public static void preInit () {
        compressed = new BlockCompressedVisBattery();
        starmetal = new BlockStarmetal();
        knightmetal_berry = new BlockBerry("knight", BlockBerry.getDefaults("knight"));
        liveroot_berry = new BlockBerry("liveroot", BlockBerry.getDefaults("liveroot"));
        naga_berry = new BlockBerry("naga", BlockBerry.getDefaults("naga"));
        manabound = new EnchantmentManabound("circadian:manabound");

        fertilizer = new Fertilizer();
        fertilizerBag = new FertilizerBag();
        mobIngredients = new MobIngredients();
        plates = new Plate();
        tools = new Tools();
        clusters = new Clusters();
        nuggets = new Nuggets();
        scribbles = new BlockRedScribbles();

        wateringCan = new WateringCan();
        satchels = new Satchels();

        icons = new Icons();

        ib_scribbles = new ItemMultiTexture(scribbles, scribbles, new ItemMultiTexture.Mapper() {
            @Override
            public String apply(ItemStack var1) {
                return scribbles.getUnlocalizedName();
            }
        });
        ib_scribbles.setRegistryName(scribbles.getRegistryName());

        ib_compressed = new ItemBlock(compressed);
        ib_compressed.setRegistryName(compressed.getRegistryName());

        ib_starmetal = new ItemBlock(starmetal);
        ib_starmetal.setRegistryName(starmetal.getRegistryName());

        ib_knightmetal = new ItemBlock(knightmetal_berry);
        ib_knightmetal.setRegistryName(knightmetal_berry.getRegistryName());

        ib_liveroot = new ItemBlock(liveroot_berry);
        ib_liveroot.setRegistryName(liveroot_berry.getRegistryName());

        ib_naga = new ItemBlock(naga_berry);
        ib_naga.setRegistryName(naga_berry.getRegistryName());

        if (Plate.enabled) {
            plates.init();
        }

        tools.init();

        if (Clusters.enabled) {
            clusters.init();
        }

        if (MobIngredients.enabled) {
            mobIngredients.init();
        }

        if (Nuggets.enabled) {
            nuggets.init();
        }

        if (Icons.enabled) {
            icons.init();
        }

        if (WateringCan.enabled) {
            wateringCan.init();
        }

        if (Satchels.enabled) {
            satchels.init();
        }

        goldenPotato = (new ItemFood(2, 0.6f, false)).setUnlocalizedName("golden_potato").setRegistryName("circadian", "golden_potato").setCreativeTab(Circadian.TAB);
        silveredPotato = (new ItemFood(2, 0.6f, false)).setUnlocalizedName("silvered_potato").setRegistryName("circadian", "silvered_potato").setCreativeTab(Circadian.TAB);
        silveredApple = (new ItemFood(4, 1.2f, false)).setUnlocalizedName("silvered_apple").setRegistryName("circadian", "silvered_apple").setCreativeTab(Circadian.TAB);
        silveredMelon = (new Item()).setUnlocalizedName("silvered_melon").setRegistryName("circadian", "silvered_melon").setCreativeTab(Circadian.TAB);
        silveredCarrot = (new ItemFood(6, 1.2f, false)).setUnlocalizedName("silvered_carrot").setRegistryName("circadian", "silvered_carrot").setCreativeTab(Circadian.TAB);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        if (compressed.enabled) {
            event.getRegistry().register(compressed);
        }

        if (scribbles.enabled) {
            event.getRegistry().register(scribbles);
        }

        if (BlockStarmetal.enabled) {
            event.getRegistry().register(starmetal);
        }

        if (BlockBerry.enabled) {
            event.getRegistry().register(knightmetal_berry);
            event.getRegistry().register(liveroot_berry);
            event.getRegistry().register(naga_berry);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        if (compressed.enabled) {
            event.getRegistry().register(ib_compressed);
        }

        if (scribbles.enabled) {
            event.getRegistry().register(ib_scribbles);
        }

        if (BlockStarmetal.enabled) {
            event.getRegistry().register(ib_starmetal);
        }
        if (BlockBerry.enabled) {
            event.getRegistry().register(ib_knightmetal);
            event.getRegistry().register(ib_liveroot);
            event.getRegistry().register(ib_naga);
        }

        if (((Fertilizer) fertilizer).enabled) {
            event.getRegistry().register(fertilizer);
            event.getRegistry().register(fertilizerBag);
        }

        event.getRegistry().register(goldenPotato);
        event.getRegistry().register(silveredPotato);
        event.getRegistry().register(silveredApple);
        event.getRegistry().register(silveredMelon);
        event.getRegistry().register(silveredCarrot);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels (ModelRegistryEvent event) {
        if (compressed.enabled) {
            ModelLoader.setCustomModelResourceLocation(ib_compressed, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_vis_battery"), "inventory"));
        }

        if (BlockStarmetal.enabled) {
            starmetal.registerModels();
        }

        if (Icons.enabled) {
            icons.registerModels();
        }

        if (WateringCan.enabled) {
            wateringCan.registerModels();
        }

        if (Satchels.enabled) {
            satchels.registerModels();
        }


        if (scribbles.enabled) {
            for (int i = 0; i < 16; i++) {
                ModelLoader.setCustomModelResourceLocation(ib_scribbles, i, new ModelResourceLocation(new ResourceLocation("circadian", String.format("redscribbles", i)), String.format("variant=%d", i)));
            }
        }

        if (BlockBerry.enabled) {
            // knightmetal
            ModelLoader.setCustomStateMapper(knightmetal_berry, StateMapper.KNIGHTMETAL);
            ModelLoader.setCustomModelResourceLocation(ib_knightmetal, 0, new ModelResourceLocation(new ResourceLocation("circadian", "block_knightmetal_bush"), "inventory"));

            // liveroot
            ModelLoader.setCustomStateMapper(naga_berry, StateMapper.NAGA);
            ModelLoader.setCustomModelResourceLocation(ib_naga, 0, new ModelResourceLocation(new ResourceLocation("circadian", "block_nagascale_bush"), "inventory"));

            // naga
            ModelLoader.setCustomStateMapper(liveroot_berry, StateMapper.LIVEROOT);
            ModelLoader.setCustomModelResourceLocation(ib_liveroot, 0, new ModelResourceLocation(new ResourceLocation("circadian", "block_liveroot_bush"), "inventory"));
        }

        if (((Fertilizer) fertilizer).enabled) {
            ModelLoader.setCustomModelResourceLocation(fertilizer, 0, new ModelResourceLocation(new ResourceLocation("circadian", "fertilizer"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(fertilizerBag, 0, new ModelResourceLocation(new ResourceLocation("circadian", "fertilizer_bag"), "inventory"));
        }

        if (Plate.enabled) {
            plates.registerModels();
        }

        if (MobIngredients.enabled) {
            mobIngredients.registerModels();
        }

        tools.registerModels();

        if (Clusters.enabled) {
            clusters.registerModels();
        }

        if (Nuggets.enabled) {
            nuggets.registerModels();
        }

        ModelLoader.setCustomModelResourceLocation(goldenPotato, 0, new ModelResourceLocation("circadian:food", "type=goldenpotato"));
        ModelLoader.setCustomModelResourceLocation(silveredPotato, 0, new ModelResourceLocation("circadian:food", "type=silveredpotato"));
        ModelLoader.setCustomModelResourceLocation(silveredApple, 0, new ModelResourceLocation("circadian:food", "type=silveredApple"));
        ModelLoader.setCustomModelResourceLocation(silveredMelon, 0, new ModelResourceLocation("circadian:food", "type=silveredmelon"));
        ModelLoader.setCustomModelResourceLocation(silveredCarrot, 0, new ModelResourceLocation("circadian:food", "type=silveredcarrot"));

    }

    @SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        if (EnchantmentManabound.enabled) {
    		event.getRegistry().register(manabound);
            Circadian.LOG.info(String.format("[Circadian] Manabound enchantment registered."));
        }
	}

	@SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (Satchels.enabled) {
            satchels.registerRecipes();
        }
    }

    @SideOnly(Side.CLIENT)
    protected static class StateMapper extends StateMapperBase {
        public static final StateMapper KNIGHTMETAL = new StateMapper("circadian", "block_knightmetal_bush");
        public static final StateMapper NAGA = new StateMapper("circadian", "block_nagascale_bush");
        public static final StateMapper LIVEROOT = new StateMapper("circadian", "block_liveroot_bush");

        protected ResourceLocation texture;

        protected StateMapper (String path, String domain) {
            this.texture = new ResourceLocation(path, domain);
        }

        @Override
        public ModelResourceLocation getModelResourceLocation(IBlockState state) {
            return new ModelResourceLocation(texture, this.getPropertyString(state.getProperties()));
        }
    }
}
