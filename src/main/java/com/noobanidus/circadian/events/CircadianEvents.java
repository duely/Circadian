package com.noobanidus.circadian.events;

import cofh.core.util.helpers.BaublesHelper;
import com.google.common.collect.Iterables;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.config.Registrar;
import com.noobanidus.circadian.enchantment.EnchantmentManabound;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CircadianEvents {

    private static int manaCost = Circadian.CONFIG.get("Enchantments.Manabound", "Cost", 370, "Cost to repair a point of damage.").getInt(370);

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        if (EnchantmentManabound.enabled && event.getEntity() instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) event.getEntity();

            Iterable<ItemStack> equipment = Iterables.concat(Arrays.asList(player.inventory.mainInventory, player.inventory.armorInventory, player.inventory.offHandInventory, BaublesHelper.getBaubles(player)));

            for (ItemStack stack : equipment) {
                if (!(stack.getItem() instanceof IManaUsingItem) && stack.isItemDamaged() && stack.isItemEnchanted() && (EnchantmentHelper.getEnchantments(stack).get(Registrar.manabound) != null)) {
                    if (ManaItemHandler.requestManaExactForTool(stack, player, manaCost, true)) {
                        stack.setItemDamage(stack.getItemDamage() - 1);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTrampleEvent(BlockEvent.FarmlandTrampleEvent event) {
        if (Circadian.CONFIG.get("Farmland", "Trample", true, "Disable trampling of farmland.").getBoolean(true)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        World world = player.world;

        if (!world.isRemote && player.ticksExisted % 20 == 0) {
            Registrar.BIOME_TRIGGER.trigger((EntityPlayerMP) player);
        }
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onDaisyRightClick (PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();

        if (world.isRemote || event.getHand() != EnumHand.MAIN_HAND) {
            event.setCanceled(true);
            event.setCancellationResult(EnumActionResult.PASS);
            return;
        }

        EntityPlayer player = event.getEntityPlayer();
        ItemStack item = player.getHeldItem(EnumHand.MAIN_HAND);
        BlockPos pos = event.getPos();
        IBlockState state = world.getBlockState(pos);
        EnumHand curHand = EnumHand.MAIN_HAND;
        EnumFacing playerFacing = player.getHorizontalFacing().getOpposite();

        if (item.isEmpty() || !(item.getItem() instanceof ItemBlock)) {
            item = player.getHeldItem(EnumHand.OFF_HAND);
            curHand = EnumHand.OFF_HAND;
        }

        if (item.isEmpty()) return;

        if (!(item.getItem() instanceof ItemBlock)) return;

        ItemBlock iblock = (ItemBlock) item.getItem();

        if (world.getTileEntity(pos) instanceof TileSpecialFlower) {
            TileSpecialFlower te = (TileSpecialFlower) world.getTileEntity(pos);
            if (te != null && te.subTileName.equals("puredaisy")) {
                float hitX = pos.getX();
                float hitY = pos.getY();
                float hitZ = pos.getZ();

                Block block = ((ItemBlock) item.getItem()).getBlock();

                BlockPos start = pos.add(1, 0, 1);
                BlockPos stop = pos.add(-1, 0, -1);
                int count = 0;

                for (BlockPos potential : BlockPos.getAllInBox(stop, start)) {
                    if (potential.equals(pos)) continue;
                    if (player.getDistance((double) pos.getX(), (double)pos.getY(), (double)pos.getZ()) < 1.3 && player.getPosition().getY() == pos.getY()) continue;

                    IBlockState stateAt = world.getBlockState(potential);
                    Block blockAt = stateAt.getBlock();

                    if ((blockAt.isReplaceable(world, potential) || blockAt.isAir(stateAt, world, potential)) && block.canPlaceBlockAt(world, potential)) {
                        IBlockState placingState = block.getStateForPlacement(world, pos, playerFacing, hitX, hitY, hitZ, item.getMetadata(), player, curHand);
                        iblock.placeBlockAt(item, player, world, potential, playerFacing, hitX, hitY, hitZ, placingState);
                        if (!player.capabilities.isCreativeMode) item.shrink(1);
                        break;
                    } else {
                        count++;
                    }
                }

                if (count < 8) {
                    event.setCanceled(true);
                    event.setCancellationResult(EnumActionResult.SUCCESS);
                }
            }
        }
    }
}
