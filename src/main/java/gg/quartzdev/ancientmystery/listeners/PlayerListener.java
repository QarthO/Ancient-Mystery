package gg.quartzdev.ancientmystery.listeners;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.Raid;
import gg.quartzdev.ancientmystery.game.RaidManager;
import gg.quartzdev.ancientmystery.gui.GamesInventory;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerListener implements Listener {

    AncientMystery plugin;
    RaidManager raidManager;

    public PlayerListener(){
        this.plugin = AncientMystery.instance;
        this.raidManager = plugin.raidManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID raidId = PdcUtil.getEntityBrand(player);
        if(raidId == null){
            return;
        }
        Raid raid = raidManager.getRaid(raidId);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
//        if(!(event.getEntity() instanceof Player player)) return;
//        GamePlayer gamePlayer = plugin.getGamePlayer(player);
//        if(gamePlayer == null) return;
//        int damage = (int) (event.getDamage()*10);
//        gamePlayer.damage(damage);
//        event.setDamage(0);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getHolder() instanceof GamesInventory gamesInventory){
            gamesInventory.onClick(event);
        }
    }
}
