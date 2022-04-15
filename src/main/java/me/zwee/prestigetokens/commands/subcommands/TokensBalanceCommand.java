package me.zwee.prestigetokens.commands.subcommands;


import lombok.Getter;
import me.zwee.prestigetokens.TokenManager;
import me.zwee.prestigetokens.utils.Colour;
import me.zwee.prestigetokens.utils.Message;
import me.zwee.prestigetokens.utils.commands.SubCommand;
import org.bukkit.entity.Player;

public class TokensBalanceCommand {
    private final TokenManager tokenManager = new TokenManager();

    public final SubCommand subCommand = new SubCommand() {
        @Override
        public void onCommand(Player player, String[] args) {
            player.sendMessage(Colour.colour(Message.getMessage("PLAYER_BALANCE"))
                    .replace("%amount%" ,String.valueOf(tokenManager.getPlayerTokens(player))));
        }

        @Override
        public String name() {
            return "balance";
        }

        @Override
        public String info() {
            return "show your balance";
        }

        @Override
        public String[] aliases() {
            return new String[]{
                    "bal"
            };
        }
    };
}
