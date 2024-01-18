package gg.quartzdev.ancientmystery.game.encounters.guardian;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.data.YMLguardian;
import gg.quartzdev.ancientmystery.util.ItemStackUtil;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

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
        drops.add(ItemStackUtil.guardianMinionLoot(1));
    }

    public void onGuardianDamage(EntityDamageByEntityEvent event){

    }



}
