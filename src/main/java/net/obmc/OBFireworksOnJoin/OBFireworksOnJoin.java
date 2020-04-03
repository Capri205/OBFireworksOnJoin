package net.obmc.OBFireworksOnJoin;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class OBFireworksOnJoin extends JavaPlugin implements Listener
{
	
	Logger log = Logger.getLogger("Minecraft");

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
		log.log(Level.INFO, "[OBFireworksOnJoin] Plugin Version " + this.getDescription().getVersion() + " activated");

    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent pje) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this, (Runnable)new Runnable() {
            public void run() {
                final Firework f = (Firework)pje.getPlayer().getWorld().spawn(pje.getPlayer().getLocation(), (Class)Firework.class);
                final FireworkMeta fm = f.getFireworkMeta();
                fm.addEffect(FireworkEffect.builder().flicker(false).trail(true).with(FireworkEffect.Type.BALL).with(FireworkEffect.Type.BALL_LARGE).with(FireworkEffect.Type.STAR).withColor(Color.ORANGE).withColor(Color.YELLOW).withFade(Color.PURPLE).withFade(Color.RED).build());
                fm.setPower(2);
                f.setFireworkMeta(fm);
            }
        }, 20L);
    }
    
	@Override
	public void onDisable() {
		Bukkit.getLogger().info("[OBFireworksOnJoin] Plugin unloaded");
	}
}
