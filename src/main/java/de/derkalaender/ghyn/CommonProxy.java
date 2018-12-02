package de.derkalaender.ghyn;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class CommonProxy {

    public void initRegistry() {
        MinecraftForge.EVENT_BUS.register(new Registry());
    }

    public void initHandler() {
        MinecraftForge.EVENT_BUS.register(new Handler());
    }

    public void initGlassList() {
        for(String name : OreDictionary.getOreNames()) {
            if(name.toLowerCase().contains("glass")) {
                List<ItemStack> oreStack = OreDictionary.getOres(name);
                Registry.GLASS_BLOCKS.addAll(oreStack);
            }
        }
    }
}
