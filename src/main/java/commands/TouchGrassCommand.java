package commands;
public void run(MessageReceivedEvent event) {


import commands.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class TouchGrassCommand implements ICommand {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if (Math.random() > 0.5) {
            event.getChannel().sendMessage("Yes! Here's some grass:").queue();
            event.getChannel().sendMessage("https://wallpapercave.com/wp/wp4854969.jpg").queue(); // Replace with actual image URL
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