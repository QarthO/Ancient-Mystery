package gg.quartzdev.ancientmystery.data;

import com.google.common.collect.Lists;
import gg.quartzdev.ancientmystery.util.ReadUtil;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class YMLguardian extends YML{
    public YMLguardian(String fileName) {
        super(fileName);
        this.loadAll();
    }

    public void loadAll(){
        this.loadBossHp();
        this.loadBossSpawnLocation();
        this.loadCrystalLocations();
    }

    String PATH_BOSS_HP = "boss.hp";
    int bossHp = 80;
    public void loadBossHp(){
        this.bossHp = ReadUtil.number(this.config.get(this.PATH_BOSS_HP)).intValue();
    }
    public int getBossHp(){
        return this.bossHp;
    }
    public void setBossHp(int hp){
        this.bossHp = hp;
        this.config.set(this.PATH_BOSS_HP, hp);
        this.saveFile();
    }

    String PATH_BOSS_SPAWN_LOCATION = "boss.spawn-location";
    Location bossSpawnLocation;
    public void loadBossSpawnLocation(){
        this.bossSpawnLocation = this.config.getLocation(this.PATH_BOSS_SPAWN_LOCATION);
    }
    public Location getBossSpawnLocation(){
        return this.bossSpawnLocation;
    }
    public void setBossSpawnLocation(Location location){
        this.bossSpawnLocation = location;
        this.config.set(this.PATH_BOSS_SPAWN_LOCATION, location);
        this.saveFile();
    }

    String PATH_BOSS_SHIELD_RECHARGE_DELAY = "boss.shield-recharge-delay";
    int bossShieldRechargeDelay;
    public void loadBossShieldRechargeDelay(){
        this.bossShieldRechargeDelay = ReadUtil.number(config.get(PATH_BOSS_SHIELD_RECHARGE_DELAY)).intValue();
    }
    public Location getBossShieldRechargeDelay(){
        return this.bossSpawnLocation;
    }
    public void setBossShieldRechargeDelay(int delay){
        this.bossShieldRechargeDelay = delay;
        this.config.set(PATH_BOSS_SHIELD_RECHARGE_DELAY, delay);
        saveFile();
    }

    List<Location> crystalLocations;
    final String PATH_CRYSTAL_LOCATIONS = "crystals.locations";

    public void loadCrystalLocations(){
        crystalLocations = (List<Location>) config.getList(PATH_CRYSTAL_LOCATIONS);
    }
    public void addCrystalLocation(Location location){
        if(crystalLocations == null){
            crystalLocations = new ArrayList<>();
        }
        crystalLocations.add(location);
        config.set(PATH_CRYSTAL_LOCATIONS, crystalLocations);
        saveFile();
    }
    public List<Location> getCrystalLocations(){
        return Lists.newArrayList(crystalLocations);
    }
    public void clearCrystalLocations(){
        if(crystalLocations == null){
            crystalLocations = new ArrayList<>();
        }
        crystalLocations.clear();
        config.set(PATH_CRYSTAL_LOCATIONS, crystalLocations);
        saveFile();
    }

}
