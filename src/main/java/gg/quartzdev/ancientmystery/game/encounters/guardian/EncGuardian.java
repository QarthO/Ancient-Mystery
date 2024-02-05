package gg.quartzdev.ancientmystery.game.encounters.guardian;

import gg.quartzdev.ancientmystery.data.YMLguardian;
import gg.quartzdev.ancientmystery.game.encounters.Encounter;
import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class EncGuardian extends Encounter {

    YMLguardian guardianConfig;
    Audience audience;
    boolean hasShield = true;
    ElderGuardian boss;
    BossBar bossBarHp;
    BossBar bossBarShields;
    Set<BukkitTask> scheduledTasks;
    World world;
    int crystalSpawnCount = 0;
    int blazeSpawnCount = 0;

    public EncGuardian(UUID raidId){
        super(raidId);
        this.guardianConfig = this.raidManager.guardianConfig;
        scheduledTasks = new HashSet<>();
    }

    public void spawnBoss(){
        Location bossSpawnLocation = this.guardianConfig.getBossSpawnLocation();
        if(bossSpawnLocation == null){
            this.raidManager.broadcast(this.raidId, Messages.ERROR_BOSS_SPAWN_LOCATION.get());
            return;
        }
        this.boss = (ElderGuardian) this.guardianConfig.getBossSpawnLocation().getWorld().spawnEntity(this.guardianConfig.getBossSpawnLocation(), EntityType.ELDER_GUARDIAN);
        world = boss.getWorld();
        PdcUtil.brandEntity(this.boss, this.raidId);
        bossBarShields = BossBar.bossBar(Component.text("Shield"),1, BossBar.Color.BLUE, BossBar.Overlay.PROGRESS);
        bossBarHp = BossBar.bossBar(Component.text("Health: 200"),1, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
    }

    public void start(List<Player> players){
        spawnBoss();
        this.audience = Audience.audience(players);
        audience.showBossBar(bossBarShields);
        audience.showBossBar(bossBarHp);
        startBlazeSpawner();
        startCrystalSpawner();
    }

    public void finish(){
        audience.sendMessage(Component.text("encounter complete"));
        if(boss != null && boss.getHealth() > 0){
            audience.sendMessage(Component.text("force ending via command"));
            boss.remove();
        }
        Bukkit.getScheduler().runTaskLater(plugin, task ->{
            audience.hideBossBar(bossBarShields);
            audience.hideBossBar(bossBarHp);
            bossBarHp = null;
            bossBarShields = null;
        }, 60);
        for(BukkitTask tasks : scheduledTasks){
            tasks.cancel();
        }
        for(Entity entity : world.getEntities()){
            if(entity instanceof Player){
                continue;
            }
            UUID entityBrand = PdcUtil.getEntityBrand(entity);
            if(raidId != null && raidId == entityBrand){
                entity.remove();
            }
        }
    }

    public void startBlazeSpawner(){
        int delay = 0;
        int interval = 10*20;
        BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            blazeSpawnCount += 1;
            audience.sendMessage(Component.text("spawning blaze"));
            Random random = new Random();
            Location location = guardianConfig.getCrystalLocations().get(random.nextInt(guardianConfig.getCrystalLocations().size()));
            Bukkit.getScheduler().runTask(plugin, () -> {
                Entity entity = boss.getLocation().getWorld().spawnEntity(location, EntityType.BLAZE);
                PdcUtil.brandEntity(entity, raidId);
            });
        }, delay, interval);

        scheduledTasks.add(task);
    }

    public void startCrystalSpawner(){
        int delay = 45*20;
        int interval = 10*20;
        BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            crystalSpawnCount += 1;
            if(crystalSpawnCount > 10){

            }
            audience.sendMessage(Component.text("spawning crystal"));
            Random random = new Random();
            Location location = guardianConfig.getCrystalLocations().get(random.nextInt(guardianConfig.getCrystalLocations().size()));
            Bukkit.getScheduler().runTask(plugin, () -> {
                Entity entity = boss.getLocation().getWorld().spawnEntity(location, EntityType.ENDER_CRYSTAL);
                PdcUtil.brandEntity(entity, raidId);
            });
        }, delay, interval);

        scheduledTasks.add(task);
    }

    public void breakShield(){
        long delay = 10*20;
        this.hasShield = false;
        bossBarShields.progress(0);
        Bukkit.getScheduler().runTaskLater(plugin, task -> {
            hasShield = true;
            bossBarShields.progress(1);
        }, delay);
    }

    public void onBossDamage(EntityDamageEvent event){

        //        Only tracking boss damage
        if (!(event.getEntity() == boss)) {
            return;
        }

//        Requires 0 shields to deal damage to boss
        if(hasShield){
            event.setCancelled(true);
            if(event instanceof EntityDamageByEntityEvent e){
               if(e.getDamager() instanceof Player player){
                   Sound sound = Sound.sound(Key.key("block.note_block.banjo"), Sound.Source.BLOCK, 1f, 1f);
                   player.playSound(sound);
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
            finish();
        }
    }

}
