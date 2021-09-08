package de.CarrotListeners;

import de.Carrot.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter
{
	public void onMessageReceived(MessageReceivedEvent event)
	{
		String message = event.getMessage().getContentDisplay().toLowerCase();
		
		String[] args = message.substring(2).split("");
		
		CommandManager obj = new CommandManager();
		
		if(obj.commands.containsKey(args[0]))
		{
			obj.perform(args[0], event.getMessage(), event.getMember(), event.getTextChannel());
		}
		else
		{
			event.getChannel().sendMessage("Unbekannter Befehl!").queue();
		}
	}
}
