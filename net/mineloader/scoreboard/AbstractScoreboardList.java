package net.mineloader.scoreboard;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.mineloader.network.PacketSender;
import net.mineloader.util.MineEntry;
import net.mineloader.util.MineName;

public class AbstractScoreboardList implements IScoreboardList{
	protected List<MineEntry<Integer>> scoreboards;
	protected ScoreObjective obj;
	
	public AbstractScoreboardList(String name){
		scoreboards = new ArrayList<MineEntry<Integer>>();
		obj = new ScoreObjective(getScoreboard(),name,IScoreObjectiveCriteria.DUMMY);
	}
	
	protected List<Score> getScores(){
		List<Score> list = new ArrayList<Score>();
		for (MineEntry<Integer> entry : scoreboards){
			Score sc = new Score(getScoreboard(),obj,entry.getName());
			sc.setScorePoints(entry.getValue());
			list.add(sc);
		}
		return list;
	}
	
	public void sendScores(EntityPlayerMP player){
		for (Score score : this.getScores()){
			PacketSender.sendPacket(new S3CPacketUpdateScore(score) , player);
		}
	}
	
	public void broadcastScores(){
		for (Score score : this.getScores()){
			PacketSender.broadcastPacket(new S3CPacketUpdateScore(score));
		}
	}
	
	public void sendObjective(EntityPlayerMP player){
		PacketSender.sendPacket(new S3BPacketScoreboardObjective(obj,0) , player);
	}
	
	public void broadcastObjective(){
		PacketSender.broadcastPacket(new S3BPacketScoreboardObjective(obj,0));
	}
	
	public ScoreObjective getObjective(){
		return obj;
	}
	
	protected static Scoreboard getScoreboard(){
		return MinecraftServer.getServer().worldServers[0].getScoreboard();
	}

	
	public void removeEntry(MineName name){
		scoreboards.remove(scoreboards.indexOf(new MineEntry<Integer>(name,0)));
	}

	@Override
	public List<MineEntry<Integer>> getEntries() {
		// TODO Auto-generated method stub
		return scoreboards;
	}
}
