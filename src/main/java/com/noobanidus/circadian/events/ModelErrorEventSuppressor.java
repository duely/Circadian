package com.noobanidus.circadian.events;

import com.noobanidus.circadian.Circadian;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SideOnly(Side.CLIENT)
public class ModelErrorEventSuppressor {

    public static boolean enabled = Circadian.CONFIG.get("ExtraUtils2.Errors", "Suppress", true, "Suppress modele and model variant loading errors from clogging up the error log.").getBoolean(true);

    @SubscribeEvent
    public static void onModelBakePost (ModelBakeEvent event) {
        Map<ResourceLocation, Exception> modelErrors = ReflectionHelper.getPrivateValue(ModelLoader.class, event.getModelLoader(), "loadingExceptions");
        Set<ModelResourceLocation> missingVariants = ReflectionHelper.getPrivateValue(ModelLoader.class, event.getModelLoader(), "missingVariants");

        List<ResourceLocation> errored = modelErrors.keySet().stream().filter((r) -> r.getResourceDomain().equals("extrautils2")).collect(Collectors.toList());
        List<ResourceLocation> missing = missingVariants.stream().filter((r) -> r.getResourceDomain().equals("extrautils2")).collect(Collectors.toList());

        errored.forEach(modelErrors::remove);
        missing.forEach(missingVariants::remove);

        Circadian.LOG.info(String.format("Suppressed %d Model Loading Errors and %d Missing Model Variant errors from ExtraUtils2.", errored.size(), missing.size()));
    }
}
