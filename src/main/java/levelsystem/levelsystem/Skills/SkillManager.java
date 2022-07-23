package levelsystem.levelsystem.Skills;

import levelsystem.levelsystem.Config.ConfigManager;
import levelsystem.levelsystem.SkillTypes;
import levelsystem.levelsystem.Skillful;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SkillManager {

    private static String color(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }

    private static File playerSkillFile;
    private static YamlConfiguration modifyPlayerSkillFile;

    private static Skillful main;

    public static void setupFile(Skillful skillful) {

        main = skillful;

        try {
            initiateFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getModifyPlayerSkillFile() { return modifyPlayerSkillFile; }
    public static File getPlayerSkillFile() { return playerSkillFile; }

    private static void initiateFiles() throws IOException {
        playerSkillFile = new File(Bukkit.getServer().getPluginManager().getPlugin(main.getDescription().getName()).getDataFolder(), "playerSkills.yml");

        if(!playerSkillFile.exists()) {
            playerSkillFile.createNewFile();
        }

        modifyPlayerSkillFile = YamlConfiguration.loadConfiguration(playerSkillFile);

        try {
            getModifyPlayerSkillFile().save(getPlayerSkillFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setupProfile(Player player) {
        System.out.println("Created profile");

        getModifyPlayerSkillFile().createSection("Players." + player.getUniqueId());

        setExperience(player, 0);
        getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".next-level-exp", ConfigManager.getStartExp());
        getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".perkpoints", 0);

        for (int i = 0; i < SkillTypes.values().length; i++) {
            getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".skills." + SkillTypes.values()[i].getSkillName() + ".level", 0);
        }

        saveFile();


    }

    public static boolean hasAccount(Player player) {
        return getModifyPlayerSkillFile().contains("Players." + player.getUniqueId()); }

    //Skills
    public static void setLevel(Player player, SkillTypes skill, int level) {
        getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".skills." + skill.getSkillName() + ".level", level); saveFile(); }

    public static void addLevel(Player player, SkillTypes skill) {
        getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".skills." + skill.getSkillName() + ".level", getLevel(player, skill) + 1); saveFile(); }

    public static void removeLevel(Player player, SkillTypes skill) {
        getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".skills." + skill.getSkillName() + ".level", getLevel(player, skill) - 1); saveFile(); }

    public static int getLevel(Player player, SkillTypes skill) {
        return getModifyPlayerSkillFile().getInt("Players." + player.getUniqueId() + ".skills." + skill.getSkillName() + ".level"); }

    //Perkpoints
    public static void addPerkpoints(Player player) {
        getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".perkpoints", getPerkpoints(player) + 1); saveFile(); }

    public static void addPerkpoints(Player player, int amount) {
        getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".perkpoints", getPerkpoints(player) + amount); saveFile(); }

    public static void removePerkpoints(Player player) {
        if(getPerkpoints(player) > 0)
            getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".perkpoints", getPerkpoints(player) - 1); saveFile(); }

    public static int getPerkpoints(Player player) {
        return getModifyPlayerSkillFile().getInt("Players." + player.getUniqueId() + ".perkpoints"); }

    public static double getNextPerkpointExp(Player player) {
        return getModifyPlayerSkillFile().getDouble("Players." + player.getUniqueId() + ".next-level-exp"); }

    public static void setNextPerkpointExp(Player player) {
        getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".next-level-exp", getNextPerkpointExp(player) * ConfigManager.getLevelChange());
    }

    public static void setExperience(Player player, double exp) {
        getModifyPlayerSkillFile().set("Players." + player.getUniqueId() + ".experience", exp); saveFile(); }

    public static double getExperience(Player player) {
        return getModifyPlayerSkillFile().getDouble("Players." + player.getUniqueId() + ".experience"); }

    public static void addExperience(Player player, double exp) {
        if(getExperience(player) + exp >= getNextPerkpointExp(player)) {
            addPerkpoints(player);
            setExperience(player, 0);
            setNextPerkpointExp(player);

            player.sendMessage(color("&6&lYou got 1 perk point"));
        } else {
            setExperience(player, getExperience(player) + exp);
        }

        saveFile();
    }

    public static void UpdateSkills(Player player) {
        //Health
        AttributeInstance healthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        healthAttribute.setBaseValue((SkillManager.getLevel(player, SkillTypes.HEALTH) * 2) + healthAttribute.getDefaultValue());

        //Agility
        AttributeInstance speedAttribute = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if(SkillManager.getLevel(player, SkillTypes.AGILITY) > 0) {
            speedAttribute.setBaseValue((SkillManager.getLevel(player, SkillTypes.AGILITY) * 0.01) + 0.1);
        }
        else {
            speedAttribute.setBaseValue(0.1);
        }
    }

    private static void saveFile() {
        System.out.println("Saved file");

        try {
            getModifyPlayerSkillFile().save(getPlayerSkillFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
