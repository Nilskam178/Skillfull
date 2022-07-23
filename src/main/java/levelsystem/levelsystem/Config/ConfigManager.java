package levelsystem.levelsystem.Config;
import levelsystem.levelsystem.Skillful;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setupConfig(Skillful skillful) {
        ConfigManager.config = skillful.getConfig();
        skillful.saveDefaultConfig();
    }

    public static double getExpMod() { return config.getDouble("exp-gain-modifier"); }

    public static double getStartExp() { return config.getDouble("levels.start-exp"); }

    public static double getLevelChange() { return config.getDouble("levels.level-change"); }

    public static int getMaxLevel() { return config.getInt("levels.max-level"); }
}
