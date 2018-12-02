package de.derkalaender.ghyn;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Registry {
    static List<ItemStack> GLASS_BLOCKS = new ArrayList<>();

    @SubscribeEvent
    public void registerPotion(RegistryEvent.Register<Potion> e) {
        PotionBloodLoss potionBloodLoss = new PotionBloodLoss(true, 0xcc0000);
        e.getRegistry().register(potionBloodLoss);
    }
}
