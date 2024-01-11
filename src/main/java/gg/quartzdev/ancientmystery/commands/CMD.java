package gg.quartzdev.ancientmystery.commands;

import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.Sender;
import org.bukkit.command.CommandSender;

public class CMD extends qCMD{

    public CMD(String cmdName, String group) {
        super(cmdName, group);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {

        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }

}
