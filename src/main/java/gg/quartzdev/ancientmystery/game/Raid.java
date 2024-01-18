package gg.quartzdev.ancientmystery.game;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.encounters.guardian.EncGuardian;
import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.Sender;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Raid {

    UUID id;
    Difficulty difficulty;
    Player creator;
    Set<Player> players;
    RaidState raidState;
    int MAX_PLAYERS;
    int TIME;
    Location joinLocation;

    public Raid(Difficulty difficulty, Player creator){
        this.id = UUID.randomUUID();
        this.difficulty = difficulty;
        this.creator = creator;
        this.players = new HashSet<>();
        this.addPlayer(creator);
        this.joinLocation = AncientMystery.instance.config.getStartLocation();
        MAX_PLAYERS = 4;
        TIME = 0;
    }

    public Player getCreator(){
        return this.creator;
    }

    public UUID getId(){
        return this.id;
    }



    public void start(){
        raidState = RaidState.RUNNING;
        for(Player player : players){
            player.teleportAsync(joinLocation);
        }
        new EncGuardian(this.id);
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void setJoinLocation(Location location){
        this.joinLocation = location;
    }

    public Location getJoinLocation(){
        return null;
    }

    public void finish(){
        Bukkit.getLogger().info("Game finsihed: " + id);
        raidState = RaidState.FINISHED;
    }

    public int tickTime(){
        return TIME++;
    }

    public void broadcast(Messages message){
        for(Player player : players){
            Sender.message(player, message);
        }
    }

}
