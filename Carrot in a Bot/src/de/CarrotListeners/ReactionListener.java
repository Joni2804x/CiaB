package de.CarrotListeners;

import java.awt.Color;

import de.Carrot.CommandManager;
import de.CarrotCommands.InitializeCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter
{
	
	public void onMessageReactionAdd(MessageReactionAddEvent event)
	{
		new InitializeCommand();
		Message message = InitializeCommand.rmessage;		
		
		if(event.getReactionEmote().getEmoji().equals("❓") && event.getMessageId().equals(message.getId()))
		{
			EmbedBuilder eb = new EmbedBuilder();
			eb.setTitle("🥕Explanation📦");
			eb.setColor(Color.red);
			eb.addField("What is Carrot in a Box?", "Carrot in a Box is a game about bluff."
					+ " It was invented by, and first appeared on, the british comedy quiz"
					+ " show [8 out of 10 Cats does Countdown]. The game has 2 player who"
					+ " are each given a box. One of those boxes contains a carrot."
					+ " Player 1 is allowed to look inside his box, Player 2 is not"
					+ " allowed to look inside his box. However, Player 2 can decide if he"
					+ " wants to swap his box with Player 1, Player 1 can't decide if he wants to swap boxes. Both Players have to talk"
					+ " to eachother, and Player 1 has to convince Player 2 to"
					+ " either swap the boxes or to keep them the way they are"
					+ " depending on if the Carrot is inside his box or not."
					+ " (You can use either Voice Channels or Text Channels for that)"
					+ " The goal is to end up with the Box containing the carrot."
					+ " Whoever then has the Carrot, wins!", false);
			eb.addBlankField(false);
			eb.addField("🕊️", "In Memory of Sean Lock 1963 - 2021", false);
			
			event.getUser().openPrivateChannel().complete().sendMessage(eb.build()).complete();
		}
		else if(event.getReactionEmote().getEmoji().equals("✅") && event.getMessageId().equals(message.getId()))
		{
			
		}
	}
}
