package net.savagedev.fancychat;

import net.savagedev.fancychat.events.AsyncFancyChatEvent;
import net.savagedev.fancychat.listeners.ChatListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class FancyChat implements FancyChatApi {
    private static final Logger LOGGER = Logger.getLogger("FancyChatApi");

    public static FancyChatApi newInstance(JavaPlugin plugin) {
        return new FancyChat(plugin);
    }

    private final JavaPlugin plugin;

    public FancyChat(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        this.plugin.getServer().getPluginManager().registerEvents(new ChatListener(), this.plugin);
        LOGGER.log(Level.INFO, "Registered listeners for " + this.plugin.getName() + ".");
    }

    @Override
    public void shutdown() {
        AsyncFancyChatEvent.getHandlerList().unregister(this.plugin);
        LOGGER.log(Level.INFO, "Unregistered listeners for " + this.plugin.getName() + ".");
    }
}
