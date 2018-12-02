package de.derkalaender.ghyn;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class PotionBloodLoss extends Potion {
    public static PotionBloodLoss instance;
    public final String NAME = "blood_loss";
    public  ResourceLocation icon;

    //set delay to between 4.5 and 7 seconds
    private int delay = Helper.createRandomTicks(4.5f,7.0f);

    PotionBloodLoss(boolean bad, int color) {
        super(bad, color);
        this.setPotionName("potion."+NAME);
        this.setRegistryName(GHYN.MOD_ID, NAME);
        this.icon = new ResourceLocation(GHYN.MOD_ID, "textures/gui/potion/"+NAME+".png");
        instance = this;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        //reduce delay by a factor of 2*amplifier
        int i = delay >> amplifier;

        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }

    @Override
    public void performEffect(@Nullable EntityLivingBase entity, int amplifier) {
        //set delay to between 4.5 and 7 seconds
        delay = Helper.createRandomTicks(4.5f,7.0f);

        if(entity != null) {
            //normally deal between .5 and 1.5 hearts of damage per blood loss
            float dmg = Helper.createRandomInt(1, 3);

            //Keep 1 heart if amplifier is 0
            if (amplifier > 0) {
                entity.attackEntityFrom(new DamageSource(NAME).setDamageBypassesArmor(), dmg);
            } else if(entity.getHealth() > 2.0f) {
                //set dmg so it reduces health to 1 heart
                if ((entity.getHealth() - dmg) < 2.0f) {
                    dmg = entity.getHealth() - 2.0f;
                }

                entity.attackEntityFrom(new DamageSource(NAME).setDamageBypassesArmor(), dmg);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        super.renderHUDEffect(x, y, effect, mc, alpha);
        mc.getTextureManager().bindTexture(icon);

        GlStateManager.enableBlend();
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        super.renderInventoryEffect(x, y, effect, mc);
        mc.getTextureManager().bindTexture(icon);

        GlStateManager.enableBlend();
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }
}
