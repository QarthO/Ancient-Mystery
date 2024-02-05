package gg.quartzdev.ancientmystery.commands;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.Raid;
import gg.quartzdev.ancientmystery.game.RaidManager;
import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import gg.quartzdev.ancientmystery.util.Sender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CMDforceend extends qCMD {
    RaidManager raidManager;
    AncientMystery plugin;
    public CMDforceend(String cmdName, String group) {
        super(cmdName, group);
        this.raidManager = AncientMystery.instance.raidManager;
        this.plugin = AncientMystery.instance;
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player player)){
            Sender.message(sender, Messages.ERROR_PLAYER_ONLY_COMMAND);
            return false;
        }

        for(Entity entity : player.getWorld().getEntities()){
            if(entity instanceof Player){
                continue;
            }

            UUID entityBrand = PdcUtil.getEntityBrand(entity);
            if(entityBrand != null){
                entity.remove();
            }
        }

        UUID raidId = PdcUtil.getEntityBrand(player);
        if(raidId == null){
            Sender.message(player, Messages.ERROR_RAID_NOT_FOUND.parse("raid-id", "null"));
            return false;
        }
        Raid raid = raidManager.getRaid(raidId);
        if(raid == null){
            Sender.message(player, Messages.ERROR_RAID_NOT_FOUND.parse("raid-id", raidId.toString()));
            return false;
        }
        raid.finish();

        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
