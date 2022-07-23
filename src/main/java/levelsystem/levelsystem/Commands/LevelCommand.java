package levelsystem.levelsystem.Commands;

import levelsystem.levelsystem.SkillGUI;
import levelsystem.levelsystem.Skills.SkillManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class LevelCommand implements CommandExecutor {

    private String color(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 0) {
                SkillGUI.openInventory(player);
            }
            else if(args[0].equals("exp")) {
                player.sendMessage(color("&aYou got &e" + SkillManager.getExperience(player) + " &aexp"));
            }
            else if(args[0].equals("add")) {
                if(player.hasPermission("SkillFull.admin")) {
                    if(args.length == 3) {
                        int amount = Integer.parseInt(args[1]);
                        Player aPlayer = Bukkit.getServer().getPlayer(args[2]);

                        if(aPlayer != null) {
                            SkillManager.addPerkpoints(aPlayer, amount);
                            player.sendMessage(color("&aAdded &b" + amount + "&a perk points to " + aPlayer.getName()));
                        }
                        else error(player);
                    }
                    else error(player);
                }
                else player.sendMessage(color("&You dont have permission to use that command"));
            }
        }

        return false;
    }

    private void error(Player player) {
        player.sendMessage(color("&cAn error occurred. Make sure the command is formatted correctly"));
    }
}
