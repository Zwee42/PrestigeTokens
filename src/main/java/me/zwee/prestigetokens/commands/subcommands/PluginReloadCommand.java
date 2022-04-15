package me.zwee.prestigetokens.commands.subcommands;

import me.zwee.prestigetokens.utils.ConfigManager;
import me.zwee.prestigetokens.utils.Message;
import me.zwee.prestigetokens.utils.commands.SubCommand;
import org.bukkit.entity.Player;

public class PluginReloadCommand {
    public final SubCommand subCommand = new SubCommand() {
        @Override
        public void onCommand(Player player, String[] args) {
            if (player.hasPermission("prestigetokens.admin")){
                ConfigManager.getFiles().forEach(ConfigManager.Config::reloadConfig);
                player.sendMessage(Message.getMessage("RELOAD"));
            }
        }

        @Override
        public String name() {
            return "reload";
        }

        @Override
        public String info() {
            return "reload the config files";
        }

        @Override
        public String[] aliases() {
            return new String[]{
                    "rl"
            };
        }
    };
}
