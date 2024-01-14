package gg.quartzdev.ancientmystery;

import gg.quartzdev.ancientmystery.data.Confiq;
import gg.quartzdev.ancientmystery.game.GameManager;
import gg.quartzdev.ancientmystery.game.player.GamePlayer;
import gg.quartzdev.ancientmystery.listeners.PlayerListener;
import gg.quartzdev.ancientmystery.util.Loqqer;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

public final class AncientMystery extends JavaPlugin {

    public static AncientMystery instance;
    public Loqqer logger;
    public Confiq config;

//    PDC keys
    public NamespacedKey gameId;

    public HashMap<UUID, GamePlayer> gamePlayers = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        this.logger = new Loqqer();
        this.config = new Confiq();

        this.enableMetrics();
        this.registerEvents();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void enableMetrics(){
        int pluginId = 20709;
        Metrics metrics = new Metrics(instance, pluginId);
    }

    public void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @SuppressWarnings("UnstableApiUsage")
    public String getVersion(){
        return instance.getPluginMeta().getVersion();
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
