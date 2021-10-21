package services;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.regex.*;

// this class is live running services
public class MessageFilterServices extends ListenerAdapter {

    private String regex = "(cac)|(buoi)|(lon)|(dit)|(buồi)|(đầu buồi)";
    private Pattern ptn;

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        if (event.getAuthor().isBot()) {
            return;
        }
        String context = event.getMessage().getContentRaw();
        ptn = Pattern.compile(regex);
        Matcher matcher = ptn.matcher(context);
        if (matcher.find()) {
            event.getChannel().sendMessage("Con chó " + event.getAuthor().getAsMention() + " chửi bậy").queue();
        }
    }
}
