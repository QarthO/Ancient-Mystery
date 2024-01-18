package gg.quartzdev.ancientmystery.game.encounters.guardian;

import gg.quartzdev.ancientmystery.data.YMLguardian;
import gg.quartzdev.ancientmystery.game.Raid;
import gg.quartzdev.ancientmystery.game.encounters.Encounter;
import gg.quartzdev.ancientmystery.game.encounters.EncounterState;
import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class EncGuardian extends Encounter {

    YMLguardian guardianConfig;
    double bossCurrentHealth;
    int currentShields;
    Entity boss;

    public EncGuardian(UUID raidId){
        super(raidId);
        this.guardianConfig = this.raidManager.guardianConfig;
    }

    public void spawnBoss(){
        Location bossSpawnLocation = this.guardianConfig.getBossSpawnLocation();
        if(bossSpawnLocation == null){
            this.raidManager.broadcast(this.raidId, Messages.ERROR_BOSS_SPAWN_LOCATION.get());
            return;
        }
        this.boss = this.guardianConfig.getBossSpawnLocation().getWorld().spawnEntity(this.guardianConfig.getBossSpawnLocation(), this.guardianConfig.getBossType());
        PdcUtil.brandMob(this.boss, this.raidId);
    }

    public void start(){
        spawnBoss();
    }

    public void trySpawnBlaze(){

    }

    public void onBossDamage(EntityDamageByEntityEvent event){

//        Makes sure mob belongs to this raid
        UUID mobBrand = PdcUtil.getMobBrand(this.plugin.gameKey, event.getEntity());
        if(mobBrand == null || mobBrand != this.raidId){
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
