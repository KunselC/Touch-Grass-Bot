package utils;

import listeners.PresenceListener;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;

import java.util.Map;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import utils.KeyValuePair;

public class OnlineTimeChecker {
    private static final Logger logger = Logger.getLogger(OnlineTimeChecker.class.getName());
    private final PresenceListener presenceListener;
    private final long threshold = 60000; // 1 minute in milliseconds

    public OnlineTimeChecker(PresenceListener presenceListener) {
        this.presenceListener = presenceListener;
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkOnlineTimes();
            }
        }, 0, 60000); // Check every minute
    }

    public void checkOnlineTimes() {
        logger.info("Checking online times...");
        ArrayList<KeyValuePair> onlineTimes = presenceListener.getOnlineTimes();
	checkEachUser(onlineTimes);
    }

    public void checkEachUser(ArrayList<KeyValuePair> onlineTimes) {
	for (int i=0; i < onlineTimes.size(); i++) {
	    KeyValuePair entry = onlineTimes.get(i);
            logger.info("Member: " + entry.getKey().getEffectiveName() + ", Online Time: " + entry.getValue());
            if ((long)entry.getValue() > threshold) {
                logger.info("Member " + entry.getKey().getEffectiveName() + " has been online for too long.");
                sendGrassDM(entry.getKey().getUser());
            }
        }
    }

    private void sendGrassDM(User user) {
        logger.info("Sending grass DM to user: " + user.getName());
        user.openPrivateChannel().queue((PrivateChannel channel) -> {
            channel.sendMessage("You've been online for too long! Here's some grass:").queue();
            channel.sendMessage("https://media.istockphoto.com/photos/landscape-in-the-tundra-picture-id1180277728?b=1&k=6&m=1180277728&s=170x170&h=Ai2KtBTS51yY0Xp4TP7UX9wMsj6jHQ0jkecXHT5GyUM=").queue(); // Replace with actual image URL
        });
    }
}
