package gg.quartzdev.ancientmystery.commands;

import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.Sender;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CommandManager extends Command {

    List<String> aliases = new ArrayList<>();
    HashMap<String, qCMD> commandsMap = new HashMap<>();

    public CommandManager(String name){
        super(name);
        aliases.add("am");
        super.setAliases(aliases);

        commandsMap.put("",                 new CMD("version", "admin"));
        commandsMap.put("reload",           new CMDreload("reload", "admin"));
        commandsMap.put("join",             new CMDjoin("join", "admin"));
        commandsMap.put("raid",             new CMDraid("raid", "player"));
        commandsMap.put("list",             new CMDlist("list", "admin"));
        commandsMap.put("forceend",         new CMDforceend("forceend", "admin"));
        commandsMap.put("clearcrystals",    new CMDclearcrystals("clearcrystals", "admin"));
        commandsMap.put("setboss",          new CMDsetboss("setboss", "admin"));
        commandsMap.put("setcrystal",       new CMDsetcrystal("setcrystal", "admin"));
        commandsMap.put("setstart",         new CMDsetstart("setstart", "admin"));

        Bukkit.getCommandMap().register(name, this);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String labelOrAlias, @NotNull String[] args) {

//        Send info command
        if(args.length == 0){
            return commandsMap.get("").run(sender, labelOrAlias, args);
        }

//        Get command from the label
        qCMD cmd = commandsMap.get(args[0]);

        if(cmd == null){
            Sender.message(sender, Messages.ERROR_CMD_NOT_FOUND.parse("cmd", args[0]));
            return false;
        }

//        Run the command
        return cmd.run(sender, labelOrAlias, args);
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String labelOrAlias, String[] args) throws IllegalArgumentException {
        List<String> completions = new ArrayList<>();
//
        if(args.length == 1){
            StringUtil.copyPartialMatches(args[0], commandsMap.keySet(), completions);
        }

        if(args.length > 1){
            qCMD cmd = commandsMap.get(args[0]);

            if(cmd == null) {
                return completions;
            }

            Iterable<String> rawCompletions = cmd.getTabCompletions(sender, args);
            if(rawCompletions != null) {
                StringUtil.copyPartialMatches(args[args.length-1], rawCompletions, completions);
            }
        }

        Collections.sort(completions);
        return completions;
    }
}
