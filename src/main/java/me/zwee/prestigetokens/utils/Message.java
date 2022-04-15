package me.zwee.prestigetokens.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {

    private static final ConfigManager.Config messageConfig = ConfigManager.getFile("messages");

    private static final Map<String, String> messages = new HashMap<String, String>(){{
        for(String messageName : messageConfig.getConfig().getConfigurationSection("Messages").getKeys(false)){
//            List<String> message = messageConfig.getConfig().getStringList("Messages."+messageName);
//            if(message.size() == 1){
//                put(messageName, message.get(0));
//            }else{
//                put(messageName, String.join("\n", message));
//            }
            put(messageName, messageConfig.getConfig().getString("Messages."+messageName));
//
//            if(messageConfig.getConfig().getStringList("Messages."+message).size() == 1){
//                put(message, messageConfig.getConfig().getString("Messages."+message));
//            }else{
//
//            }
        }
    }};

    public static String getMessage(String messageName){
        return Colour.colour(messages.get(messageName));
    }

}
