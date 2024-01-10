package gg.quartzdev.ancientmystery.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Game {

    UUID gameId;
    Set<Player> players;
    GameState gameState;
    int MAX_PLAYERS;
    int TIME_LIMIT; // seconds
    int TIME_LEFT;

    Location joinLocation;



    public Game(){
        this.gameId = UUID.randomUUID();
        this.players = new HashSet<>();
        MAX_PLAYERS = 4;
        TIME_LIMIT = 120;
        TIME_LEFT = TIME_LIMIT;

    }

    public void start(){
        gameState = GameState.RUNNING;
    }

    public void setJoinLocation(Location location){
        this.joinLocation = location;
    }

    public Location getJoinLocation(){

    }

    public void finish(){
        Bukkit.getLogger().info("Game finsihed: " + gameId);
        gameState = GameState.FINISHED;
    }

    public int tickTime(){
        Bukkit.getLogger().info("Game: " + gameId + "Time: " + TIME_LEFT);
        TIME_LEFT--;
        if(TIME_LEFT < 0){
            finish();
        }
        return TIME_LEFT;
    }

}
