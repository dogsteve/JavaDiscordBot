package command;

import contentfilter.TextMessageFilter;
import entities.MessageFilter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import entities.Command;
import ultis.ScopeChecker;

public class MessageFilterCommand extends ListenerAdapter {

    private final String[] command = {
            "addnewfilter", "removefilter", "showfilter", "findfilter"
    };

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        if (event.getAuthor().equals(event.getJDA().getSelfUser())) {
            return;
        }
        String context = event.getMessage().getContentRaw();
        Command cmd = new Command(context);
        if (!cmd.checkLegalCommand()) {
            return;
        }
        if (!ScopeChecker.checks(command, cmd)) {
            return;
        }
        if (cmd.getCommandName().equals(command[2])) {
            String returnMessage = "";
            for (MessageFilter msgf : TextMessageFilter.getMessageFilter()) {
                returnMessage += "Filter contex : " + msgf.getContext() + " / Reply word : " + msgf.getType() + "\n";
            }
            event.getChannel().sendMessage(returnMessage).queue();
            return;
        }
        if (cmd.getNumberOfContext() == 0) {
            event.getChannel().sendMessage("Missing parameter for this command").queue();
            return;
        }
        if (cmd.getCommandName().equals(command[0])) {
            if (cmd.getNumberOfContext() < 2) {
                event.getChannel().sendMessage("Missing parameter for this command").queue();
                return;
            }
            event.getChannel().sendMessage(TextMessageFilter.addNewFilter(new MessageFilter(cmd.getCommandContext()[0], cmd.getCommandContext()[1]))).queue();
        }
        if (cmd.getCommandName().equals(command[1])) {
            event.getChannel().sendMessage(TextMessageFilter.removeFilter(cmd.getCommandContext()[0])).queue();
            return;
        }
    }
}
