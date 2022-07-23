package levelsystem.levelsystem.Listeners;

import levelsystem.levelsystem.Config.ConfigManager;
import levelsystem.levelsystem.SkillTypes;
import levelsystem.levelsystem.Skills.SkillManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import java.util.Random;

public class ExpGainListener implements Listener {

    private static String color(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }

    @EventHandler
    private void OnExpGain(PlayerExpChangeEvent event) {
        int amount = event.getAmount();
        Player player = event.getPlayer();

        SkillManager.addExperience(player, (Math.round(amount * 10) / 10) * ConfigManager.getExpMod());
    }

    @EventHandler
    private void onLevelChange(PlayerLevelChangeEvent event) {
        Player player = event.getPlayer();
        Random random = new Random();

        if(random.nextFloat() < (0.02 * SkillManager.getLevel(player, SkillTypes.LUCK))) {
            SkillManager.addPerkpoints(player);

            player.sendMessage(color("&6&lYou got a perk point from leveling up"));
        }
    }
}
