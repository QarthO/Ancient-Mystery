package gg.quartzdev.ancientmystery.game.encounters.guardian;

import gg.quartzdev.ancientmystery.game.encounters.Encounter;
import gg.quartzdev.ancientmystery.game.encounters.EncounterState;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class GuardianRaid extends Encounter {

    EntityType BOSS_TYPE;
    double BOSS_MAX_HEALTH;
    double bossCurrentHealth;
    int currentShields;
    Location bossSpawnLocation;
    NamespacedKey key;


    public GuardianRaid(UUID gameId){
        super(gameId);
        this.BOSS_TYPE = EntityType.ELDER_GUARDIAN;
        this.BOSS_MAX_HEALTH = 200.0;
        this.key = new NamespacedKey(this.plugin, "guardian");
    }

    public void spawnBoss(){
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
        UUID mobBrand = PdcUtil.getMobBrand(this.key, event.getEntity());
        if(mobBrand == null || mobBrand != this.gameId){
            return;
        }

//        Only tracking boss damage
        if(event.getEntity().getType() != this.BOSS_TYPE){
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
