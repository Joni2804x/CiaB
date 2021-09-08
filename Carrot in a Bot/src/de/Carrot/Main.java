package de.Carrot;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import de.CarrotListeners.CommandListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class Main {
	
	public static ShardManager shardMan;

	public static void main(String[] args) 
	{
		try {
			new Main();
		} catch (LoginException | IllegalArgumentException e) {
			
			e.printStackTrace();
		}
	}
	public Main() throws LoginException, IllegalArgumentException
	{
		DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault("");
		
		builder.setStatus(OnlineStatus.ONLINE);
		
		builder.addEventListeners(new CommandListener());
		
		shardMan = builder.build();
		
		System.out.println("Bot Online");
		
		shutdown();
	}

	private static void shutdown()
	{
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
		if(input.equalsIgnoreCase("exit"))
		{
			shardMan.setStatus(OnlineStatus.OFFLINE);
			shardMan.shutdown();
			System.out.println("Bot offline");
		}
		
		scanner.close();
	}

}
