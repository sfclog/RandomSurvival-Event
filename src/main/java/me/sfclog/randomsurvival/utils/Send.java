package me.sfclog.randomsurvival.utils;


import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Send {


	public static void send(Player p,String s,Sound so) {
		 p.sendMessage(Color.tran("&8(&#ff0000&lR&#ff9900&lA&#99ff00&lN&#006699&lD&#3c009b&lO&#9400d3&lM&8) "+ s));
		 p.playSound(p.getLocation(), so, 50, 1);
	}

	public static void send_center_global(String s) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if (p != null) {
				CenterMSG.sendCenteredMessage(p, s);
			}
		}
	}

    public static void sendglobal(String s, Sound sound) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p != null) {
				send(p,s,sound);
			}

		}
    }
}
