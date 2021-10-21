package command;

import entities.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ultis.ScopeChecker;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FeedBackCommand extends ListenerAdapter {

    String[] command = {"feedback", "credit"};

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

        // feedback
        if (cmd.getCommandName().equals(command[0])) {
            try {
                String feedback =  "Time: " + event.getMessage().getTimeCreated().toString() + " | Name: " + event.getAuthor().getName() + " | Context: ";
                for (String str : cmd.getCommandContext()) {
                    feedback += str;
                }
                feedback += "\n";
                Files.write(Paths.get("feedback.txt"), feedback.getBytes(), StandardOpenOption.APPEND);
                event.getChannel().sendMessage("Bần tăng xin ghi nhận góp ý của thính giả. Cảm ơn bạn đã tham gia chương trình beta!").queue();
                return;
            }
            catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        // credit
        if (cmd.getCommandName().equals(command[1])) {
            event.getChannel().sendMessage("Một mình bần tăng làm hết =)), liên hệ hoặc donate cho mình qua mail nha : luyendong1102@gmail.com nha").queue();
            return;
        }

    }
}
