package com.huskehhh.code.commands.core;

import com.huskehhh.code.HuskyIRC;
import com.huskehhh.code.auth.AuthCheck;
import com.huskehhh.code.config.Config;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Join extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws Exception {

        if (AuthCheck.authCheck(event.getUser().getNick())) {

            if (event.getMessage().split(" ").length > 1) {

                if (event.getMessage().equals("!join")) {

                    event.respond("Please specify a channel to join.");
                }

                String channelarg = event.getMessage().split(" ")[1];
                if (event.getMessage().startsWith("!join ") && event.getMessage().contains(channelarg)) {

                    String[] admin = Config.admins;

                    for (int i = 0; i <= admin.length; i++) {

                        if (admin[i] == null) return;

                        if (admin[i].replaceAll(" ", "").equals(event.getUser().getNick())) {
                            HuskyIRC.bot.sendRawLineNow("join" + " " + channelarg);
                            event.respond("Attempted to join channel " + channelarg);
                        }

                    }

                }

            }

        }

    }

}
