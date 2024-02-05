package gg.quartzdev.ancientmystery.game.encounters.guardian;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.Raid;
import gg.quartzdev.ancientmystery.game.RaidManager;
import gg.quartzdev.ancientmystery.util.*;
import org.bukkit.Material;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class GuardianListeners implements Listener {

    AncientMystery plugin;
    Loqqer logger;
    RaidManager raidManager;
    public GuardianListeners(){
        this.plugin = AncientMystery.instance;
        this.logger = plugin.logger;
        this.raidManager = plugin.raidManager;
    }

    @EventHandler
    public void onBlazeDeath(EntityDeathEvent event){
        if(!(event.getEntity() instanceof Blaze blaze)){
            return;
        }
        if(PdcUtil.getEntityBrand(blaze) == null){
            return;
        }
        List<ItemStack> drops = event.getDrops();
        drops.clear();
        drops.add(LootUtil.guardianMinionLoot(1));
    }

    @EventHandler
    public void onGuardianDamage(EntityDamageEvent event) {
//        Makes sure mob belongs to this raid
        Raid raid = getRaidFromEntity(event.getEntity());
        if(raid == null){
            return;
        }
        raid.handleEvents(event);
    }

    @EventHandler
    public void onChargeAnchor(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Raid raid = getRaidFromEntity(player);
        if(raid == null){
            return;
        }
        if(event.getAction().isLeftClick() || event.getClickedBlock() == null){
            return;
        }
        if(!(event.getClickedBlock().getBlockData() instanceof RespawnAnchor anchor)){
            return;
        }
        if(player.getInventory().getItemInMainHand().getType() != Material.GLOWSTONE){
            return;
        }
        if(anchor.getCharges() == anchor.getMaximumCharges()){
            raid.handleEvents(event);
            return;
        }
        Sender.message(player, "filling up anchor");
    }

    @EventHandler
    public void onAnchorExplosion(BlockExplodeEvent event){
        event.blockList().clear();
    }

    @EventHandler
    public void onCrystalExplosion(EntityExplodeEvent event){
        //        Makes sure mob belongs to this raid
        UUID mobBrand = PdcUtil.getEntityBrand(event.getEntity());
        event.blockList().clear();
        if (mobBrand == null) {
            return;
        }
        Raid raid = raidManager.getRaid(mobBrand);
        if(raid == null){
            logger.error(Messages.ERROR_RAID_NOT_FOUND.parse("raid-id", mobBrand.toString()));
            return;
        }
//        raid.handleEvents(event);
    }

    @EventHandler
    public void onCrystalPlace(EntitySpawnEvent event){
        if(!(event.getEntity() instanceof EnderCrystal crystal)){
            return;
        }

        for(Entity e : event.getEntity().getNearbyEntities(5,5,5)){
            if(e instanceof Player player){
                UUID brand = PdcUtil.getEntityBrand(player);
                if(brand == null){
                    return;
                }
                PdcUtil.brandEntity(crystal, brand);
            }
        }
    }

    private @Nullable Raid getRaidFromEntity(Entity entity){
        UUID raidId = PdcUtil.getEntityBrand(entity);
        if (raidId == null) {
            return null;
        }
        Raid raid = raidManager.getRaid(raidId);
        if(raid == null){
            logger.error(Messages.ERROR_RAID_NOT_FOUND.parse("raid-id", raidId.toString()));
            return null;
        }
        return raid;
    }


}
