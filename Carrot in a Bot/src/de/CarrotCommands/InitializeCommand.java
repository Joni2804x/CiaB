package de.CarrotCommands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class InitializeCommand implements ServerCommand
{
	public static Message rmessage;
	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
		EmbedBuilder eb = new EmbedBuilder();
		
		eb.setTitle("🥕Carrot in a Box📦");
		
		eb.setColor(Color.GREEN);
		eb.addField("Rules:", "React with ❓ to see the information on the game", false);
		eb.addField("Start:", "React with ✅ to start the game (2 players requierd)", false);
		
		rmessage = channel.sendMessage(eb.build()).complete();
		
		rmessage.addReaction("❓").queue();
		rmessage.addReaction("✅").queue();
		
	}

}
