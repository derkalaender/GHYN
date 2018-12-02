package de.derkalaender.ghyn;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Handler {

    //deal dmg for left-clicking glass
    @SubscribeEvent
    public void onPlayerLeftClickBlock(PlayerInteractEvent.LeftClickBlock e) {
        EntityPlayer player = e.getEntityPlayer();
        if(!player.isCreative()) {
            World world = e.getWorld();
            Item itemBlock = new ItemStack(world.getBlockState(e.getPos()).getBlock()).getItem();

            for(ItemStack itemInList : Registry.GLASS_BLOCKS) {
                if(itemBlock == itemInList.getItem()) {
                    dealDamage(player, 2.0f, 5.0f, 44.4f, Helper.createRandomTicks(10, 20), 0);
                }
            }
        }
    }

    //deal dmg for breaking glass
    @SubscribeEvent
    public void onBlockDestroyed(BlockEvent.BreakEvent e) {
        EntityPlayer player = e.getPlayer();
        if(!player.isCreative()) {
            World world = e.getWorld();
            Item itemBlock = new ItemStack(world.getBlockState(e.getPos()).getBlock()).getItem();

            for(ItemStack itemInList : Registry.GLASS_BLOCKS) {
                if(itemBlock == itemInList.getItem()) {
                    dealDamage(player, 4.0f, 100.0f, 33.3f, Helper.createRandomTicks(15, 20), 1);
                }
            }
        }
    }

    private void dealDamage(EntityPlayer player, float dmg, float chanceInPercent, float chanceBloodInPercent, int bloodDuration, int bloodLevel) {
        if(Helper.createChanceInPercent() <= chanceInPercent) {
            player.attackEntityFrom(new DamageSource("cut").setDamageBypassesArmor(), dmg);

            //additionally give blood loss effect
            if(Helper.createChanceInPercent() <= chanceBloodInPercent) {
                player.addPotionEffect(new PotionEffect(PotionBloodLoss.instance, bloodDuration, bloodLevel, true, true));
            }
        }
    }
}
