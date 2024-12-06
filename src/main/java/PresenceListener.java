package listeners;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PresenceListener extends ListenerAdapter {
    private final Map<Member, Long> onlineTimes = new HashMap<>();

    @Override
    public void onGuildMemberUpdateOnlineStatus(GuildMemberUpdateOnlineStatusEvent event) {
        Member member = event.getMember();
        if (event.getNewOnlineStatus().isOnline()) {
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