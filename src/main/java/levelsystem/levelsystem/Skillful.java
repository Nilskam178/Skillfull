package levelsystem.levelsystem;

import levelsystem.levelsystem.Commands.LevelCommand;
import levelsystem.levelsystem.Config.ConfigManager;
import levelsystem.levelsystem.Listeners.DamageListener;
import levelsystem.levelsystem.Listeners.ExpGainListener;
import levelsystem.levelsystem.Listeners.InventoryListener;
import levelsystem.levelsystem.Listeners.JoinListener;
import levelsystem.levelsystem.Skills.SkillManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skillful extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);
        SkillManager.setupFile(this);

        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new ExpGainListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        getCommand("skillful").setExecutor(new LevelCommand());
    }

    @Override
    public void onDisable() {

    }
}
