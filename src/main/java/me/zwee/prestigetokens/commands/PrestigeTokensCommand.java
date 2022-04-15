package me.zwee.prestigetokens.commands;


import me.zwee.prestigetokens.commands.subcommands.*;
import me.zwee.prestigetokens.utils.Colour;
import me.zwee.prestigetokens.utils.ConfigManager;
import me.zwee.prestigetokens.utils.Message;
import me.zwee.prestigetokens.utils.commands.BaseCommand;
import me.zwee.prestigetokens.utils.commands.CommandManager;
import me.zwee.prestigetokens.utils.commands.SubCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;


public class PrestigeTokensCommand {
    private final ConfigManager.Config messageConfig = ConfigManager.getFile("messages");
    public void RegisterCommand(){
        CommandManager.AddBaseCommand(baseCommand);
    }

   private final BaseCommand baseCommand = new BaseCommand() {
        @Override
        public void onCommand(Player player, String[] args) {
            messageConfig.getConfig().getStringList("Messages.HELP_MESSAGE")
                    .forEach(line -> player.sendMessage(Colour.colour(line)));
        }

        @Override
        public String name() {
            return "pt";
        }

        @Override
        public String info() {
            return "main pt command";
        }

        @Override
        public List<SubCommand> subCommandList() {
            return Arrays.asList(
                    new TokensBalanceCommand().subCommand,
                    new TokensGiveCommand().subCommand,
                    new TokensRemoveCommand().subCommand,
                    new PluginReloadCommand().subCommand,
                    new ShopCommand().subCommand
                    );}

        @Override
        public String[] aliases() {
            return new String[]{
                    "prestigetokens",
                    "prestigetoken",
                    "ptokens",
                    "ptoken"
            };
        }
    };
}
