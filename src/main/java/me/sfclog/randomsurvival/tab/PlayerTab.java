package me.sfclog.randomsurvival.tab;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.sfclog.randomsurvival.board.BoardInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerTab {






    public static void update_header_and_footer(Player p) {
        PacketContainer pc =  ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        pc.getChatComponents().write(0, WrappedChatComponent.fromLegacyText(BoardInfo.getHeader()))
                .write(1, WrappedChatComponent.fromLegacyText(BoardInfo.getFooter()));
        try
        {
            ProtocolLibrary.getProtocolManager().sendServerPacket(p, pc);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
