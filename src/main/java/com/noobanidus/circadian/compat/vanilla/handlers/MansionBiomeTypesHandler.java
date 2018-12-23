package com.noobanidus.circadian.compat.vanilla.handlers;

import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.core.asm.tweaks.Tweak;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.WoodlandMansion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MansionBiomeTypesHandler {
    public static boolean enabled = Circadian.CONFIG.get("Vanilla.Mansion", "AdditionalBiomes", true, "Allow the Woodland Mansion to spawn in additional biomes. Adds most woodlands and forests from Thaumcraft, Traverse and Vanilla.").getBoolean();

    public static void init() {
        if (!enabled) return;

        try {
            modifyFields();
        } catch (ReflectiveOperationException e) {
            Circadian.LOG.error("[Circadian] Error adjusting biomes for Woodland Mansion.");
            e.printStackTrace();
        }
    }

    public static void modifyFields () throws ReflectiveOperationException {
        Field field = ReflectionHelper.findField(WoodlandMansion.class, "ALLOWED_BIOMES", "field_191072_a");
        field.setAccessible(true);

        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        List<Biome> newBiomes = new ArrayList<>();

        newBiomes.addAll(Arrays.asList(Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.MUTATED_BIRCH_FOREST, Biomes.MUTATED_FOREST, Biomes.FOREST_HILLS, Biomes.FOREST, Biomes.MUTATED_TAIGA, Biomes.MUTATED_ROOFED_FOREST, Biomes.ROOFED_FOREST, Biomes.TAIGA, Biomes.COLD_TAIGA, Biomes.COLD_TAIGA_HILLS, Biomes.MUTATED_REDWOOD_TAIGA, Biomes.MUTATED_REDWOOD_TAIGA_HILLS));

        if (Loader.isModLoaded("traverse")) {
            newBiomes.add(ForgeRegistries.BIOMES.getValue(new ResourceLocation("traverse:woodlands")));
            newBiomes.add(ForgeRegistries.BIOMES.getValue(new ResourceLocation("traverse:autumnal_woods")));
            newBiomes.add(ForgeRegistries.BIOMES.getValue(new ResourceLocation("traverse:thicket")));
            newBiomes.add(ForgeRegistries.BIOMES.getValue(new ResourceLocation("traverse:forested_hills")));
            newBiomes.add(ForgeRegistries.BIOMES.getValue(new ResourceLocation("traverse:birch_forested_hills")));
            newBiomes.add(ForgeRegistries.BIOMES.getValue(new ResourceLocation("traverse:autumnal_wooded_hills")));
            newBiomes.add(ForgeRegistries.BIOMES.getValue(new ResourceLocation("traverse:snowy_coniferous_forest")));
        }
        if (Loader.isModLoaded("thaumcraft")) {
            newBiomes.add(ForgeRegistries.BIOMES.getValue(new ResourceLocation("thaumcraft:magical_forest")));
        }

        field.set(null, newBiomes);

        Circadian.LOG.info("[Circadian] Adjusted Woodland Mansions to spawn in additional biomes.");
    }
}