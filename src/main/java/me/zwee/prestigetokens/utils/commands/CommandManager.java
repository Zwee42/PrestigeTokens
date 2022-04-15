package me.zwee.prestigetokens.utils.commands;

import me.prisonranksx.gui.PrestigeItem;
import me.zwee.prestigetokens.PrestigeTokens;
import me.zwee.prestigetokens.commands.PrestigeTokensCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager implements CommandExecutor {


    private static final List<BaseCommand> commands = new ArrayList<>();

    private final PrestigeTokens plugin;

    public CommandManager (PrestigeTokens plugin){this.plugin = plugin;}

    public static void AddBaseCommand(BaseCommand baseCommand){
        commands.add(baseCommand);
    }

    public void SetupCommands() {
        for (BaseCommand main : commands) {
            System.out.println("Registering Command: " + main.name());
            plugin.getCommand(main.name()).setExecutor(this);
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        for (BaseCommand main : commands) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (cmd.getName().equalsIgnoreCase(main.name())) {
                    if (args.length == 0) {
                        main.onCommand(player, args);
                        return true;
                    }
                        SubCommand target = this.get(main, args[0]);
                        if (target == null) {
                            player.sendMessage("This is not a valid subcommand");
                            return true;
                        }
                    ArrayList<String> a = new ArrayList<>(Arrays.asList(args));
                        a.remove(0);
                        args = a.toArray(new String[0]);
                        try {
                            target.onCommand(player, args);
                        } catch (Exception ignored) {
                        }

                        
                }
                }
            }

        return true;
    }

    private SubCommand get(BaseCommand baseCommand, String subcommandName) {

        for (SubCommand cmd : baseCommand.subCommandList()) {
            String cmdName = cmd.name();

            if (cmdName.equalsIgnoreCase(subcommandName)) {
                return cmd;
            }

            String[] args;
            int argLength = (args = cmd.aliases()).length;

            for (int index = 0; index < argLength; ++index) {
                String alias = args[index];
                if (subcommandName.equalsIgnoreCase(alias)) {
                    return cmd;
                }
            }
        }
        return null;
    }
}
