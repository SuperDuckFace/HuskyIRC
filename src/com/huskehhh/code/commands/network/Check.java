package com.huskehhh.code.commands.network;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SuppressWarnings("rawtypes")
public class Check extends ListenerAdapter {

    public void onMessage(MessageEvent event) {

        String[] line = event.getMessage().split(" ");

        if (line[0].equalsIgnoreCase("!check")) {

            if (line.length > 1) {

                try {

                    InetAddress checkme = InetAddress.getByName(line[1]);
                    event.respond("Host name : " + checkme.getCanonicalHostName());

                } catch (UnknownHostException e) {
                    event.respond("Either you're retarded or this guy has hacks.");
                }

            } else {
                event.respond("Usage: !check <ip>");
            }

        }

    }

}