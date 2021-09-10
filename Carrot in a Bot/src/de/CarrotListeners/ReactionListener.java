package de.CarrotListeners;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import de.Carrot.CommandManager;
import de.CarrotCommands.InitializeCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.internal.handle.EventCache.Type;

public class ReactionListener extends ListenerAdapter
{
	
	static Message emessage;
	static Message nmessage;
	static Message pm1;
	static Message pm2;
	static Message pm3;
	static Message pm4;
	static User player1;
	static User player2;
	static TextChannel endChannel;
	static boolean swapped;
	static int t;
	static boolean carrot;
	static boolean help = false;
	
	public void onMessageReactionAdd(MessageReactionAddEvent event)
	{
		if(event.isFromType(ChannelType.TEXT))
		{
		endChannel = event.getTextChannel();
		}
		
		new InitializeCommand();
		Message message = InitializeCommand.rmessage;		
		
		int i = 0;
		
		if(event.getReactionEmote().getEmoji().equals("❓") && event.getMessageId().equals(message.getId()) && !event.getUser().isBot())
		{
			explanation(event);
		}
		else if(event.getReactionEmote().getEmoji().equals("✅") && event.getMessageId().equals(message.getId()))
		{
			String Id = event.getMessageId();
			RestAction<Message> messager = event.getChannel().retrieveMessageById(Id);
			Message ma = messager.complete();
			
				for(MessageReaction reaction : ma.getReactions())
				{
					if(reaction.getReactionEmote().getEmoji().equals("✅"))
					{
						//System.out.println("debug");
						List<User> participants = reaction.retrieveUsers().complete();
						participants.remove(message.getJDA().getSelfUser());
						
						
					
					if(participants.size() >= 2)
					{				
						message.clearReactions("✅").queue();;
						
						Random rand = new Random();
						t = rand.nextInt(1);
						carrot = new Random().nextBoolean();
						boolean player = new Random().nextBoolean();
						
						if(player)
						{
						player1 = participants.get(0);
						player2 = participants.get(1);
						}
						
						if(!player)
						{
						player1 = participants.get(1);
					    player2 = participants.get(0);
						}
						
						nmessage = event.getChannel().sendMessage("A game is currently active!"
								+ "\n\nCurrent players: " + player1 + " and " + player2).complete();
					
					EmbedBuilder bl = new EmbedBuilder();
					bl.setTitle("You are Player 1!");
					
					if(carrot)
					{
						bl.addField("Your box contains the carrot!", "🥕", false);
						bl.setColor(Color.green);
					}
					else
					{
						bl.addField("Your box does not contain the carrot.", "🚫", false);
						bl.setColor(Color.red);
					}
					
					EmbedBuilder bs = new EmbedBuilder();
					bs.setTitle("You are Player 2!");
					bs.setColor(Color.blue);
					bs.addField("React with to 🔀 swap the boxes", "", false);
					bs.addField("React with 🚫 to not swap the boxes", "Remember to talk with Player 1 first!", false);
					
					if(t == 0)
					{
						pm1 = player1.openPrivateChannel().complete().sendMessage(bl.build()).complete();
						pm2 = player2.openPrivateChannel().complete().sendMessage(bs.build()).complete();
						
						pm2.addReaction("🔀").queue();
						pm2.addReaction("🚫").queue();
					}
					else if(t == 1)
					{
						pm3 = player1.openPrivateChannel().complete().sendMessage(bs.build()).complete();
						pm4 = player2.openPrivateChannel().complete().sendMessage(bl.build()).complete();
						
						pm3.addReaction("🔀").queue();
						pm3.addReaction("🚫").queue();
					}
					else
					{
						System.out.println("alles gabutt hier");
					}
					}
					}
				}
			
			
		}
		
		if(!event.getUser().isBot())
		{
			
		if(event.getReactionEmote().getEmoji().equals("🗑️") && event.getMessageId().equals(message.getId()) && event.getUser() == InitializeCommand.Author)
		{
			message.delete().queue();
			Message thxmessage = event.getChannel().sendMessage("Thank you for playing!").complete();
			thxmessage.delete().queueAfter(15, TimeUnit.SECONDS);
		}
		
		if(help == true)
		{
			
			if(event.getReactionEmote().getEmoji().equals("🗑️") && event.getMessageId().equals(emessage.getId()))
			{
				emessage.delete().queue();
				help = false;
			}
		
		}
		
		if(event.getReactionEmote().getEmoji().equals("🚫") && event.getMessageId().equals(pm2.getId()))
		{
			swapped = false;
			endStay();
		}
		else if(event.getReactionEmote().getEmoji().equals("🚫") && event.getMessageId().equals(pm3.getId()))
		{
			swapped = false;
			endStay();
		}
		
		if(event.getReactionEmote().getEmoji().equals("🔀") && event.getMessageId().equals(pm2.getId()))
		{
			swapped = true;
			endSwap();
		}
		else if(event.getReactionEmote().getEmoji().equals("🔀") && event.getMessageId().equals(pm3.getId()))
		{
			swapped = true;
			endSwap();
		}
		
		}
	}

	private void explanation(MessageReactionAddEvent event) 
	{
		List<String> quotes = Arrays.asList(
				 "If you look me up in a dictionairy, you will get a 4 letter word with a c in it, a u in it, and a t in it. And that word is of course, cute."
				,"If I could reanimate something, I would reanimate all the leaders of the Nazi Party, all the top Nazis. I would put them on an Island and Film it, and call it Nazi Island."
				,"I have worked out that I am virtually Chinese, because everything I own is from China."
				,"I want my orbituary to say: Nooooooo, Whyyyyyyyy, Nooooooo, Aahhhhhhh. You can't write tears."
				,"Salad cream is horrible, like albino ketchup."
				,"Interesting fact: a shark will only attack you if you’re wet."
				,"A bit of advice: never read a pop-up book about giraffes."
				,"when michael jackson died i wonder if his life flashed before him and if it did, i wonder if he thought 'who's that little black kid singing my songs?!"
				,"Some people say the glass is half empty or the glass is half full, but for me thats irrelevant, because I’m having another drink."
				);
		
		Random rand = new Random();
		int r = new Random().nextInt(quotes.size());
		String quote = quotes.get(r);
		
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("🥕Explanation📦");
		eb.setColor(Color.BLUE);
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
				+ " Whoever then has the Carrot, wins! (The game ends once Player 2 has made a decision)"
				+ "", false);
		eb.addBlankField(false);
		eb.addField("🕊️", "In Memory of Sean Lock 1963 - 2021", false);
		eb.addField("", quote + "   - Sean Lock", false);
		
		emessage = event.getUser().openPrivateChannel().complete().sendMessage(eb.build()).complete();
		emessage.addReaction("🗑️").queue();
		help = true;
	}

	private void endSwap() 
	{
		nmessage.delete().queue();
		
		EmbedBuilder endSwap = new EmbedBuilder();
		
		if(t == 0)
		{
			pm1.delete().queue();
			pm2.delete().queue();
		}
		else if(t == 1)
		{
			pm3.delete().queue();
			pm4.delete().queue();
		}
		
		if(carrot)
		{
			endSwap.setTitle("The results are in!");
			endSwap.setColor(Color.blue);
			endSwap.addField("Player 1's box does not contain the Carrot!", "", false);
			endSwap.addField("Player 2's box does contain the Carrot!", "", false);
			endSwap.addField("Player 2 Wins!", "", false);
		}
		else if(!carrot)
		{
			endSwap.setTitle("The results are in!");
			endSwap.setColor(Color.blue);
			endSwap.addField("Player 1's box does contain the Carrot", "", false);
			endSwap.addField("Player 2's box does not contain the Carrot", "", false);
			endSwap.addField("Player 1 Wins!", "", false);
		}
		
		Message end1 = endChannel.sendMessage(endSwap.build()).complete();
		InitializeCommand.rmessage.addReaction("✅").queue();
		end1.delete().queueAfter(1, TimeUnit.MINUTES);
		
	}

	private void endStay() 
	{
		nmessage.delete().queue();
		
		EmbedBuilder endStay = new EmbedBuilder();
		
		if(t == 0)
		{
			pm1.delete().queue();
			pm2.delete().queue();
		}
		else if(t == 1)
		{
			pm3.delete().queue();
			pm4.delete().queue();
		}
		
		if(carrot)
		{
			endStay.setTitle("The results are in!");
			endStay.setColor(Color.blue);
			endStay.addField("Player 1's box does contain the Carrot!", "", false);
			endStay.addField("Player 2's box does not contain the Carrot!", "", false);
			endStay.addField("Player 1 Wins!", "", false);
		}
		else if(!carrot)
		{
			endStay.setTitle("The results are in!");
			endStay.setColor(Color.blue);
			endStay.addField("Player 1's box does not contain the Carrot!", "", false);
			endStay.addField("Player 2's box does contain the Carrot!", "", false);
			endStay.addField("Player 2 Wins!", "", false);
		}
		
		Message end2 = endChannel.sendMessage(endStay.build()).complete();
		InitializeCommand.rmessage.addReaction("✅").queue();
		end2.delete().queueAfter(1, TimeUnit.MINUTES);
	}

}
