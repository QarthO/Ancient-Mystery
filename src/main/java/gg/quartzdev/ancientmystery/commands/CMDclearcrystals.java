package gg.quartzdev.ancientmystery.commands;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.RaidManager;
import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.Sender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDclearcrystals extends qCMD {
    RaidManager raidManager;
    AncientMystery plugin;
    public CMDclearcrystals(String cmdName, String group) {
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
        this.plugin.raidManager.guardianConfig.clearCrystalLocations();
        Sender.message(player, Messages.CLEAR_GUARDIAN_CRYSTAL);
        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
