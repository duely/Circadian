package com.noobanidus.circadian.compat.bloodmagic.rituals.imperfect;

import WayofTime.bloodmagic.BloodMagic;
import WayofTime.bloodmagic.ritual.RitualRegister;
import WayofTime.bloodmagic.ritual.imperfect.IImperfectRitualStone;
import WayofTime.bloodmagic.ritual.imperfect.ImperfectRitual;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

@RitualRegister.Imperfect("day")
public class DayRitual extends ImperfectRitual {
	public DayRitual() {
		super("day", s -> s.getBlock() == Blocks.GOLD_BLOCK, 5000, true, "ritual.circadian.imperfect.day");
	}

	@Override
	public boolean onActivate(IImperfectRitualStone imperfectRitualStone, EntityPlayer player) {

		if (!imperfectRitualStone.getRitualWorld().isRemote)
			imperfectRitualStone.getRitualWorld().setWorldTime((imperfectRitualStone.getRitualWorld().getWorldTime() / 24000) * 24000);

		return true;
	}
}
