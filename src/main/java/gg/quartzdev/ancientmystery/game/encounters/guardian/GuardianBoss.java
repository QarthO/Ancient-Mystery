package gg.quartzdev.ancientmystery.game.encounters.guardian;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public class GuardianBoss extends EncounterMob {
    Location spawnLocation;

    public GuardianBoss(UUID gameId, NamespacedKey raidKey){
        super(gameId, EntityType.ELDER_GUARDIAN, raidKey);
        spawnLocation = new Location(Bukkit.getWorld("world"), -3, 129, 17);

    }

    public void spawn(){
//        ElderGuardian boss = (ElderGuardian) spawnLocation.getWorld().spawnEntity(spawnLocation, this, CreatureSpawnEvent.SpawnReason.CUSTOM);
//        PdcUtil.brandMob(this, boss, this.gameId);
//        AttributeInstance maxHealth = boss.getAttribute(Attribute.GENERIC_MAX_HEALTH);
//        maxHealth.setBaseValue(BOSS_MAX_HEALTH);
//        boss.setHealth(this.BOSS_MAX_HEALTH);
    }


}
