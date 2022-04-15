package me.zwee.prestigetokens.commands;


import lombok.Getter;
import me.zwee.prestigetokens.PrestigeTokens;
import me.zwee.prestigetokens.commands.subcommands.ShopCommand;
import me.zwee.prestigetokens.commands.subcommands.TokensBalanceCommand;
import me.zwee.prestigetokens.commands.subcommands.TokensGiveCommand;
import me.zwee.prestigetokens.commands.subcommands.TokensRemoveCommand;
import me.zwee.prestigetokens.utils.Colour;
import me.zwee.prestigetokens.utils.ConfigManager;
import me.zwee.prestigetokens.utils.Message;
import me.zwee.prestigetokens.utils.commands.BaseCommand;
import me.zwee.prestigetokens.utils.commands.CommandManager;
import me.zwee.prestigetokens.utils.commands.SubCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;


public class PrestigeTokensCommand {
    private ConfigManager.Config messageConfig = ConfigManager.getFile("messages");
    public void RegisterCommand(){
        CommandManager.AddBaseCommand(baseCommand);
    }

   private final BaseCommand baseCommand = new BaseCommand() {
        @Override
        public void onCommand(Player player, String[] args) {
            messageConfig.getConfig().getStringList("HELP_MESSAGE")
                    .forEach(x -> player.sendMessage(Colour.colour(x)));
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
                    new ShopCommand().subCommand,
                    new TokensRemoveCommand().subCommand
                    );}

        @Override
        public String[] aliases() {
            return new String[]{
                    "prestigetokens",
                    "ptokens"
            };
        }
    };
}
