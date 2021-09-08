package de.Carrot;

import java.util.concurrent.ConcurrentHashMap;

import de.CarrotCommands.InitializeCommand;
import de.CarrotCommands.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandManager 
{
	public ConcurrentHashMap<String, ServerCommand> commands;
	
	public CommandManager()
	{
		this.commands = new ConcurrentHashMap<>();
		
		this.commands.put("initialize", new InitializeCommand());
	
	}
	
	public boolean perform(String s, Message m, Member p, TextChannel c)
	{
			ServerCommand cmd;
			if((cmd = this.commands.get(s.toLowerCase())) != null)
			{
				cmd.performCommand(p, c, m);
				return true;
			}
			
		return false;
	}
}
