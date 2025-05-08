package top.catnies.firmetadata;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FirMetaData extends PlaceholderExpansion {

    Plugin placeholderApi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");

    @Override
    public @NotNull String getIdentifier() {
        return "firmetadata";
    }

    @Override
    public @NotNull String getAuthor() {
        return "catnies_metadata";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (!player.isOnline()) return "";
        return handleRequest((Player) player, params);
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        return handleRequest(player, params);
    }


    public String handleRequest(Player player, String params) {
        String[] args = params.split(",");
        if (args.length <= 1) return "Error Args";
        String action = args[0];
        String key = args[1];

        switch (action) {
            case "set" -> {
                if (args.length < 3) return "Error Args";
                String value = args[2];
                return setMetaData(player, key, value);
            }
            case "setPlugin" -> {
                if (args.length < 4) return "Error Args";
                String value = args[2];
                String pluginName = args[3];
                return setMetaData(player, key, value, pluginName);
            }
            case "get" -> {
                return getMetaData(player, key);
            }
            case "remove" -> {
                return removeMetaData(player, key);
            }
            case "removePlugin" -> {
                if (args.length < 3) return "Error Args";
                String pluginName = args[2];
                return removeMetaData(player, key, pluginName);
            }
            case "has" -> {
                return hasMetaData(player, key);
            }
            default -> {
                return "Error Args";
            }
        }
    }


    // 设置值
    public String setMetaData(Player player, String key, String value){
        player.setMetadata(key, new FixedMetadataValue(placeholderApi, value));
        return "OK";
    }
    public String setMetaData(Player player, String key, String value, String pluginName){
        Plugin customPlugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (customPlugin != null) {
            player.setMetadata(key, new FixedMetadataValue(customPlugin, value));
            return "OK";
        }
        return "plugin not found";
    }


    // 读取值
    public String getMetaData(Player player, String key){
        List<MetadataValue> metadata = player.getMetadata(key);
        if (!metadata.isEmpty()) return metadata.getFirst().asString();
        return "null";
    }


    // 清除值
    public String removeMetaData(Player player, String key) {
        player.removeMetadata(key, placeholderApi);
        return "OK";
    }
    public String removeMetaData(Player player, String key, String pluginName) {
        Plugin customPlugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (customPlugin != null) {
            player.removeMetadata(key, customPlugin);
            return "OK";
        }
        return "plugin not found";
    }


    // 存在值
    public String hasMetaData(Player player, String key) {
        boolean b = player.hasMetadata(key);
        return b ? "yes" : "no";
    }

}
