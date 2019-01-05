package com.noobanidus.circadian.compat.oreberries.blocks;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.infinityraider.infinitylib.utility.WorldHelper;
import com.noobanidus.circadian.Circadian;
import josephcsible.oreberries.BlockOreberryBush;
import josephcsible.oreberries.config.GeneralConfig;
import josephcsible.oreberries.config.OreberryConfig;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import twilightforest.item.TFItems;

import javax.annotation.Nonnull;
import java.util.Random;

// TODO: Sort out licensing issues

@SuppressWarnings("WeakerAccess")
public class BlockBerry extends Block implements IPlantable, IGrowable {

    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3); // small, medium, large, large with berries
    protected static final AxisAlignedBB[] OREBERRY_BUSH_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D),
            new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.75D, 0.875D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)
    };
    protected static final AxisAlignedBB[] OREBERRY_BUSH_COLLISION_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D),
            new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.75D, 0.875D),
            new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D)
    };
    public static boolean enabled = Circadian.CONFIG.get("Items.TwilightBushes", "Enable", true, "Enable Twilight Forest bushes.").getBoolean(true);
    public final OreberryConfig config;
    public String name;

    public BlockBerry(String name, OreberryConfig config) {
        super(Material.LEAVES);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
        this.setTickRandomly(true);
        this.setHardness(0.3F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(Circadian.TAB);
        this.setUnlocalizedName(Circadian.MODID + "." + name + ".oreberry_bush");
        this.setRegistryName(Circadian.MODID, name + "_oreberry_bush");
        this.config = config;
        this.name = name;
    }

    public static OreberryConfig getDefaults(String name) {
        switch (name) {
            case "naga":
                JsonObject naga = new JsonObject();
                naga.addProperty("bushName", "Naga Stoneberry Bush");
                naga.addProperty("berryName", "Nagaleaf"); // this doesn't do anything
                naga.add("smeltingResult", JsonNull.INSTANCE);
                naga.addProperty("tooltip", "");
                naga.addProperty("special", "nagaleaf");
                naga.addProperty("tradeable", false);
                naga.addProperty("maxHeight", 28);
                naga.addProperty("sizeChance", 7);
                return new OreberryConfig("Naga", naga);
            case "knight":
                JsonObject knight = new JsonObject();
                knight.addProperty("bushName", "Armor Shardberry Bush");
                knight.addProperty("berryName", ""); // this doesn't do anything
                knight.add("smeltingResult", JsonNull.INSTANCE);
                knight.addProperty("tooltip", "");
                knight.addProperty("special", "armorshard");
                knight.addProperty("tradeable", false);
                knight.addProperty("maxHeight", 24);
                knight.addProperty("sizeChance", 7);
                return new OreberryConfig("Knight", knight);
            case "liveroot":
                JsonObject liveroot = new JsonObject();
                liveroot.addProperty("bushName", "Liveroot Cluster");
                liveroot.addProperty("berryName", ""); // this doesn't do anything
                liveroot.add("smeltingResult", JsonNull.INSTANCE);
                liveroot.addProperty("tooltip", "");
                liveroot.addProperty("special", "Liveroots");
                liveroot.addProperty("growsInLight", true);
                liveroot.addProperty("tradeable", false);
                liveroot.addProperty("maxHeight", 50);
                liveroot.addProperty("sizeChance", 7);
                return new OreberryConfig("Liveroot", liveroot);
            default:
                return null;
        }
    }

    @Override
    @Nonnull
    public String getLocalizedName() {
        return config.bushName;
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AGE);
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(AGE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(AGE);
    }

    /* The following methods define a berry bush's size depending on metadata */
    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return OREBERRY_BUSH_AABB[Math.min(state.getValue(AGE), 2)];
    }

    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos pos) {
        return OREBERRY_BUSH_COLLISION_AABB[Math.min(state.getValue(AGE), 2)];
    }

    /* Left-click harvests berries */
    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        harvest(worldIn, pos, worldIn.getBlockState(pos), playerIn);
    }

    /* Right-click harvests berries */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return harvest(worldIn, pos, state, playerIn);
    }

    public ItemStack getBerriesStack(Random rand) {
        Item item;

        switch (name) {
            case "naga":
                item = TFItems.naga_scale;
                break;
            case "knight":
                item = TFItems.armor_shard;
                break;
            case "liveroot":
                item = TFItems.liveroot;
                break;
            default:
                return null;
        }
        return new ItemStack(item, rand.nextInt(config.maxDrops - config.minDrops + 1) + config.minDrops);
    }

    /* Render logic */

    protected boolean harvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (state.getValue(AGE) >= 3) {
            if (world.isRemote)
                return true;

            world.setBlockState(pos, state.withProperty(AGE, 2));
            if (player instanceof FakePlayer) {
                WorldHelper.spawnItemInWorld(world, pos, getBerriesStack(world.rand));
            } else {
                ItemHandlerHelper.giveItemToPlayer(player, getBerriesStack(world.rand));
            }

        }

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return Minecraft.getMinecraft().gameSettings.fancyGraphics ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        // TODO resolve the fact that this is called by the server, but the answer depends on whether fancy graphics are on
        // For now, the answer is always just no on the server
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockFaceShape getBlockFaceShape(IBlockAccess blockAccess, IBlockState state, BlockPos pos, EnumFacing facing) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return state.getValue(AGE) >= 2;
    }

    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(IBlockState state, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos pos, EnumFacing side) {
        if (side != EnumFacing.DOWN && state.getValue(AGE) < 2) {
            // This face is completely inside of our block, so it definitely has to be rendered
            return true;
        }
        if (!Minecraft.getMinecraft().gameSettings.fancyGraphics) {
            IBlockState touchingState = blockAccess.getBlockState(pos.offset(side));
            if (touchingState.getBlock() == this && touchingState.getValue(AGE) >= 2) {
                // Like vanilla leaves, don't render if we're on fast graphics and touching another one of ourself
                // Note that it can only possibly be touching if our neighbor is full-size
                return false;
            }
        }
        @SuppressWarnings("deprecation")
        boolean thisShouldNotBeDeprecated = super.shouldSideBeRendered(state, blockAccess, pos, side);
        return thisShouldNotBeDeprecated;
    }

    /* Bush growth */
    protected boolean ageAndLightOkayToGrow(World worldIn, BlockPos pos, IBlockState state) {
        return state.getValue(AGE) < 3 && (config.growsInLight || worldIn.getLight(pos) < 10);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote && ageAndLightOkayToGrow(worldIn, pos, state) && ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextDouble() < GeneralConfig.tickGrowthChance)) {
            grow(worldIn, rand, pos, state);
            ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
        }
    }

    @Override
    public boolean canSustainPlant(@Nonnull IBlockState state, @Nonnull IBlockAccess world, BlockPos pos, @Nonnull EnumFacing direction, IPlantable plantable) {
        if (plantable instanceof BlockOreberryBush)
            return state.getValue(AGE) > 1;
        return super.canSustainPlant(state, world, pos, direction, plantable);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, @Nonnull BlockPos pos) {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return super.canPlaceBlockAt(worldIn, pos) &&
                (config.growsInLight || worldIn.getLight(pos) < 13) &&
                soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this) &&
                worldIn.isAirBlock(pos);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Cave;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.getDefaultState();
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!(entityIn instanceof EntityItem))
            entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
    }

    @Override
    @SuppressWarnings("deprecation")
    public PathNodeType getAiPathNodeType(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return PathNodeType.DAMAGE_CACTUS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean causesSuffocation(IBlockState state) {
        return false;
    }

    @Override
    public boolean canGrow(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, boolean isClient) {
        return GeneralConfig.bonemealGrowthChance > 0.0D && ageAndLightOkayToGrow(worldIn, pos, state);
    }

    @Override
    public boolean canUseBonemeal(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        return rand.nextDouble() < GeneralConfig.bonemealGrowthChance;
    }

    @Override
    public void grow(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        worldIn.setBlockState(pos, state.withProperty(AGE, state.getValue(AGE) + 1));
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, @Nonnull IBlockState state, EntityPlayer player) {
        return player != null && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand()) >= GeneralConfig.silkTouchRequirement;
    }

    @Override
    public int quantityDropped(Random random) {
        return GeneralConfig.silkTouchRequirement == 0 ? super.quantityDropped(random) : 0;
    }
}
