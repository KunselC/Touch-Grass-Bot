package listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;

public class PresenceListener extends ListenerAdapter {
    private static final Logger logger = Logger.getLogger(PresenceListener.class.getName());
    private final Map<Member, Long> onlineTimes = new HashMap<>();

    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        Member member = event.getMember();
	System.out.println("Literally anything plz");
        logger.info("UserUpdateOnlineStatusEvent triggered for member: " + member.getEffectiveName());
        if (event.getNewOnlineStatus().equals(net.dv8tion.jda.api.OnlineStatus.ONLINE)) {
            logger.info("Member " + member.getEffectiveName() + " is now online.");
            onlineTimes.put(member, System.currentTimeMillis());
        } else {
            long onlineTime = System.currentTimeMillis() - onlineTimes.getOrDefault(member, System.currentTimeMillis());
            logger.info("Member " + member.getEffectiveName() + " is now offline. Online time: " + onlineTime);
            onlineTimes.put(member, onlineTime);
        }
    }

    public Map<Member, Long> getOnlineTimes() {
        return onlineTimes;
    }
}
