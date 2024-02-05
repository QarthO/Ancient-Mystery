package gg.quartzdev.ancientmystery.commands;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.RaidManager;
import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.Sender;
import org.bukkit.Difficulty;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDlist extends qCMD {
    RaidManager raidManager;
    AncientMystery plugin;
    public CMDlist(String cmdName, String group) {
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
        int raidCount = raidManager.getRaids().size();

        Sender.message(player, "Raids: " + raidCount);
        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
