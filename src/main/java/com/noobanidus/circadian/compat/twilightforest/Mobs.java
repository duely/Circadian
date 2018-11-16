package com.noobanidus.circadian.compat.twilightforest;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import twilightforest.entity.passive.EntityTFPenguin;

public class Mobs {
    public static void registerSpawns () {
        for (Biome biome: BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY)) {
            biome.getSpawnableList(EnumCreatureType.CREATURE).add(new Biome.SpawnListEntry(EntityTFPenguin.class, 10, 4, 4));
        }
    }
}