package com.huskehhh.code.commands.oresomecraft;

import com.huskehhh.code.config.Config;
import com.huskehhh.database.mysql.MySQL;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rap extends ListenerAdapter {

    MySQL mysql = new MySQL(Config.Ohostname,
            Config.Oport, Config.Odatabase,
            Config.Ouser, Config.Opassword);

    public void onMessage(MessageEvent event) throws Exception {
        String[] line = event.getMessage().split(" ");

        if (line[0].equalsIgnoreCase("!rap")) {

            if (line.length > 2) {
                String player = line[1];
                String server = line[2];

                String smp = "SELECT * FROM `mb_ban_records_smp` WHERE `banned`='" + player + "';";
                String battles = "SELECT * FROM `mb_ban_records_battles` WHERE `banned`='" + player + "';";
                String onslaught = "SELECT * FROM `mb_ban_records_onslaught` WHERE `banned`='" + player + "';";

                ResultSet rs = null;

                if (server.equalsIgnoreCase("smp")) {
                    rs = mysql.querySQL(smp);
                } else if (server.equalsIgnoreCase("battles")) {
                    rs = mysql.querySQL(battles);
                } else if (server.equalsIgnoreCase("onslaught")) {
                    rs = mysql.querySQL(onslaught);
                }

                if (rs != null) {

                    try {

                        event.respond(player + " has been banned previously from " + server + " for:");

                        while (rs.next()) {
                            event.respond(rs.getString("ban_reason") + " (" + rs.getString("banned_by") + ").");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

            } else {
                event.respond("Usage: !rap <player> <smp|onslaught|battles>");
            }

        }

    }

}