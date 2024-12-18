package listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.logging.Logger;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;

import utils.KeyValuePair;

public class PresenceListener extends ListenerAdapter {
    private static final Logger logger = Logger.getLogger(PresenceListener.class.getName());
    private final ArrayList<KeyValuePair> onlineTimes = new ArrayList<>();

    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        Member member = event.getMember();
        logger.info("UserUpdateOnlineStatusEvent triggered for member: " + member.getEffectiveName());
        if (event.getNewOnlineStatus().equals(net.dv8tion.jda.api.OnlineStatus.ONLINE)) {
            logger.info("Member " + member.getEffectiveName() + " is now online.");
            onlineTimes.add(new KeyValuePair(member, System.currentTimeMillis()));
        } else {
	    Object time = System.currentTimeMillis();
	    for (KeyValuePair pair : onlineTimes) {
	        if (pair.getKey().equals(member)) {
		    time = pair.getValue();
		    break;
		}
	    };
            long onlineTime = System.currentTimeMillis() - (long)time;
            logger.info("Member " + member.getEffectiveName() + " is now offline. Online time: " + onlineTime);
            onlineTimes.add(new KeyValuePair(member, onlineTime));
        }
    }

    public ArrayList<KeyValuePair> getOnlineTimes() {
        return onlineTimes;
    }
}
