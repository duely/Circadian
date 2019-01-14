package com.noobanidus.circadian.compat.vanilla.advancements;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.advancements.IGenericPlayerPredicate;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.*;

public class BiomePredicate implements IGenericPlayerPredicate {
    public static BiomePredicate ANY = new BiomePredicate(new ArrayList<>());

    private List<BiomeDictionary.Type> biomeTypes;

    public BiomePredicate(List<BiomeDictionary.Type> biomeTypes) {
        this.biomeTypes = biomeTypes;
    }

    @Override
    public boolean test(EntityPlayerMP player) {
        World world = player.world;
        BlockPos pos = player.getPosition();

        Set<BiomeDictionary.Type> cur = BiomeDictionary.getTypes(world.provider.getBiomeForCoords(pos));
        for (BiomeDictionary.Type type : biomeTypes) {
            if (!cur.contains(type)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public BiomePredicate deserialize(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            List<BiomeDictionary.Type> biomeTypes = new ArrayList<>();
            Map<String, BiomeDictionary.Type> map = new HashMap<>();

            BiomeDictionary.Type.getAll().forEach((type) -> map.put(type.getName(), type));

            JsonObject jsonobject = element.getAsJsonObject();

            if (jsonobject.has("biome_types")) {
                for (JsonElement elem : JsonUtils.getJsonArray(jsonobject, "biome_types")) {
                    String key = elem.getAsString();

                    if (map.containsKey(key)) {
                        biomeTypes.add(map.get(key));
                    } else {
                        Circadian.LOG.error(String.format("Invalid biome type `%s` for advancement.", key));
                    }
                }
            }

            return new BiomePredicate(biomeTypes);
        } else {
            return ANY;
        }
    }
}

