import commands.CommandListener;
import constants.Secret;
import listeners.PresenceListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import utils.OnlineTimeChecker;

import javax.security.auth.login.LoginException;
import java.util.logging.LogManager;

public class DiscordBot {

    public static JDABuilder builder;

    public static void main(String[] args) throws LoginException {
        // Load logging configuration
        try {
            LogManager.getLogManager().readConfiguration(DiscordBot.class.getResourceAsStream("/logging.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String token = Secret.token; // your bot token
        builder = JDABuilder.createDefault(token);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);
        builder.setActivity(Activity.listening("your commands"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);

        // Create and register the PresenceListener
        PresenceListener presenceListener = new PresenceListener();
        builder.addEventListeners(new CommandListener(), presenceListener);

        // Instantiate OnlineTimeChecker with the PresenceListener
        new OnlineTimeChecker(presenceListener);

        builder.build();
    }
}