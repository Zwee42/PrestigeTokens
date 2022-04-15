package me.zwee.prestigetokens.commands.subcommands;

import lombok.Getter;
import me.zwee.prestigetokens.TokenManager;
import me.zwee.prestigetokens.utils.Colour;
import me.zwee.prestigetokens.utils.Message;
import me.zwee.prestigetokens.utils.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TokensRemoveCommand {
    private final TokenManager tokenManager = new TokenManager();

    public final SubCommand subCommand = new SubCommand() {
        @Override
        public void onCommand(Player player, String[] args) {
            if(player.hasPermission("prestigetokens.admin")){
                if(args.length == 2){
                    if(args[0] != null){
                        if (tokenManager.getPlayerTokens(player) != 0){
                            Player targetPlayer = Bukkit.getPlayer(args[0]);
                            int removedPT = Integer.parseInt(args[1]);
                            tokenManager.takePlayerTokens(targetPlayer , removedPT);
                            player.sendMessage(Colour.colour(Message.getMessage("TOKENS_REMOVED"))
                                    .replace("%amount%" , String.valueOf(removedPT))
                                    .replace("%player%" , targetPlayer.getName()));
                        }
                    }
                }
            }
        }

        @Override
        public String name() {
            return "remove";
        }

        @Override
        public String info() {
            return "remove prestige tokens from someone";
        }

        @Override
        public String[] aliases() {
            return new String[0];
        }
    };
}
