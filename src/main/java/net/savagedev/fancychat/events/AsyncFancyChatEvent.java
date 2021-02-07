package net.savagedev.fancychat.events;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AsyncFancyChatEvent extends Event implements Cancellable {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    private final List<TextComponent> format = new ArrayList<>();

    private final AsyncPlayerChatEvent event;

    private boolean cancelled;
    private String message;

    public AsyncFancyChatEvent(AsyncPlayerChatEvent event) {
        super(true);
        this.format.add(new TextComponent(event.getFormat()));
        this.cancelled = event.isCancelled();
        this.message = event.getMessage();
        this.event = event;
    }

    public void setFormat(TextComponent format, TextComponent... extras) {
        this.format.clear();
        this.format.add(format);
        this.format.addAll(Arrays.asList(extras));
    }

    public void setFormat(TextComponent format, String... extras) {
        this.format.clear();
        this.format.add(format);
        this.format.addAll(Arrays.stream(extras).map(TextComponent::new).collect(Collectors.toList()));
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Player getPlayer() {
        return this.event.getPlayer();
    }

    public List<TextComponent> getFormat() {
        return this.format;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }
}
