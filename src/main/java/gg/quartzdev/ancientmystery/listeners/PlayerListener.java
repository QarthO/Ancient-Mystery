package gg.quartzdev.ancientmystery.listeners;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.player.GamePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    AncientMystery plugin;

    public PlayerListener(){
        this.plugin = AncientMystery.getInstance();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        plugin.addGamePlayer(player);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player player)) return;
        GamePlayer gamePlayer = plugin.getGamePlayer(player);
        if(gamePlayer == null) return;
        int damage = (int) (event.getDamage()*10);
        gamePlayer.damage(damage);
        event.setDamage(0);
    }
}
