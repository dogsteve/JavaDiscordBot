package command;

import entities.Command;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ultis.ScopeChecker;


public class ActivityCommand extends ListenerAdapter {

    String[] command = {"changeactivity"};
    private JDA jda;

    public ActivityCommand (JDA instance) {
        this.jda = instance;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String context = event.getMessage().getContentRaw();
        Command cmd = new Command(context);
        if (event.getAuthor().isBot()) {
            return;
        }
        if (!cmd.checkLegalCommand()) {
            return;
        }
        if (!ScopeChecker.checks(command, cmd)) {
            return;
        }

        if (cmd.getCommandName().equals(command[0])) {
            if (cmd.getCommandContext()[0].equalsIgnoreCase("playing")) {
                this.jda.getPresence().setActivity(Activity.playing(cmd.getCommandContext()[1]));
            }
            if (cmd.getCommandContext()[0].equalsIgnoreCase("watching")) {
                this.jda.getPresence().setActivity(Activity.watching(cmd.getCommandContext()[1]));
            }
            if (cmd.getCommandContext()[0].equalsIgnoreCase("listening")) {
                this.jda.getPresence().setActivity(Activity.listening(cmd.getCommandContext()[1]));
            }
            if (cmd.getCommandContext()[0].equalsIgnoreCase("competing")) {
                this.jda.getPresence().setActivity(Activity.competing(cmd.getCommandContext()[1]));
            }
        }

    }
}
