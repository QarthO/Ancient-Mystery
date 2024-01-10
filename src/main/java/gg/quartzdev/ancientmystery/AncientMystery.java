package gg.quartzdev.ancientmystery;

import gg.quartzdev.ancientmystery.game.GameManager;
import gg.quartzdev.ancientmystery.game.player.GamePlayer;
import gg.quartzdev.ancientmystery.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

public final class AncientMystery extends JavaPlugin {

    private static AncientMystery instance;
    public GameManager gameManager;
    public static AncientMystery getInstance(){
        return instance;
    }

    public HashMap<UUID, GamePlayer> gamePlayers = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        this.gameManager = new GameManager();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        updateAllHealth();
        gameManager.create();
        gameManager.globalClock();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void updateAllHealth(){
        long delay = 1;
        long period = 40;
        Bukkit.getScheduler().runTaskTimer(instance, () -> {
            for(GamePlayer gPlayer : gamePlayers.values()){
                gPlayer.updateHealthBar();
            }
        }, delay, period);
    }

    public boolean isGamePlayer(UUID playerId){
        return gamePlayers.containsKey(playerId);
    }

    public @Nullable GamePlayer getGamePlayer(Player player){
        UUID playerId = player.getUniqueId();
        if(isGamePlayer(playerId)){
            return null;
        }
        return this.gamePlayers.get(playerId);
    }

    public void addGamePlayer(Player player){
        UUID playerId = player.getUniqueId();
        if(isGamePlayer(playerId)){
            return;
        }
        this.gamePlayers.put(playerId, new GamePlayer(player));
    }


}
