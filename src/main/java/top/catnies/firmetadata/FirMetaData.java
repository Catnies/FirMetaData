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

    Plugin plugin = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");

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
            case "get" -> {
                return getMetaData(player, key);
            }
            case "remove" -> {
                return removeMetaData(player, key);
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
        player.setMetadata(key, new FixedMetadataValue(plugin, value));
        return "OK";
    }


    // 读取值
    public String getMetaData(Player player, String key){
        List<MetadataValue> metadata = player.getMetadata(key);
        if (!metadata.isEmpty()) return metadata.getFirst().asString();
        return "Empty Value";
    }


    // 清除值
    public String removeMetaData(Player player, String key) {
        player.removeMetadata(key, plugin);
        return "OK";
    }


    // 存在值
    public String hasMetaData(Player player, String key) {
        boolean b = player.hasMetadata(key);
        return b ? "yes" : "no";
    }

}
