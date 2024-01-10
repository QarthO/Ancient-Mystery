package gg.quartzdev.ancientmystery.game.encounters;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.GameManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;

public class GuardianListeners implements Listener {

    AncientMystery plugin;
    GameManager gameManager;

    public GuardianListeners(){
        this.plugin = AncientMystery.getInstance();
        this.gameManager = this.plugin.gameManager;
    }

    @EventHandler
    public void onBlazeDeath(EntityDeathEvent event){
        if(event.getEntity().getType() != EntityType.BLAZE){
            return;
        }
        List<ItemStack> drops = event.getDrops();
        drops.clear();
        drops.add(this.getGlowstoneDrop());
    }

    public ItemStack getGlowstoneDrop(){
        ItemStack item = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta itemMeta = item.getItemMeta();
        Component component = Component.text("Charge Dust").decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(component);
        item.setItemMeta(itemMeta);
        return item;
    }

    public void onGuardianDamage(EntityDamageByEntityEvent event){

    }

}
