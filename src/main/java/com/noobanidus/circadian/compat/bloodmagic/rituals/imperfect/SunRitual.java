package com.noobanidus.circadian.compat.bloodmagic.rituals.imperfect;

import WayofTime.bloodmagic.ritual.RitualRegister;
import WayofTime.bloodmagic.ritual.imperfect.IImperfectRitualStone;
import WayofTime.bloodmagic.ritual.imperfect.ImperfectRitual;
import net.minecraft.entity.player.EntityPlayer;
import vazkii.botania.common.block.ModFluffBlocks;

import javax.annotation.Nonnull;

@RitualRegister.Imperfect("sun")
@SuppressWarnings("unused")
public class SunRitual extends ImperfectRitual {
    public SunRitual() {
        super("sun", s -> s.getBlock() == ModFluffBlocks.sunnyQuartz, 5000, true, "ritual.circadian.imperfect.sun");
    }

    @Override
    public boolean onActivate(@Nonnull IImperfectRitualStone imperfectRitualStone, @Nonnull EntityPlayer player) {
        if (!imperfectRitualStone.getRitualWorld().isRemote)
            imperfectRitualStone.getRitualWorld().getWorldInfo().setRaining(false);

        return true;
    }
}

