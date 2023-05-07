package me.sfclog.randomsurvival.board;

import org.bukkit.entity.Player;
import java.util.HashSet;

public class UserBoardManage {

    public static HashSet<UserBoard> listboard = new HashSet<>();


    public static void add(Player p) {
        UserBoard board = new UserBoard(p);
        if(board != null) {
            listboard.add(board);
        }
    }
    public static void update() {
        listboard.removeIf(b -> !b.update(BoardInfo.getTitle(),BoardInfo.getInfo(b.getPlayer())));
    }

}
