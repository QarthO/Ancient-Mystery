package gg.quartzdev.ancientmystery.game;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Raid {

    UUID id;
    Difficulty difficulty;
    Player creator;
    Set<Player> players;
    GameState gameState;
    int MAX_PLAYERS;
    int TIME_LIMIT; // seconds
    int TIME_LEFT;
    Location joinLocation;

    public Raid(Difficulty difficulty, Player creator){
        this.id = UUID.randomUUID();
        this.difficulty = difficulty;
        this.creator = creator;
        this.players = new HashSet<>();
        MAX_PLAYERS = 4;
        TIME_LIMIT = 120;
        TIME_LEFT = TIME_LIMIT;
    }

    public Player getCreator(){
        return this.creator;
    }

    public UUID getId(){
        return this.id;
    }

    public void start(){
        gameState = GameState.RUNNING;
    }

    public void setJoinLocation(Location location){
        this.joinLocation = location;
    }

    public Location getJoinLocation(){
        return null;
    }

    public void finish(){
        Bukkit.getLogger().info("Game finsihed: " + id);
        gameState = GameState.FINISHED;
    }

    public int tickTime(){
        Bukkit.getLogger().info("Game: " + id + "Time: " + TIME_LEFT);
        TIME_LEFT--;
        if(TIME_LEFT < 0){
            finish();
        }
        return TIME_LEFT;
    }

}
