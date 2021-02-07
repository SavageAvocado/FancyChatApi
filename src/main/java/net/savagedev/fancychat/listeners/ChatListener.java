package net.savagedev.fancychat.listeners;

import net.md_5.bungee.api.chat.TextComponent;
import net.savagedev.fancychat.FancyChat;
import net.savagedev.fancychat.events.AsyncFancyChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void on(AsyncPlayerChatEvent e) {
        final AsyncFancyChatEvent event = new AsyncFancyChatEvent(e);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            for (TextComponent component : event.getFormat()) {
                component.setText(String.format(component.getText(), e.getPlayer().getDisplayName(), e.getMessage()));
            }
            for (Player player : e.getRecipients()) {
                player.spigot().sendMessage(event.getFormat().toArray(new TextComponent[0]));
            }
        }
        e.setCancelled(true);
    }
}
