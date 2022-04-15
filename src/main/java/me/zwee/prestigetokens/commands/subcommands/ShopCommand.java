package me.zwee.prestigetokens.commands.subcommands;

import me.zwee.prestigetokens.gui.ShopGUI;
import me.zwee.prestigetokens.utils.commands.SubCommand;
import org.bukkit.entity.Player;

public class ShopCommand {

    public final SubCommand subCommand = new SubCommand() {
        private final ShopGUI gui = new ShopGUI();

        @Override
        public void onCommand(Player player, String[] args) {
        player.openInventory(gui.getShopGUI());
        }

        @Override
        public String name() {
            return "shop";
        }

        @Override
        public String info() {
            return "open the shop menu";
        }

        @Override
        public String[] aliases() {
            return new String[0];
        }
    };
}
