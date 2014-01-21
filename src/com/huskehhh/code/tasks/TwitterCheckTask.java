package com.huskehhh.code.tasks;

import com.huskehhh.code.HuskyIRC;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.*;
import java.util.ListIterator;
import java.util.TimerTask;

public class TwitterCheckTask extends TimerTask {

    @Override
    public void run() {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        int tweetCount = 1;
        long userId = 763736701; //OresomeCraft Twitter User ID

        ResponseList tweet = null;

        try {
            tweet = twitter.getUserTimeline(userId, new Paging(1, tweetCount));
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        ListIterator li = tweet.listIterator();

        while (li.hasNext()) {
            HuskyIRC.bot.sendMessage("#oresomecraft", Color.GREEN + "OresomeCraft Twitter: " + li.next().toString());
        }


        new TwitterCheck();
    }

}
