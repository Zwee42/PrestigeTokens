package me.zwee.prestigetokens.utils;


import lombok.Getter;
import me.zwee.prestigetokens.PrestigeTokens;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.util.*;

public class ConfigManager {

    private final static Map<String, Config> configs = new HashMap<>();

    @Getter private static PrestigeTokens plugin;

    public ConfigManager(PrestigeTokens plugin) {ConfigManager.plugin = plugin;}

    public static Config getFile(String configFileName) { return configs.get(configFileName); }

    public static List<Config> getFiles(){ return new ArrayList<>(configs.values()); }

    public void RegisterConfigs(){

            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdir();
            }
            List<String> configFiles = new ArrayList<>(Arrays.asList(
                    "data",
                    "shop",
                    "messages"
                    ));
            System.out.print("Configs");
            for(String string : configFiles){ configs.put(string, new Config(string)); }
            loadConfig();
    }

    private void loadConfig(){
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }


    public static class Config {
        private FileConfiguration config;
        private final File configFile;

        public Config(String name) {
            String fileName = name.split("/")[name.split("/").length-1];
            if(name.contains("/")){
                configFile = new File(plugin.getDataFolder()+"/"+name.substring(0, name.length()- fileName.length()), fileName+".yml");
            }else{
                configFile = new File(plugin.getDataFolder(), fileName+".yml");
            }

            if(!configFile.exists()){
                try {
                    config = YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource(name+".yml")));
                    configFile.createNewFile();
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage(Colour.colour("&2The "+fileName+".yml file has been created"));
                } catch (IOException e) {
                    Bukkit.getServer().getConsoleSender().sendMessage(Colour.colour("&cCould not create the "+fileName+".yml file"+"\n"));
                }
                }else{
                    config = YamlConfiguration.loadConfiguration(configFile);
                }
            saveConfig();


        }

        public FileConfiguration getConfig() {
            return config;
        }

        public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(Colour.colour("&4"+e));
        }
    }
    public void reloadConfig(){
            config = YamlConfiguration.loadConfiguration(configFile);
    }
    }
}
