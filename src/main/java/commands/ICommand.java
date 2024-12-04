package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface ICommand {
    void run(List<String> args, MessageReceivedEvent event);
    String getCommand();
    String getHelp();
}
