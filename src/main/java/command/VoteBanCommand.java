package command;

import entities.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ultis.ScopeChecker;

public class VoteBanCommand extends ListenerAdapter {

    private String[] command = {
            "voteban", "cancleban", "enjoyvote", "+1"
    };
    private int numberJoin = 1;
    private int numberVote = 0;

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        if (event.getAuthor().equals(event.getJDA().getSelfUser())) {
            return;
        }
        Command cmd = new Command(event.getMessage().getContentRaw());
        if (!cmd.checkLegalCommand()) {
            return;
        }
        if (!ScopeChecker.checks(command, cmd)) {
            return;
        }
        if (cmd.getCommandName().equals(command[0])) {
            event.getChannel().sendMessage("Press " + PrefixConstance.PREFIX + "enjoyvote" + " to join voting").queue();
        }
        if (cmd.getCommandName().equals(command[2])) {
            event.getChannel().sendMessage("Press " + PrefixConstance.PREFIX + "+1 to agree press nothing for nothing").queue();
        }
    }
}
