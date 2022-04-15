package me.zwee.prestigetokens.utils;

import java.util.HashMap;
import java.util.Map;

public class Message {

    private static final ConfigManager.Config messageConfig = ConfigManager.getFile("messages");

    private static final Map<String, String> messages = new HashMap<String, String>(){{
        for(String message : messageConfig.getConfig().getConfigurationSection("Messages").getKeys(false)){
            put(message, messageConfig.getConfig().getString("Messages."+message));
        }
    }};

    public static String getMessage(String messageName){
        return messages.get(messageName);
    }

}
