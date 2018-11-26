package com.noobanidus.circadian.events;


import WayofTime.bloodmagic.event.RitualEvent;
import WayofTime.bloodmagic.ritual.IMasterRitualStone;
import WayofTime.bloodmagic.ritual.types.RitualMeteor;
import com.noobanidus.circadian.Circadian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RitualEventHandler {

    public static boolean meteorEnabled = Circadian.CONFIG.get("BloodMagic.Rituals.Meteor", "Platform", true, "Automatically generate a platform for meteor to land on.").getBoolean(true);

    @SubscribeEvent
    public static void onRitual (RitualEvent.RitualActivatedEvent event) {
        if (event.getRitual() instanceof RitualMeteor && meteorEnabled) {
            IMasterRitualStone mrs = event.getRitualStone();
            World world = mrs.getWorldObj();
            BlockPos p = mrs.getBlockPos();

            int y = Circadian.CONFIG.get("BloodMagic.Rituals.Meteor", "PlatformHeight", 24, "Height above the ritual stone to place platform.").getInt(24);

            BlockPos pos = new BlockPos(p.getX(), p.getY() + y, p.getZ());

            if (world.isAirBlock(pos)) {
                IBlockState stone = Blocks.STONE.getDefaultState();
                world.setBlockState(pos, stone);
            }
        }
    }
}
