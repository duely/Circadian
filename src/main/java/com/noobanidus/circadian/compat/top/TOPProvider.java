package com.noobanidus.circadian.compat.top;

import WayofTime.bloodmagic.BloodMagic;
import WayofTime.bloodmagic.block.BlockRitualController;
import WayofTime.bloodmagic.block.enums.EnumRitualController;
import WayofTime.bloodmagic.ritual.Ritual;
import WayofTime.bloodmagic.ritual.imperfect.ImperfectRitual;
import WayofTime.bloodmagic.tile.TileMasterRitualStone;
import WayofTime.bloodmagic.util.helper.RitualHelper;
import com.noobanidus.circadian.compat.oreberries.blocks.BlockBerry;
import com.noobanidus.circadian.compat.thaumcraft.blocks.BlockCompressedVisBattery;
import com.rwtema.extrautils2.blocks.BlockEnderLilly;
import josephcsible.oreberries.BlockOreberryBush;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import org.apache.commons.lang3.StringUtils;
import thaumcraft.common.blocks.crafting.BlockVoidSiphon;
import thaumcraft.common.blocks.devices.BlockVisBattery;
import thaumcraft.common.blocks.world.ore.BlockCrystal;
import thaumcraft.common.tiles.crafting.TileVoidSiphon;
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
                    } else if (blockState.getBlock() instanceof BlockCrystal) {
                        int size = blockState.getValue(BlockCrystal.SIZE);
                        String progress = ((size == 0) ? "25%" : ((size == 1)) ? "50%" : ((size == 2)) ? "75%" : "100%");

                        probeInfo.text(((size == 3) ? OK : WARNING) + "Growth: " + progress);
                    } else if (blockState.getBlock() instanceof BlockVoidSiphon) {
                        TileVoidSiphon siphon = (TileVoidSiphon) world.getTileEntity(data.getPos());

                        int progress = (int) ((siphon.progress/2000.f) * 100.f);

                        probeInfo.text(((progress < 80) ? WARNING : OK ) + String.format("Growth: %d%%", progress));

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
                            String brew_name = I18n.format(brew.getUnlocalizedName());
                            probeInfo.text(OK + String.format("Brew: %s", brew_name));
                            if (timeLeft == 0) {
                                probeInfo.text(WARNING + "Unlit.");
                            } else {
                                probeInfo.text(OK + String.format("%d minutes remaining.", timeLeft));
                            }
                        }
                    } else if (blockState.getBlock() instanceof BlockRitualController) {
                        // Ritual name
                        // Current LP cost?
                        // Active or not
                        BlockRitualController block = (BlockRitualController) blockState.getBlock();
                        EnumRitualController type = blockState.getValue(block.getProperty());

                        String ritualType = type.toString();

                        if (type == EnumRitualController.IMPERFECT) {
                            ImperfectRitual ritual = BloodMagic.RITUAL_MANAGER.getImperfectRitual(world.getBlockState(data.getPos().up()));
                            if (ritual != null) {
                                String ritualName = StringUtils.capitalize(ritual.getName());
                                probeInfo.text(OK + String.format("Imperfect Ritual of %s", ritualName));
                                probeInfo.text(WARNING + String.format("Cost: %dLP", ritual.getActivationCost()));
                            } else {
                                probeInfo.text(WARNING + "Invalid ritual.");
                            }
                        } else {
                            TileMasterRitualStone tile = (TileMasterRitualStone) world.getTileEntity(data.getPos());

                            String key = RitualHelper.getValidRitual(world, data.getPos());
                            if (!key.isEmpty()) {
                                Ritual ritual = BloodMagic.RITUAL_MANAGER.getRitual(key);
                                String ritualName = I18n.format(ritual.getUnlocalizedName());
                                probeInfo.text(OK + ritualName);
                                if (tile != null && tile.isPowered()) {
                                    probeInfo.text(WARNING + "(Disabled via redstone)");
                                } else if (tile != null && tile.isActive()) {
                                    probeInfo.text(OK + "Active");
                                } else {
                                    probeInfo.text(WARNING + String.format("Activation cost: %,dLP", ritual.getActivationCost()));
                                }
                            } else {
                                probeInfo.text(WARNING + "Invalid ritual.");
                            }

                        }
                    }

                }
            });
            return null;
        }
    }
}
