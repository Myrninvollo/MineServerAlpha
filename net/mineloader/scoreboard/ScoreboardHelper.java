package net.mineloader.scoreboard;

import java.util.Arrays;
import java.util.List;

import net.minecraft.server.MinecraftServer;
import net.mineloader.util.MineName;

public class ScoreboardHelper {
	
	public static IScoreboardList fromList(List<MineName> list , String name){
		ScoreboardOrderedList ord = new ScoreboardOrderedList(name);
		for (MineName cur : list){
			ord.addEntry(cur);
		}
		return ord;
	}
	
	public static IScoreboardList constructList(String name , MineName... list){
		ScoreboardOrderedList ord = new ScoreboardOrderedList(name);
		for (MineName cur : list){
			ord.addEntry(cur);
		}
		return ord;
	}
	
	public static IScoreboardList fromGenericList(List<String> list , String name){
		ScoreboardOrderedList ord = new ScoreboardOrderedList(name);
		for (String cur : list){
			ord.addEntry(new MineName("white",cur));
		}
		return ord;
	}
	
	public static IScoreboardList playerList(String name){
		return fromGenericList(Arrays.asList(MinecraftServer.getServer().getAllUsernames()),name);
	}
}
