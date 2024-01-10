package gg.quartzdev.ancientmystery.game;

import gg.quartzdev.ancientmystery.AncientMystery;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class GameManager {

    AncientMystery plugin;
    HashMap<UUID, Game> games;

    public GameManager(){
        this.plugin = AncientMystery.getInstance();
        this.games = new HashMap<>();
    }

    public void create(){
        Game game = new Game();
        games.put(game.gameId, game);
        game.gameState = GameState.RUNNING;
    }

    public void delete(UUID gameId){
        Game game = games.get(gameId);
        if(game != null){
            games.remove(gameId);
        }
    }

    public void globalClock(){
        long delay = 0;
        long interval = 1;
        Bukkit.getAsyncScheduler().runAtFixedRate(plugin, task -> {
            for(Game game : games.values()){
                if(game.gameState != GameState.RUNNING) continue;
                game.tickTime();
            }
        }, delay, interval, TimeUnit.SECONDS);
    }


}
