package levelsystem.levelsystem.Listeners;

import levelsystem.levelsystem.Config.ConfigManager;
import levelsystem.levelsystem.SkillGUI;
import levelsystem.levelsystem.SkillTypes;
import levelsystem.levelsystem.Skills.SkillManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {

    private String color(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }

    @EventHandler
    private void OnInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if(inventory != null) {
            if(event.getView().getTitle().equals(color("&bSkill menu")) && event.getCurrentItem() != null) {
                event.setCancelled(true);

                Player player = (Player) event.getWhoClicked();

                if(event.getClick() == ClickType.LEFT) {
                    if(SkillManager.getPerkpoints(player) > 0) {
                        switch (event.getRawSlot()) {
                            case 11:
                                if(SkillManager.getLevel(player, SkillTypes.HEALTH) < ConfigManager.getMaxLevel()) {
                                    SkillManager.addLevel(player, SkillTypes.HEALTH);
                                    SkillManager.removePerkpoints(player);
                                }
                                break;
                            case 12:
                                if(SkillManager.getLevel(player, SkillTypes.RESISTANCE) < ConfigManager.getMaxLevel()) {
                                    SkillManager.addLevel(player, SkillTypes.RESISTANCE);
                                    SkillManager.removePerkpoints(player);
                                }
                                break;
                            case 13:
                                if(SkillManager.getLevel(player, SkillTypes.STRENGTH) < ConfigManager.getMaxLevel()) {
                                    SkillManager.addLevel(player, SkillTypes.STRENGTH);
                                    SkillManager.removePerkpoints(player);
                                }
                                break;
                            case 14:
                                if(SkillManager.getLevel(player, SkillTypes.AGILITY) < ConfigManager.getMaxLevel()) {
                                    SkillManager.addLevel(player, SkillTypes.AGILITY);
                                    SkillManager.removePerkpoints(player);
                                }
                                break;
                            case 15:
                                if(SkillManager.getLevel(player, SkillTypes.LUCK) < ConfigManager.getMaxLevel()) {
                                    SkillManager.addLevel(player, SkillTypes.LUCK);
                                    SkillManager.removePerkpoints(player);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
                else if (event.getClick() == ClickType.RIGHT) {
                    switch (event.getRawSlot()) {
                        case 11:
                            if(SkillManager.getLevel(player, SkillTypes.HEALTH) > 0) {
                                SkillManager.removeLevel(player, SkillTypes.HEALTH);
                                SkillManager.addPerkpoints(player);
                            }
                            break;
                        case 12:
                            if(SkillManager.getLevel(player, SkillTypes.RESISTANCE) > 0) {
                                SkillManager.removeLevel(player, SkillTypes.RESISTANCE);
                                SkillManager.addPerkpoints(player);
                            }
                            break;
                        case 13:
                            if(SkillManager.getLevel(player, SkillTypes.STRENGTH) > 0) {
                                SkillManager.removeLevel(player, SkillTypes.STRENGTH);
                                SkillManager.addPerkpoints(player);
                            }
                            break;
                        case 14:
                            if(SkillManager.getLevel(player, SkillTypes.AGILITY) > 0) {
                                SkillManager.removeLevel(player, SkillTypes.AGILITY);
                                SkillManager.addPerkpoints(player);
                            }
                            break;
                        case 15:
                            if(SkillManager.getLevel(player, SkillTypes.LUCK) > 0) {
                                SkillManager.removeLevel(player, SkillTypes.LUCK);
                                SkillManager.addPerkpoints(player);
                            }
                            break;
                        default:
                            break;
                    }
                }

                SkillGUI.openInventory(player);
            }
        }
    }

    @EventHandler
    private void OnInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if(event.getView().getTitle().equals(color("&bSkill menu"))) {
            SkillManager.UpdateSkills(player);
        }
    }
}
