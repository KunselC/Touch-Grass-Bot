package commands;

import commands.commandList.ExampleCommand;
import commands.TouchGrassCommand;
import constants.Global;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.*;
import java.util.regex.Pattern;

public class CommandManager {
    private final Map<String, ICommand> commands = new HashMap<>();

    public CommandManager(){
        //enlist the commands here
        addCommand(new ExampleCommand());
        addCommand(new TouchGrassCommand());
    }

    private void addCommand(ICommand c){
        if(!commands.containsKey(c.getCommand())){
            commands.put(c.getCommand(), c);
        }
    }

    public Collection<ICommand> getCommands(){
        return commands.values();
    }

    public ICommand getCommand(String commandName){
        if(commandName == null){
            return null;
        }
        return commands.get(commandName);
    }

    public void run(MessageReceivedEvent event){
        final String msg = event.getMessage().getContentRaw();
        if(!msg.startsWith(Global.prefix)){
            return;
        }

        final String split[] = msg.replaceFirst("(?i)" + Pattern.quote(Global.prefix), "").split("\\s+");
        final String command = split[0].toLowerCase();
        if(commands.containsKey(command)){
            final List<String> args = Arrays.asList(split).subList(1, split.length);
            commands.get(command).run(args, event);
        }
    }
}