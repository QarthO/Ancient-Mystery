package gg.quartzdev.ancientmystery.commands;

import gg.quartzdev.ancientmystery.AncientMystery;
import org.bukkit.command.CommandSender;

public class CMDjoin extends qCMD {

    AncientMystery plugin;

    public CMDjoin(String cmdName, String group) {
        super(cmdName, group);
        this.plugin = AncientMystery.instance;
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {

        return false;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
