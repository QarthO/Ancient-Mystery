package gg.quartzdev.ancientmystery.game.encounters.guardian;

import gg.quartzdev.ancientmystery.data.YMLguardian;
import gg.quartzdev.ancientmystery.game.Raid;
import gg.quartzdev.ancientmystery.game.encounters.Encounter;
import gg.quartzdev.ancientmystery.game.encounters.EncounterState;
import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class EncGuardian extends Encounter {

    YMLguardian guardianConfig;
    double bossCurrentHealth;
    int currentShields;

    public EncGuardian(UUID gameId){
        super(gameId);
        this.guardianConfig = this.raidManager.guardianConfig;
        this.spawnBoss();
    }

    public void spawnBoss(){
        Location bossSpawnLocation = this.guardianConfig.getBossSpawnLocation();
        if(bossSpawnLocation == null){
            Raid raid = this.plugin.raidManager.getRaid(this.gameId);
            if(raid == null){
                this.plugin.logger.error(Messages.ERROR_RAID_NOT_FOUND.parse("raid-id", this.gameId.toString()));
                return;
            }
            raid.broadcast(Messages.ERROR_BOSS_SPAWN_LOCATION);
            return;
        }
        Entity boss = this.guardianConfig.getBossSpawnLocation().getWorld().spawnEntity(this.guardianConfig.getBossSpawnLocation(), this.guardianConfig.getBossType());
        PdcUtil.brandMob(this.plugin.gameKey, boss, this.gameId);
    }

    public void startRaid(){
        spawnBoss();
    }

    public void tasks(){



    }

    public void trySpawnBlaze(){

    }

    public void onBossDamage(EntityDamageByEntityEvent event){

//        Makes sure mob belongs to this raid
        UUID mobBrand = PdcUtil.getMobBrand(this.plugin.gameKey, event.getEntity());
        if(mobBrand == null || mobBrand != this.gameId){
            return;
        }

//        Only tracking boss damage
        if(event.getEntity().getType() != this.guardianConfig.getBossType()){
            return;
        }

//        Boss only takes damage when raid is active
        if(this.state != EncounterState.ACTIVE){
            event.setCancelled(true);
            return;
        }

//        Requires 0 shields to deal damage to boss
        if(currentShields > 0){
            event.setCancelled(true);
        }
    }

}
