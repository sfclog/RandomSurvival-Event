package me.sfclog.randomsurvival.board;

import fr.mrmicky.fastboard.FastBoard;
import me.sfclog.randomsurvival.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.util.List;

public class UserBoard {

    public Player p;
    public FastBoard fast;

    public UserBoard(Player p) {
        this.p = p;
        this.fast = new FastBoard(p);
    }

    public boolean update(String title , List<String> line) {
        if(p != null && Bukkit.getPlayer(p.getName()) != null) {
            this.fast.updateTitle(title);
            this.fast.updateLines(line);
            update_belowhealth();
            return true;
        }
        return false;
    }

    public Player getPlayer() {
        return p;
    }

    public void update_belowhealth() {
        Objective health = Main.create_obj(Main.board,"health", "dummy");
        Score score = health.getScore((OfflinePlayer)p);
        Double s = Double.valueOf(p.getHealth());
        score.setScore(s.intValue());
        p.setScoreboard(Main.board);
    }
}
