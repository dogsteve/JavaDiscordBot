package command;

import GUI.PriceChart;
import entities.Command;
import entities.Stonks;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import stocks.VNDIRECRestAPI;
import ultis.ScopeChecker;

import java.io.File;

public class FinanceCommand extends ListenerAdapter {

    String[] command = {"stonks", "chart", "nextturn"};
    private VNDIRECRestAPI vndirect;

    @SneakyThrows
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

        // stonks
        if (cmd.getCommandName().equals(command[0])) {
            this.vndirect = new VNDIRECRestAPI(cmd.getCommandContext()[0], cmd.getCommandContext()[1], cmd.getCommandContext()[2]);
            vndirect.getPureJSON();
            event.getChannel().sendMessage("Data fetched").queue();
            if (cmd.getCommandContext().length > 3) {
                String header = "- ID ------------------ Date ------------ OpenPrice --------- ClosePrice --------- PercentChange";
                event.getChannel().sendMessage(header + "\n").queue();
                for (Stonks stk : vndirect.getListObject()) {
                    String print = "`" + stk.getCode() + "`                        `"+ stk.getDate() +"`                   `"+ stk.getOpen() +"`                            `" + stk.getClose() +"`                           `" +stk.getPctChange() + "`";
                    event.getChannel().sendMessage(print + "\n").queue();
                }
            }
        }

        // chart command
        if (cmd.getCommandName().equals(command[1])) {
            if (this.vndirect == null) {
                event.getChannel().sendMessage("You must run stonks fisrt!").queue();
                return;
            }
            PriceChart prc = new PriceChart(this.vndirect.getListObject());
            prc.getImage();
            File file = new File("chart.png");
            if (!file.exists()) {
                event.getChannel().sendMessage("Error").queue();
                return;
            }
            event.getChannel().sendMessage(event.getAuthor().getAsMention()).queue();
            event.getChannel().sendFile(file).queue();
            file.delete();
        }

        // next turn command
        if (cmd.getCommandName().equals(command[2])) {
            this.vndirect = null;
            event.getChannel().sendMessage("This session ended").queue();
        }
    }
}
