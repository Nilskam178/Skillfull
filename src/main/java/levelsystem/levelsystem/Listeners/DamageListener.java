package levelsystem.levelsystem.Listeners;

import levelsystem.levelsystem.SkillTypes;
import levelsystem.levelsystem.Skills.SkillManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class DamageListener implements Listener {

    @EventHandler
    private void OnDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = ((Player) event.getEntity());
            event.setDamage(event.getFinalDamage() * (1 - (0.05 * SkillManager.getLevel(player, SkillTypes.RESISTANCE))));
        }
    }

    @EventHandler
    private void OnPlayerDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            event.setDamage(event.getFinalDamage() * (1 + (0.05 * SkillManager.getLevel(player, SkillTypes.STRENGTH))));
        }
    }
}
