package me.zwee.prestigetokens.utils.commands;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class BaseCommand {

    public BaseCommand() {
    }

    public abstract void onCommand(Player player, String[] args);

    public abstract String name();

    public abstract String info();

    public abstract List<SubCommand> subCommandList();

    public abstract String[] aliases();



}
