package messageservices;

import contentfilter.TextMessageFilter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageFilterServices extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);

        String context = event.getMessage().getContentRaw();
        TextMessageFilter.getMessageFilter().forEach(
                object -> {
                    if (context.matches(object.getContext())) {
                        event.getChannel().sendMessage(event.getAuthor().getAsMention() + " " + object.getType()).queue();
                    }
                }
        );
    }
}
