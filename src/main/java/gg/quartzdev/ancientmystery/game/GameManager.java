package gg.quartzdev.ancientmystery.game;

import gg.quartzdev.ancientmystery.AncientMystery;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class GameManager {

    AncientMystery plugin;
    HashMap<UUID, Raid> games;

    public GameManager(){
        this.plugin = AncientMystery.instance;
        this.games = new HashMap<>();
    }

    public Collection<Raid> getGames(){
        return games.values();
    }

    public void create(Difficulty difficulty, Player creator){
        Raid raid = new Raid(difficulty, creator);
        games.put(raid.id, raid);
        raid.gameState = GameState.RUNNING;
    }

    public void delete(UUID gameId){
        Raid raid = games.get(gameId);
        if(raid != null){
            games.remove(gameId);
        }
    }

    public void globalClock(){
        long delay = 0;
        long interval = 1;
        Bukkit.getAsyncScheduler().runAtFixedRate(plugin, task -> {
            for(Raid raid : games.values()){
                if(raid.gameState != GameState.RUNNING) continue;
                raid.tickTime();
            }
        }, delay, interval, TimeUnit.SECONDS);
    }


}
