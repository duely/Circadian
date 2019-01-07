package com.noobanidus.circadian.compat.vanilla.advancements;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.noobanidus.circadian.Circadian;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BiomeTrigger implements ICriterionTrigger<BiomeTrigger.Instance> {
    private final ResourceLocation id = new ResourceLocation(Circadian.MODID, "biome_type");
    private final Map<PlayerAdvancements, BiomeTrigger.Listeners> listeners = Maps.newHashMap();

    @Override
    @Nonnull
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public void addListener(@Nonnull PlayerAdvancements advancementsIn, @Nonnull ICriterionTrigger.Listener<BiomeTrigger.Instance> listener) {
        BiomeTrigger.Listeners list = listeners.get(advancementsIn);

        if (list == null) {
            list = new BiomeTrigger.Listeners(advancementsIn);
            listeners.put(advancementsIn, list);
        }

        list.add(listener);
    }

    @Override
    public void removeListener(@Nonnull PlayerAdvancements advancementsIn, @Nonnull ICriterionTrigger.Listener<BiomeTrigger.Instance> listener) {
        BiomeTrigger.Listeners list = listeners.get(advancementsIn);

        if (list != null) {
            list.remove(listener);

            if (list.isEmpty()) {
                listeners.remove(advancementsIn);
            }
        }
    }

    @Override
    public void removeAllListeners(@Nonnull PlayerAdvancements advancementsIn) {
        listeners.remove(advancementsIn);
    }

    @Override
    @Nonnull
    public BiomeTrigger.Instance deserializeInstance(@Nonnull JsonObject json, @Nonnull JsonDeserializationContext context) {
        BiomePredicate biomePredicate = BiomePredicate.deserialize(json);
        return new BiomeTrigger.Instance(getId(), biomePredicate);
    }

    public void trigger(EntityPlayerMP player) {
        BiomeTrigger.Listeners list = listeners.get(player.getAdvancements());

        if (list != null) {
            list.trigger(player.getServerWorld(), player.getPosition());
        }
    }

    static class Instance extends AbstractCriterionInstance {
        BiomePredicate predicate;

        Instance(ResourceLocation location, BiomePredicate predicate) {
            super(location);

            this.predicate = predicate;
        }

        boolean test(WorldServer world, BlockPos pos) {
            return predicate.test(world, pos);
        }
    }


    static class Listeners {
        PlayerAdvancements advancements;
        Set<ICriterionTrigger.Listener<BiomeTrigger.Instance>> listeners = Sets.newHashSet();

        Listeners(PlayerAdvancements advancementsIn) {
            this.advancements = advancementsIn;
        }

        public boolean isEmpty() {
            return listeners.isEmpty();
        }

        public void add(ICriterionTrigger.Listener<BiomeTrigger.Instance> listener) {
            listeners.add(listener);
        }

        public void remove(ICriterionTrigger.Listener<BiomeTrigger.Instance> listener) {
            listeners.remove(listener);
        }

        void trigger(WorldServer world, BlockPos position) {
            List<ICriterionTrigger.Listener<BiomeTrigger.Instance>> list = Lists.newArrayList();

            for (ICriterionTrigger.Listener<BiomeTrigger.Instance> listener : listeners) {
                if (listener.getCriterionInstance().test(world, position)) {
                    list.add(listener);
                }
            }

            if (list.size() != 0) {
                for (ICriterionTrigger.Listener<BiomeTrigger.Instance> listener : list) {
                    listener.grantCriterion(advancements);
                }
            }
        }
    }
}
