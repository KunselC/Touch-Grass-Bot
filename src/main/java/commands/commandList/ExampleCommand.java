package commands.commandList;

import commands.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class ExampleCommand implements ICommand {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        event.getChannel().sendMessage("Hallo!").queue();
    }

    @Override
    public String getCommand() {
        return "Hello";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
