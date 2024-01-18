package gg.quartzdev.ancientmystery.game.encounters.guardian;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.data.YMLguardian;
import gg.quartzdev.ancientmystery.util.LootUtil;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import org.bukkit.entity.Blaze;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GuardianListeners implements Listener {

    AncientMystery plugin;
    YMLguardian guardianConfig;
    public GuardianListeners(){
        this.plugin = AncientMystery.instance;
    }

    @EventHandler
    public void onBlazeDeath(EntityDeathEvent event){
        if(!(event.getEntity() instanceof Blaze blaze)){
            return;
        }
        if(PdcUtil.getMobBrand(this.plugin.gameKey, blaze) == null){
            return;
        }
        List<ItemStack> drops = event.getDrops();
        drops.clear();
        drops.add(LootUtil.guardianMinionLoot(1));
    }

    public void onGuardianDamage(EntityDamageByEntityEvent event){

    }



}
