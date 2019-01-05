package com.noobanidus.circadian.core.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.Name("Cicada Tweaker Core")
@IFMLLoadingPlugin.TransformerExclusions({"com.noobanidus.circadian.core.asm"})
@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(1337)
public class CicadaTweakerPlugin implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"com.noobanidus.circadian.core.asm.CicadaTweakerTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return "com.noobanidus.circadian.core.asm.CicadaTweakerContainer";
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
