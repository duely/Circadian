package com.noobanidus.circadian.top;

import com.noobanidus.circadian.blocks.CompressedBlockVisBattery;
import com.rwtema.extrautils2.blocks.BlockEnderLilly;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import thaumcraft.common.blocks.devices.BlockVisBattery;

import javax.annotation.Nullable;

import static mcjty.theoneprobe.api.TextStyleClass.*;

public class TOPLilyProvider {
    private static boolean registered;

    public static void init () {
        if (registered) {
            return;
        }

        registered = true;

        FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "com.noobanidus.circadian.top.TOPLilyProvider$GetTheOneProbe");
    }

    public static class GetTheOneProbe implements com.google.common.base.Function<ITheOneProbe, Void> {
        public static ITheOneProbe probe;

        @Nullable
        @Override
        public Void apply (ITheOneProbe theOneProbe) {
            probe = theOneProbe;
            probe.registerProvider(new IProbeInfoProvider() {
                @Override
                public String getID() {
                    return "circadian:toplilyprovider";
                }

                @Override
                public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
                    if (blockState.getBlock() instanceof BlockEnderLilly) {
                        int growthState = blockState.getValue(BlockEnderLilly.GROWTH_STATE);
                        probeInfo.text((growthState == 7) ? OK + "Fully grown" : "Growth: " + WARNING + String.format("%s%%", growthState * 14));
                    } else if (blockState.getBlock() instanceof CompressedBlockVisBattery && mode == ProbeMode.EXTENDED) {
                        int power = blockState.getValue(CompressedBlockVisBattery.CHARGE);
                        probeInfo.text(String.format(OK + "Vis: %s" + ((power==10) ? " (max)" : ""), power * 9));
                    } else if (blockState.getBlock() instanceof BlockVisBattery && mode == ProbeMode.EXTENDED) {
                        int power = blockState.getValue(BlockVisBattery.CHARGE);
                        probeInfo.text(String.format(OK + "Vis: %s" + ((power==10) ? " (max)" : ""), power));
                    }
                }
            });
            return null;
        }
    }
}
