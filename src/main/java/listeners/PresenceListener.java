package listeners;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;

public class PresenceListener extends ListenerAdapter {
    private final Map<Member, Long> onlineTimes = new HashMap<>();

    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        Member member = event.getMember();
        if (event.getNewOnlineStatus().equals(net.dv8tion.jda.api.OnlineStatus.ONLINE)) {
            onlineTimes.put(member, System.currentTimeMillis());
        } else {
            long onlineTime = System.currentTimeMillis() - onlineTimes.getOrDefault(member, System.currentTimeMillis());
            onlineTimes.put(member, onlineTime);
        }
    }

    public Map<Member, Long> getOnlineTimes() {
        return onlineTimes;
    }
}