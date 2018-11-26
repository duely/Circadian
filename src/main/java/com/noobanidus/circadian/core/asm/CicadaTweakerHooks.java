package com.noobanidus.circadian.core.asm;

import com.infinityraider.agricraft.blocks.BlockCrop;
import com.infinityraider.agricraft.tiles.TileEntityCrop;
import com.infinityraider.infinitylib.utility.WorldHelper;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.config.Registrar;
import josephcsible.oreberries.BlockOreberryBush;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockStem;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.items.ItemHandlerHelper;
import thaumcraft.common.lib.utils.CropUtils;

@SuppressWarnings("unused")
public class CicadaTweakerHooks {
	public static final ResourceLocation PENGUIN_LOOT_TABLE = new ResourceLocation("cicadatweaks", "entities/twilightforest/penguin");

	public static boolean isGrownCrop(World world, BlockPos pos) {
		if (world.isAirBlock(pos)) {
			return false;
		}
		boolean found = false;
		IBlockState bs = world.getBlockState(pos);
		Block bi = bs.getBlock();
		int md = bi.getMetaFromState(bs);

		if (bi instanceof BlockCrop) {
			md = ((BlockCrop) bi).getCropTile(world, pos).map(TileEntityCrop::getGrowthStage).orElse(0);
		}
		if (CropUtils.standardCrops.contains(bi.getUnlocalizedName() + md) || CropUtils.clickableCrops.contains(bi.getUnlocalizedName() + md) || CropUtils.stackedCrops.contains(bi.getUnlocalizedName() + md)) {
			found = true;
		}

		Block biB = world.getBlockState(pos.down()).getBlock();
		return (bi instanceof IGrowable && !((IGrowable) bi).canGrow(world, pos, world.getBlockState(pos), world.isRemote) && !(bi instanceof BlockStem)) || (bi instanceof BlockCrops && md == 7 && !found) || CropUtils.standardCrops.contains(bi.getUnlocalizedName() + md) || CropUtils.clickableCrops.contains(bi.getUnlocalizedName() + md) || (CropUtils.stackedCrops.contains(bi.getUnlocalizedName() + md) && biB == bi);
	}

	public static boolean harvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (state.getValue(BlockOreberryBush.AGE) >= 3) {
			if (world.isRemote)
				return true;

			world.setBlockState(pos, state.withProperty(BlockOreberryBush.AGE, 2));
			if (player instanceof FakePlayer) {
				WorldHelper.spawnItemInWorld(world, pos, ((BlockOreberryBush) state.getBlock()).getBerriesStack(world.rand));
			} else {
				ItemHandlerHelper.giveItemToPlayer(player, ((BlockOreberryBush) state.getBlock()).getBerriesStack(world.rand));
			}
		}

		return false;
	}

	public static boolean handleEating(AbstractHorse horse, EntityPlayer player, ItemStack stack) {
		boolean flag = false;
		float f = 0.0F;
		int i = 0;
		int j = 0;
		Item item = stack.getItem();

		if (item == Items.WHEAT) {
			f = 2.0F;
			i = 20;
			j = 3;
		} else if (item == Items.SUGAR) {
			f = 1.0F;
			i = 30;
			j = 3;
		} else if (item == Item.getItemFromBlock(Blocks.HAY_BLOCK)) {
			f = 20.0F;
			i = 180;
		} else if (item == Items.APPLE) {
			f = 3.0F;
			i = 60;
			j = 3;
		} else if (item == Items.GOLDEN_CARROT || item == Registrar.silveredCarrot || item == Registrar.silveredPotato) {
			f = 4.0F;
			i = 60;
			j = 5;

			if (horse.isTame() && horse.getGrowingAge() == 0 && !horse.isInLove()) {
				flag = true;
				horse.setInLove(player);
			}
		} else if (item == Items.GOLDEN_APPLE || item == Registrar.silveredApple || item == Registrar.goldenPotato) {
			f = 10.0F;
			i = 240;
			j = 10;

			if (horse.isTame() && horse.getGrowingAge() == 0 && !horse.isInLove()) {
				flag = true;
				horse.setInLove(player);
			}
		}

		if (horse.getHealth() < horse.getMaxHealth() && f > 0.0F) {
			horse.heal(f);
			flag = true;
		}

		if (horse.isChild() && i > 0) {
			horse.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, horse.posX + (double) (horse.rand.nextFloat() * horse.width * 2.0F) - (double) horse.width, horse.posY + 0.5D + (double) (horse.rand.nextFloat() * horse.height), horse.posZ + (double) (horse.rand.nextFloat() * horse.width * 2.0F) - (double) horse.width, 0.0D, 0.0D, 0.0D);

			if (!horse.world.isRemote) {
				horse.addGrowth(i);
			}

			flag = true;
		}

		if (j > 0 && (flag || !horse.isTame()) && horse.getTemper() < horse.getMaxTemper()) {
			flag = true;

			if (!horse.world.isRemote) {
				horse.increaseTemper(j);
			}
		}

		if (flag) {
			horse.eatingHorse();
		}

		return flag;
	}

	public static EntityCreature entity;

	public static ResourceLocation getLootTable() {
		return PENGUIN_LOOT_TABLE;
	}

	public static int modifyActivationCost (String ritualName, int activationCost) {
		return Circadian.CONFIG.get("BloodMagic.Rituals", ritualName, activationCost, "Modify activation cost for ritual").getInt(activationCost);
	}

	public static void placePlatform (World world, BlockPos p) {
	    int y = Circadian.CONFIG.get("BloodMagic.Rituals", "MeteorPlatformHeight", 24, "Height above the ritual stone to place platform.").getInt(24);

	    BlockPos pos = new BlockPos(p.getX(), p.getY() + y, p.getZ());

        if (world.isAirBlock(pos)) {
            IBlockState stone = Blocks.STONE.getDefaultState();
            world.setBlockState(pos, stone);
        }
    }
}
