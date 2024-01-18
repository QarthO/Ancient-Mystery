package gg.quartzdev.ancientmystery.game;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.data.YMLguardian;
import gg.quartzdev.ancientmystery.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RaidManager {

    private final AncientMystery plugin;
    private final HashMap<UUID, Raid> raids;
    public final YMLguardian guardianConfig;

    public RaidManager(){
        this.plugin = AncientMystery.instance;
        this.raids = new HashMap<>();
        this.guardianConfig = new YMLguardian("encounter-guardian.yml");
    }

    public Collection<Raid> getRaids(){
        return raids.values();
    }

    public @Nullable Raid getRaid(UUID raidId){
        return raids.get(raidId);
    }

    public void create(Difficulty difficulty, Player creator){
        Raid raid = new Raid(difficulty, creator);
        raids.put(raid.id, raid);
        raid.raidState = RaidState.IDLE;
    }

    public void delete(UUID gameId){
        Raid raid = raids.get(gameId);
        if(raid != null){
            raids.remove(gameId);
        }
    }

    public void globalClock(){
        long delay = 0;
        long interval = 1;
        Bukkit.getAsyncScheduler().runAtFixedRate(plugin, task -> {
            for(Raid raid : raids.values()){
                if(raid.raidState != RaidState.RUNNING) continue;
                raid.tickTime();
            }
        }, delay, interval, TimeUnit.SECONDS);
    }

    public void startRaid(UUID raidId){
        Raid raid = raids.get(raidId);
        if(raid == null){
            this.plugin.logger.error(Messages.ERROR_RAID_NOT_FOUND.parse("raid-id", raidId.toString()));
            return;
        }
        raid.start();
    }

    public void loadEncountersConfig(){

    }


}
