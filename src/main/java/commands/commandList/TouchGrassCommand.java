package commands.commandList;

import utils.OnlineTimeChecker;

import commands.ICommand;
import listeners.PresenceListener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;

import java.util.List;

public class TouchGrassCommand implements ICommand {

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if (Math.random() > 0.5) {
            event.getChannel().sendMessage("Yes! Here's some grass:").queue();
            event.getChannel().sendMessage("https://wallpapercave.com/wp/wp4854969.jpg").queue();
        } else {
            event.getChannel().sendMessage("No! 👍").queue();
        }
    }

    @Override
    public String getCommand() {
        return "touchgrass";
    }

    @Override
    public String getHelp() {
        return "Tells you if you need to touch grass.";
    }
}
