package levelsystem.levelsystem;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import levelsystem.levelsystem.Config.ConfigManager;
import levelsystem.levelsystem.Skills.SkillManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class SkillGUI {

    private static String color(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }

    public static boolean openInventory(Player player) {
        Inventory skillgui = Bukkit.createInventory(player, 18, color("&bSkill menu"));

        ItemStack perkItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta perkItemMeta = (SkullMeta) perkItem.getItemMeta();

        GameProfile perkProfile = new GameProfile(UUID.randomUUID(), null);
        perkProfile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmExNDc0ZDg5MWQyYTY0N2VlMWFhNzliNTg5NDRhNTZlN2I4M2FiZDliN2NjMTM0ZTQyMGMxYjNhN2M0OTk4In19fQ=="));
        Field perkField;

        try {
            perkField = perkItemMeta.getClass().getDeclaredField("profile");
            perkField.setAccessible(true);
            perkField.set(perkItemMeta, perkProfile);
        } catch (NoSuchFieldException |IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }

        perkItemMeta.setDisplayName(color("&e&l- - - Perk points - - -"));
        perkItemMeta.setLore(Arrays.asList(
                color("&8-----------------------"),
                color("&dPerk points left: &b" + SkillManager.getPerkpoints(player)),
                color("&dExperience: &e" + SkillManager.getExperience(player) + "&6/&e" + SkillManager.getNextPerkpointExp(player)),
                color("&8-----------------------")
        ));

        perkItem.setItemMeta(perkItemMeta);

        ItemStack infoItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta infoItemMeta = (SkullMeta) infoItem.getItemMeta();

        GameProfile infoProfile = new GameProfile(UUID.randomUUID(), null);
        infoProfile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmFkYzA0OGE3Y2U3OGY3ZGFkNzJhMDdkYTI3ZDg1YzA5MTY4ODFlNTUyMmVlZWQxZTNkYWYyMTdhMzhjMWEifX19"));
        Field infoField;

        try {
            infoField = infoItemMeta.getClass().getDeclaredField("profile");
            infoField.setAccessible(true);
            infoField.set(infoItemMeta, infoProfile);
        } catch (NoSuchFieldException |IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }

        infoItemMeta.setDisplayName(color("&9&lINFO"));
        infoItemMeta.setLore(Arrays.asList(
                color("&8-----------------------"),
                color("&8Left click - adds a level to the specific skill"),
                color("&8Right click - removes a level from the specific skill "),
                color("&8-----------------------")
        ));


        infoItem.setItemMeta(infoItemMeta);

        ItemStack healthIcon = new ItemStack(Material.RED_DYE);
        ItemMeta healthMeta = healthIcon.getItemMeta();

        healthMeta.setDisplayName(color("&c&lHEALTH"));
        healthMeta.setLore(Arrays.asList(
                color("&8-----------------------"),
                color("&dLevel: &b" + SkillManager.getLevel(player, SkillTypes.HEALTH) + "/" + ConfigManager.getMaxLevel()),
                color("&8&oThis skill increases numbers of hearts"),
                color("&8-----------------------")
        ));

        healthIcon.setItemMeta(healthMeta);

        ItemStack resistanceIcon = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta resistanceMeta = resistanceIcon.getItemMeta();

        resistanceMeta.setDisplayName(color("&5&lRESISTANCE"));
        resistanceMeta.setLore(Arrays.asList(
                color("&8-----------------------"),
                color("&dLevel: &b" + SkillManager.getLevel(player, SkillTypes.RESISTANCE) + "/" + ConfigManager.getMaxLevel()),
                color("&8&oThis skill decreases incoming damage"),
                color("&8-----------------------")
        ));

        resistanceMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        resistanceIcon.setItemMeta(resistanceMeta);

        ItemStack strenghtIcon = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta strenghtMeta = strenghtIcon.getItemMeta();

        strenghtMeta.setDisplayName(color("&9&lSTRENGHT"));
        strenghtMeta.setLore(Arrays.asList(
                color("&8-----------------------"),
                color("&dLevel: &b" + SkillManager.getLevel(player, SkillTypes.STRENGTH) + "/" + ConfigManager.getMaxLevel()),
                color("&8&oThis skill increases the damage you deal"),
                color("&8-----------------------")
        ));

        strenghtMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        strenghtIcon.setItemMeta(strenghtMeta);

        ItemStack agilityIcon = new ItemStack(Material.FEATHER);
        ItemMeta agilityMeta = agilityIcon.getItemMeta();

        agilityMeta.setDisplayName(color("&f&lAGILITY"));
        agilityMeta.setLore(Arrays.asList(
                color("&8-----------------------"),
                color("&dLevel: &b" + SkillManager.getLevel(player, SkillTypes.AGILITY) + "/" + ConfigManager.getMaxLevel()),
                color("&8&oThis skill increases movement speed"),
                color("&8-----------------------")
        ));

        agilityIcon.setItemMeta(agilityMeta);

        ItemStack luckIcon = new ItemStack(Material.ENDER_EYE);
        ItemMeta luckMeta = luckIcon.getItemMeta();

        luckMeta.setDisplayName(color("&d&lLUCK"));
        luckMeta.setLore(Arrays.asList(
                color("&8-----------------------"),
                color("&dLevel: &b" + SkillManager.getLevel(player, SkillTypes.LUCK) + "/" + ConfigManager.getMaxLevel()),
                color("&8&oThis skill increases the chance of "),
                color("&8&oan extra perk point when you level up"),
                color("&8-----------------------")
        ));

        luckIcon.setItemMeta(luckMeta);

        ItemStack emptySlot = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = emptySlot.getItemMeta();

        emptyMeta.setDisplayName("-");

        emptySlot.setItemMeta(emptyMeta);

        skillgui.setItem(0, infoItem);
        skillgui.setItem(4, perkItem);
        skillgui.setItem(11, healthIcon);
        skillgui.setItem(12, resistanceIcon);
        skillgui.setItem(13, strenghtIcon);
        skillgui.setItem(14, agilityIcon);
        skillgui.setItem(15, luckIcon);

        int[] emptySlotArray = new int[] {1, 2, 3, 5, 6, 7, 8, 9, 10, 16, 17};

        for (int slotID: emptySlotArray) {

            skillgui.setItem(slotID, emptySlot);
        }

        player.openInventory(skillgui);
        return true;
    }
}
