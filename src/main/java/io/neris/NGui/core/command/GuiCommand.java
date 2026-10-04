package io.neris.NGui.core.command;

import io.neris.NGui.core.services.ServiceController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GuiCommand implements CommandExecutor {

    private final ServiceController service;
    private final Map<String, SubCommand> subCommandMap = new HashMap<>();
    private String helpMessage;


    public GuiCommand(ServiceController service) {
        this.service = service;

        addSubcommand(new AllMenuCloserSub(service.getPlayerMenuCloser()), "closeall");
        addSubcommand(new TestMenuOpenSub(service.getPlayerMenuOpener()), "test");
        addSubcommand(new CloseMenuForPlayerSub(service.getPlayerMenuCloser()), "closefor");

        buildHelpMessage();
    }


    public void addSubcommand(SubCommand command, String... aliases){
        for (String alias : aliases){
            subCommandMap.put(alias, command);
        }
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(args.length == 0){
            helpMessage(sender);
            return true;
        }

        executeSubcommand(sender, args);

        return true;
    }

    private void helpMessage(CommandSender sender){
        if(!sender.hasPermission("ngui.help")){
            return;
        }

        sender.sendMessage(helpMessage);
    }

    private void buildHelpMessage(){
        Set<String> aliasSet = subCommandMap.keySet();
        StringBuilder message = new StringBuilder("Список команд: ");
        for (String alias : aliasSet){
            message.append(alias).append(" ");
        }

        helpMessage = message.toString();
    }

    private void executeSubcommand(CommandSender sender, String[] args){
        String subcommandAlias = args[0];

        if(!subCommandMap.containsKey(subcommandAlias)){
            helpMessage(sender);
            return;
        }

        SubCommand subCommand = subCommandMap.get(subcommandAlias);
        subCommand.execute(sender, args);
    }


}
