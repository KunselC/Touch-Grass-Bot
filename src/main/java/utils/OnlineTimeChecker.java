package utils;

import listeners.PresenceListener;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineTimeChecker {
    private final PresenceListener presenceListener;
    private final long threshold = 7200000; // 2 hours in milliseconds

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

    private void checkOnlineTimes() {
        Map<Member, Long> onlineTimes = presenceListener.getOnlineTimes();
        for (Map.Entry<Member, Long> entry : onlineTimes.entrySet()) {
            if (entry.getValue() > threshold) {
                sendGrassDM(entry.getKey().getUser());
            }
        }
    }

    private void sendGrassDM(User user) {
        user.openPrivateChannel().queue((PrivateChannel channel) -> {
            channel.sendMessage("You've been online for too long! Here's some grass:").queue();
            channel.sendMessage("https://wallpapercave.com/wp/wp4854969.jpg").queue(); // Replace with actual image URL
        });
    }
}
