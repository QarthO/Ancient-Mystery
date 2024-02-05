package gg.quartzdev.ancientmystery.game;

import com.google.common.collect.Lists;
import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.encounters.guardian.EncGuardian;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import gg.quartzdev.ancientmystery.util.Sender;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class Raid {

    UUID id;
    Difficulty difficulty;
    Player creator;
    List<Player> players;
    RaidState raidState;
    int MAX_PLAYERS;
    int TIME;
    Location raidStartLocation;

    EncGuardian firstEncounter;

    public Raid(Difficulty difficulty, Player creator){
        this.id = UUID.randomUUID();
        this.difficulty = difficulty;
        this.creator = creator;
        this.players = new ArrayList<>();
        this.addPlayer(creator);
        this.raidStartLocation = AncientMystery.instance.config.getStartLocation();
        MAX_PLAYERS = 4;
        TIME = 0;
    }

    public UUID getId(){
        return this.id;
    }

    public Player getCreator(){
        return this.creator;
    }

    public List<Player> getRaidParty(){
        return Lists.newArrayList(players);
    }

    public void start(){
        raidState = RaidState.RUNNING;
        for(Player player : players){
            player.teleportAsync(raidStartLocation);
            PdcUtil.brandEntity(player, id);
        }
        firstEncounter = new EncGuardian(this.id);
        firstEncounter.start(players);
    }

    public void handleEvents(EntityDamageEvent event){
        firstEncounter.onBossDamage(event);
    }
    public void handleEvents(EntityExplodeEvent event){
//        firstEncounter.onCrystalExplosion(event);
    }
    public void handleEvents(PlayerInteractEvent event){
        firstEncounter.breakShield();
    }


    public void addPlayer(Player player){
        players.add(player);
        PdcUtil.brandEntity(player, getId());
    }

    public void setRaidStartLocation(Location location){
        this.raidStartLocation = location;
    }

    public Location getRaidStartLocation(){
        return null;
    }

    public void finish(){
        Bukkit.getLogger().info("Game finished: " + id);
        firstEncounter.finish();
        raidState = RaidState.FINISHED;
    }

    public int tickTime(){
        return TIME++;
    }

    public void broadcast(String message){
        for(Player player : players){
            Sender.message(player, message);
        }
    }

}
