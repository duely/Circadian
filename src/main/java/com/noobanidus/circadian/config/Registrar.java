package com.noobanidus.circadian.config;

import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.advancements.GenericTrigger;
import com.noobanidus.circadian.compat.astralsorcery.blocks.BlockStarmetal;
import com.noobanidus.circadian.compat.extrautilities2.blocks.BlockCompressedStoneEntry;
import com.noobanidus.circadian.compat.oreberries.blocks.BlockBerry;
import com.noobanidus.circadian.compat.thaumcraft.advancements.ResearchPredicate;
import com.noobanidus.circadian.compat.thaumcraft.blocks.BlockCompressedVisBattery;
import com.noobanidus.circadian.compat.vanilla.advancements.BiomePredicate;
import com.noobanidus.circadian.enchantment.EnchantmentManabound;
import com.noobanidus.circadian.icons.BlockMiniatures;
import com.noobanidus.circadian.icons.Icons;
import com.noobanidus.circadian.items.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber
public class Registrar {
    @SuppressWarnings("unused")
    public static GenericTrigger BIOME_TRIGGER = CriteriaTriggers.register(new GenericTrigger(new ResourceLocation(Circadian.MODID, "biome_type"), BiomePredicate.ANY));
    public static GenericTrigger THAUMCRAFT_RESEARCH_TRIGGER = CriteriaTriggers.register(new GenericTrigger(new ResourceLocation(Circadian.MODID, "thaumcraft_research"), ResearchPredicate.ANY));

    public static BlockCompressedVisBattery compressed;
    public static BlockStarmetal starmetal;
    public static BlockMiniatures miniatures;

    public static ItemBlock ib_compressed;
    public static ItemBlock ib_starmetal;
    public static ItemBlock ib_knightmetal;
    public static ItemBlock ib_liveroot;
    public static ItemBlock ib_naga;
    public static ItemBlock ib_miniatures;

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
    public static Sticks sticks;

    public static Satchels satchels;
    public static WateringCan wateringCan;

    public static Icons icons;

    public static Item goldenPotato;
    public static Item silveredPotato;
    public static Item silveredApple;
    public static Item silveredMelon;
    public static Item silveredCarrot;

    public static Enchantment manabound;

    @SuppressWarnings("unused")
    public static BlockCompressedStoneEntry[] compressedBlocks = new BlockCompressedStoneEntry[]{new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(0), "stone", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(1), "stone_granite", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(3), "stone_diorite", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(5), "stone_andesite", 2), new BlockCompressedStoneEntry(Blocks.SANDSTONE, "sandstone_normal", 2), new BlockCompressedStoneEntry(Blocks.RED_SANDSTONE, "red_sandstone_normal", 2)};

    @SuppressWarnings("ConstantConditions")
    public static void preInit() {
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
        sticks = new Sticks();
        clusters = new Clusters();
        nuggets = new Nuggets();
        miniatures = new BlockMiniatures();
        miniatures.setRegistryName("circadian:miniatures");

        wateringCan = new WateringCan();
        satchels = new Satchels();

        icons = new Icons();

        ib_miniatures = new ItemMultiTexture(miniatures, miniatures, new ItemMultiTexture.Mapper() {
            @Override
            @Nonnull
            public String apply(@Nonnull ItemStack stack) {
                for (BlockMiniatures.MiniatureVariant var : BlockMiniatures.MiniatureVariant.values()) {
                    if (var.meta == stack.getItemDamage()) {
                        return var.getName();
                    }
                }

                return "invalid";
            }
        });

        ib_miniatures.setRegistryName(miniatures.getRegistryName());

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

        if (Sticks.enabled) {
            sticks.init();
        }

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

        goldenPotato = (new ItemFood(2, 0.6f, false)).setAlwaysEdible().setUnlocalizedName("golden_potato").setRegistryName("circadian", "golden_potato").setCreativeTab(Circadian.TAB);
        silveredPotato = (new ItemFood(2, 0.6f, false)).setAlwaysEdible().setUnlocalizedName("silvered_potato").setRegistryName("circadian", "silvered_potato").setCreativeTab(Circadian.TAB);
        silveredApple = (new ItemAppleGold(4, 1.2f, false)).setAlwaysEdible().setUnlocalizedName("silvered_apple").setRegistryName("circadian", "silvered_apple").setCreativeTab(Circadian.TAB);
        silveredMelon = (new Item()).setUnlocalizedName("silvered_melon").setRegistryName("circadian", "silvered_melon").setCreativeTab(Circadian.TAB);
        silveredCarrot = (new ItemFood(6, 1.2f, false)).setAlwaysEdible().setUnlocalizedName("silvered_carrot").setRegistryName("circadian", "silvered_carrot").setCreativeTab(Circadian.TAB);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        if (BlockCompressedStoneEntry.enabled) {
            event.getRegistry().register(compressed);
        }

        if (BlockStarmetal.enabled) {
            event.getRegistry().register(starmetal);
        }

        if (BlockBerry.enabled) {
            event.getRegistry().register(knightmetal_berry);
            event.getRegistry().register(liveroot_berry);
            event.getRegistry().register(naga_berry);
        }

        event.getRegistry().register(miniatures);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        if (BlockCompressedStoneEntry.enabled) {
            event.getRegistry().register(ib_compressed);
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

        event.getRegistry().register(ib_miniatures);

        event.getRegistry().register(goldenPotato);
        event.getRegistry().register(silveredPotato);
        event.getRegistry().register(silveredApple);
        event.getRegistry().register(silveredMelon);
        event.getRegistry().register(silveredCarrot);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        if (BlockCompressedStoneEntry.enabled) {
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

        miniatures.registerModel();

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

        if (Sticks.enabled) {
            sticks.registerModels();
        }

        if (Clusters.enabled) {
            clusters.registerModels();
        }

        if (Nuggets.enabled) {
            nuggets.registerModels();
        }

        ModelLoader.setCustomModelResourceLocation(goldenPotato, 0, new ModelResourceLocation("circadian:food", "type=goldenpotato"));
        ModelLoader.setCustomModelResourceLocation(silveredPotato, 0, new ModelResourceLocation("circadian:food", "type=silveredpotato"));
        ModelLoader.setCustomModelResourceLocation(silveredApple, 0, new ModelResourceLocation("circadian:food", "type=silveredApple"));
        ModelLoader.setCustomModelResourceLocation(silveredApple, 1, new ModelResourceLocation("circadian:food", "type=silveredApple"));
        ModelLoader.setCustomModelResourceLocation(silveredMelon, 0, new ModelResourceLocation("circadian:food", "type=silveredmelon"));
        ModelLoader.setCustomModelResourceLocation(silveredCarrot, 0, new ModelResourceLocation("circadian:food", "type=silveredcarrot"));
    }

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        if (EnchantmentManabound.enabled) {
            event.getRegistry().register(manabound);
        }
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (Satchels.enabled) {
            satchels.registerRecipes();
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block e) {
        BlockColors blocks = e.getBlockColors();

        final IBlockColor grassHandler = (state, worldIn, pos, tintIndex) -> {
            if (tintIndex == 1 && worldIn != null && pos != null && state.getBlock() instanceof BlockMiniatures) {
                if (Registrar.miniatures.getMetaFromState(state) == 0) {
                    return 5635969;
                } else if (Registrar.miniatures.getMetaFromState(state) == 1) {
                    return ColorizerGrass.getGrassColor(0.25, 0.8);
                }
            }

            return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);
        };

        final IBlockColor leafHandler = (state, worldIn, pos, tintIndex) -> {
            if (tintIndex == 2 && worldIn != null && pos != null && state.getBlock() instanceof BlockMiniatures) {
                if (Registrar.miniatures.getMetaFromState(state) == 0) {
                    return 6750149;
                } else if (Registrar.miniatures.getMetaFromState(state) == 1) {
                    return ColorizerFoliage.getFoliageColor(0.25, 0.8);
                }
            }

            return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
        };

        blocks.registerBlockColorHandler(grassHandler, Registrar.miniatures);
        blocks.registerBlockColorHandler(leafHandler, Registrar.miniatures);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item e) {
        ItemColors items = e.getItemColors();

        final IItemColor grassHandler = (stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemBlock && ((ItemBlock) stack.getItem()).getBlock() instanceof BlockMiniatures) {
                if (stack.getItemDamage() == 0) {
                    return 5635969;
                } else if (stack.getItemDamage() == 1) {
                    return ColorizerGrass.getGrassColor(0.25, 0.8);
                }
            }

            return ColorizerGrass.getGrassColor(0.5, 1.0);
        };

        final IItemColor leafHandler = (stack, tintIndex) -> {
            if (tintIndex == 2 && stack.getItem() instanceof ItemBlock && ((ItemBlock) stack.getItem()).getBlock() instanceof BlockMiniatures) {
                if (stack.getItemDamage() == 0) {
                    return 6750149;
                } else if (stack.getItemDamage() == 1) {
                    return ColorizerFoliage.getFoliageColor(0.25, 0.8);
                }
            }

            return ColorizerFoliage.getFoliageColor(0.5, 1.0);
        };

        items.registerItemColorHandler(grassHandler, Registrar.miniatures);
        items.registerItemColorHandler(leafHandler, Registrar.miniatures);
    }

    @SideOnly(Side.CLIENT)
    protected static class StateMapper extends StateMapperBase {
        public static final StateMapper KNIGHTMETAL = new StateMapper("circadian", "block_knightmetal_bush");
        public static final StateMapper NAGA = new StateMapper("circadian", "block_nagascale_bush");
        public static final StateMapper LIVEROOT = new StateMapper("circadian", "block_liveroot_bush");

        protected ResourceLocation texture;

        protected StateMapper(String path, String domain) {
            this.texture = new ResourceLocation(path, domain);
        }

        @Override
        @Nonnull
        public ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
            return new ModelResourceLocation(texture, this.getPropertyString(state.getProperties()));
        }
    }
}
