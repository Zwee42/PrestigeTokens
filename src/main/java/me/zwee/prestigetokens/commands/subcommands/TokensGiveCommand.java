package me.zwee.prestigetokens.commands.subcommands;


import lombok.Getter;
import me.zwee.prestigetokens.TokenManager;

import me.zwee.prestigetokens.utils.Colour;
import me.zwee.prestigetokens.utils.Message;
import me.zwee.prestigetokens.utils.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TokensGiveCommand {
    private final TokenManager tokenManager = new TokenManager();

    public final SubCommand subCommand = new SubCommand() {
        @Override
        public void onCommand(Player player, String[] args) {
            if(player.hasPermission("prestigetokens.admin")){
                if(args.length == 2){
                    if(args[0] != null){
                        Player targetPlayer = Bukkit.getPlayer(args[0]);
                        int addedPT = Integer.parseInt(args[1]);
                        tokenManager.addPlayerTokens(targetPlayer, addedPT);
                        player.sendMessage(Colour.colour(Message.getMessage("TOKENS_RECEIVED"))
                                .replace("%amount%" , String.valueOf(addedPT)));
                    }
                }
            }
        }

        @Override
        public String name() {
            return "give";
        }

        @Override
        public String info() {
            return "give someone prestige tokens";
        }

        @Override
        public String[] aliases() {
            return new String[0];
        }
    };

}
