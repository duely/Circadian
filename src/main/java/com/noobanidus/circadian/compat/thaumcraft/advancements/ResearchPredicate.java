package com.noobanidus.circadian.compat.thaumcraft.advancements;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.advancements.IGenericPlayerPredicate;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.JsonUtils;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ResearchPredicate implements IGenericPlayerPredicate {
    public static ResearchPredicate ANY = new ResearchPredicate(new ArrayList<>());
    private List<ResearchEntry> requirements;

    private ResearchPredicate(List<ResearchEntry> requirements) {
        this.requirements = requirements;
    }

    @Override
    public boolean test (EntityPlayerMP player)
    {
        IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);

        for (ResearchEntry entry : requirements) {
            int stage = knowledge.getResearchStage(entry.getKey()) + 1;

            // enforcing full stages for all of them
            if (stage < entry.getStages().length) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ResearchPredicate deserialize(@Nullable JsonElement element)
    {
        if (element != null && !element.isJsonNull())
        {
            JsonObject jsonobject = element.getAsJsonObject();
            List<ResearchEntry> researchTypes = Lists.newArrayList();

            if (jsonobject.has("research")) {
                for (JsonElement elem : JsonUtils.getJsonArray(jsonobject, "research")) {
                    String key = elem.getAsString();

                    ResearchEntry entry = ResearchCategories.getResearch(key);

                    if (entry != null) {
                        researchTypes.add(entry);
                    } else {
                        Circadian.LOG.error(String.format("Invalid research type `%s` for advancement.", key));
                    }
                }
            }

            return new ResearchPredicate(researchTypes);
        }
        else
        {
            return ANY;
        }
    }
}
