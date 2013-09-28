package com.huskehhh.code.commands;

import com.huskehhh.code.Config;
import com.huskehhh.code.HuskyIRC;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {

        String[] line = event.getMessage().split(" ");

        if (line[0].equalsIgnoreCase("!update")) {
            if (line.length == 1) {
                if (event.getUser().getNick().equals("Huskehhh")) {
                    if (isUpdate()) {
                        try {
                            Runtime.getRuntime().exec("sh update.sh");
                            HuskyIRC.end();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public boolean isUpdate() {
        String u = "https://raw.github.com/Huskehhh/HuskyIRC/master/VERSION";
        try {
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            if (!in.readLine().equals(Config.version)) {
                return true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


}
