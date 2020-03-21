package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Player;
import model.Skill;
import utility.ConnectionManager;

public class PlayerDAO
{
	 static List<Player> list=new ArrayList<Player>();
	 
	public List<Player> getAllPlayers() throws Exception 
	{
		Connection con=ConnectionManager.getConnection();
		 Statement stmt=  con.createStatement();
			
			ResultSet rs =stmt.executeQuery("select * from player FULL OUTER JOIN skill  ON  player.skill_id=skill.id");
		  
			while(rs.next())
		    {
				long skillId=rs.getLong(4);
				long playerId=rs.getLong(1);
				String playerName=rs.getString(2);
				String playerCountry=rs.getString(3);
				String skillName=rs.getString(6);
				
				Skill skill=new Skill(skillId,skillName);
				
				Player player =new Player(playerId,playerName,playerCountry,skill);
				
		 		list.add(player);
		
		    }
	
		return list;
		
	}
}