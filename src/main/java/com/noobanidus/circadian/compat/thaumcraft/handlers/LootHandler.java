package com.noobanidus.circadian.compat.thaumcraft.handlers;

import thaumcraft.api.internal.WeightedRandomLoot;

public class LootHandler {
    public static void removeLootEntries() {
        WeightedRandomLoot.lootBagCommon.clear();
        WeightedRandomLoot.lootBagUncommon.clear();
        WeightedRandomLoot.lootBagRare.clear();
    }
}
