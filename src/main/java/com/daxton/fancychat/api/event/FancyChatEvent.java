package com.daxton.fancychat.api.event;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FancyChatEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private boolean cancel = false;

	private TextComponent chatMessage;

	private final Player player;

	public FancyChatEvent(Player player, TextComponent chatMessage){
		this.player = player;
		this.chatMessage = chatMessage;
	}

	public TextComponent getChatMessage() {
		return chatMessage;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}

}
