package gg.quartzdev.ancientmystery.game.encounters.guardian;

import gg.quartzdev.ancientmystery.data.YMLguardian;
import gg.quartzdev.ancientmystery.game.encounters.Encounter;
import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import gg.quartzdev.ancientmystery.util.Sender;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Set;
import java.util.UUID;

public class EncGuardian extends Encounter {

    YMLguardian guardianConfig;
    Audience audience;
    int currentShields = 6;
    ElderGuardian boss;
    BossBar bossBarHp;
    BossBar bossBarShields;

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
        this.boss = (ElderGuardian) this.guardianConfig.getBossSpawnLocation().getWorld().spawnEntity(this.guardianConfig.getBossSpawnLocation(), EntityType.ELDER_GUARDIAN);
        PdcUtil.brandEntity(this.boss, this.raidId);
        bossBarShields = BossBar.bossBar(Component.text("Shields: " + currentShields),1, BossBar.Color.BLUE, BossBar.Overlay.NOTCHED_6);
        bossBarHp = BossBar.bossBar(Component.text("Health: 200"),1, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
    }

    public void start(Set<Player> players){
        spawnBoss();
        this.audience = Audience.audience(players);
        audience.showBossBar(bossBarShields);
        audience.showBossBar(bossBarHp);
    }

    public void trySpawnBlaze(){

    }

    public void onCrystalExplosion(EntityExplodeEvent event){
        currentShields -=1;
        int maxShields = 6;
        double percent = (double) currentShields / maxShields;
        double rounded = Math.max(0,Math.min(1, percent));
        bossBarShields.progress((float) rounded);
        bossBarShields.name(Component.text("Shields: " + currentShields));
    }

    public void onBossDamage(EntityDamageEvent event){

        //        Only tracking boss damage
        if (!(event.getEntity() == boss)) {
            return;
        }

//        Requires 0 shields to deal damage to boss
        if(currentShields > 0){
            event.setCancelled(true);
            if(event instanceof EntityDamageByEntityEvent e){
               if(e.getDamager() instanceof Player player){
                   Sender.message(player, "you must break the shield first!");
               }
            }
            return;
        }

        double newHp = boss.getHealth() - event.getDamage();
        double maxHp = boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double percent = newHp / maxHp;
        double rounded = Math.max(0,Math.min(1, percent));
        bossBarHp.progress((float) rounded);
        bossBarHp.name(Component.text("Health: " + (int) Math.max(0, newHp)));
        if(newHp <= 0){
            Bukkit.getScheduler().runTaskLater(plugin, task ->{
                audience.hideBossBar(bossBarShields);
                audience.hideBossBar(bossBarHp);
                bossBarHp = null;
                bossBarShields = null;
            }, 60);
        }
    }

}
