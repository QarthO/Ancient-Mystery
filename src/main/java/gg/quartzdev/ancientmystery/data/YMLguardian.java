package gg.quartzdev.ancientmystery.data;

import gg.quartzdev.ancientmystery.util.ReadUtil;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class YMLguardian extends YML{
    public YMLguardian(String fileName) {
        super(fileName);
        this.loadAll();
    }

    public void loadAll(){
        this.loadBossHp();
        this.loadBossSpawnLocation();
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

}
