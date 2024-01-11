package gg.quartzdev.ancientmystery.game.encounters.guardian;

import gg.quartzdev.ancientmystery.game.encounters.EncounterMob;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.UUID;

public class GuardianBoss extends EncounterMob {
    Location spawnLocation;

    public GuardianBoss(UUID gameId, NamespacedKey raidKey){
        super(gameId, EntityType.ELDER_GUARDIAN, raidKey);
        spawnLocation = new Location(Bukkit.getWorld("world"), -3, 129, 17);

    }

    public void spawn(){
        ElderGuardian boss = (ElderGuardian) spawnLocation.getWorld().spawnEntity(spawnLocation, this, CreatureSpawnEvent.SpawnReason.CUSTOM);
        PdcUtil.brandMob(this.key, boss, this.gameId);
        AttributeInstance maxHealth = boss.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        maxHealth.setBaseValue(BOSS_MAX_HEALTH);
        boss.setHealth(this.BOSS_MAX_HEALTH);
    }


}
