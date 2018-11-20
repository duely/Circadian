package com.noobanidus.circadian.compat.top;

import com.noobanidus.circadian.compat.oreberries.blocks.BlockBerry;
import com.noobanidus.circadian.compat.thaumcraft.blocks.BlockCompressedVisBattery;
import com.rwtema.extrautils2.blocks.BlockEnderLilly;
import josephcsible.oreberries.BlockOreberryBush;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import thaumcraft.common.blocks.devices.BlockVisBattery;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.common.block.BlockIncensePlate;
import vazkii.botania.common.block.tile.TileIncensePlate;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.brew.ItemIncenseStick;

import javax.annotation.Nullable;

import static mcjty.theoneprobe.api.TextStyleClass.OK;
import static mcjty.theoneprobe.api.TextStyleClass.WARNING;

public class TOPProvider {
    private static boolean registered;

    public static void init() {
        if (registered) {
            return;
        }

        registered = true;

        FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "com.noobanidus.circadian.compat.top.TOPProvider$GetTheOneProbe");
    }

    @SuppressWarnings("unused")
    public static class GetTheOneProbe implements com.google.common.base.Function<ITheOneProbe, Void> {
        public static ITheOneProbe probe;

        @Nullable
        @Override
        public Void apply(ITheOneProbe theOneProbe) {
            probe = theOneProbe;
            probe.registerProvider(new IProbeInfoProvider() {
                @Override
                public String getID() {
                    return "circadian:topprovider";
                }

                @Override
                public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
                    if (blockState.getBlock() instanceof BlockEnderLilly) {
                        int growthState = blockState.getValue(BlockEnderLilly.GROWTH_STATE);
                        probeInfo.text((growthState == 7) ? OK + "Fully grown" : "Growth: " + WARNING + String.format("%s%%", growthState * 14));
                    } else if (blockState.getBlock() instanceof BlockCompressedVisBattery && mode == ProbeMode.EXTENDED) {
                        int power = blockState.getValue(BlockCompressedVisBattery.CHARGE);
                        probeInfo.text(String.format(OK + "Vis: %s" + ((power == 10) ? " (max)" : ""), power * 9));
                    } else if (blockState.getBlock() instanceof BlockVisBattery && mode == ProbeMode.EXTENDED) {
                        int power = blockState.getValue(BlockVisBattery.CHARGE);
                        probeInfo.text(String.format(OK + "Vis: %s" + ((power == 10) ? " (max)" : ""), power));
                    } else if (blockState.getBlock() instanceof BlockOreberryBush) {
                        int growthState = blockState.getValue(BlockOreberryBush.AGE);
                        probeInfo.text((growthState == 3) ? OK + "Ready to Harvest" : "");
                    } else if (blockState.getBlock() instanceof BlockBerry) {
                        int growthState = blockState.getValue(BlockBerry.AGE);
                        probeInfo.text((growthState == 3) ? OK + "Ready to Harvest" : "");
                    } else if (blockState.getBlock() instanceof BlockIncensePlate) {
                        TileIncensePlate plate = (TileIncensePlate) world.getTileEntity(data.getPos());

                        if (plate == null) {
                            probeInfo.text(WARNING + "Invalid tile entity.");
                            return;
                        }

                        int timeLeft = plate.timeLeft / 60 / 20;
                        ItemStack stack = plate.getItemHandler().getStackInSlot(0);
                        Brew brew = ((ItemIncenseStick) ModItems.incenseStick).getBrew(stack);
                        if (stack.isEmpty()) {
                            probeInfo.text(WARNING + "No brew.");
                        } else {
                            String brew_name = I18n.translateToLocal(brew.getUnlocalizedName());
                            probeInfo.text(OK + String.format("Brew: %s", brew_name));
                            if (timeLeft == 0) {
                                probeInfo.text(WARNING + "Unlit.");
                            } else {
                                probeInfo.text(OK + String.format("%d minutes remaining.", timeLeft));
                            }
                        }
                    }

                }
            });
            return null;
        }
    }
}
