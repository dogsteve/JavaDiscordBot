package command;

import entities.Command;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ultis.ScopeChecker;
import  services.VoteBanServices;

public class VoteBanCommand extends ListenerAdapter {

    private VoteBanServices voteban;

    private String[] command = {
            "voteban", "cancelevote", "enjoyvote", "+1", "-1"
    };

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        if (event.getAuthor().isBot()) {
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
            User userToVote = event.getMessage().getMentionedUsers().get(0);
            this.voteban = new VoteBanServices(userToVote, cmd.getCommandContext()[0], event);
            event.getChannel().sendMessage("Press " + PrefixConstance.PREFIX + "enjoyvote" + " to join voting").queue();
            return;
        }

        if (cmd.getCommandName().equals(command[2])) {
            event.getChannel().sendMessage(this.voteban.addNewVoter(event.getAuthor())).queue();
            return;
        }

        if (cmd.getCommandName().equals(command[1])) {
            this.voteban.cancleVote();
            event.getChannel().sendMessage("Canceled").queue();
            return;
        }

        if (cmd.getCommandName().equals(command[3])) {
            event.getChannel().sendMessage(this.voteban.plusVote(event.getAuthor())).queue();
            if (this.voteban.voteValid()) {
                event.getChannel().sendMessage(this.voteban.voteExcute()).queue();
                return;
            }
            return;
        }

        if (cmd.getCommandName().equals(command[4])) {
            event.getChannel().sendMessage(this.voteban.subVote(event.getAuthor())).queue();
            return;
        }
    }
}
