package levelsystem.levelsystem.Listeners;

import levelsystem.levelsystem.SkillTypes;
import levelsystem.levelsystem.Skills.SkillManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    private void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(!SkillManager.hasAccount(player)) {
            SkillManager.setupProfile(player);
        }
        else {
            SkillManager.UpdateSkills(player);
        }
    }
}
