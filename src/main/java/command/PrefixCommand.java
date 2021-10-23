package command;

import entities.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ultis.ScopeChecker;

public class PrefixCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        String context = event.getMessage().getContentRaw();
        Command cmd = new Command(context);
        if (event.getAuthor().isBot()) {
            return;
        }
        if (!cmd.checkLegalCommand()) {
            return;
        }
        if (!cmd.getCommandName().equals("setting")) {
            return;
        }
        if (cmd.getCommandName().equals("setting")) {
            PrefixConstance.PREFIX = cmd.getCommandContext()[0];
            event.getChannel().sendMessage("Prefix has been changed to: " + PrefixConstance.PREFIX).queue();
            return;
        }
    }
}
