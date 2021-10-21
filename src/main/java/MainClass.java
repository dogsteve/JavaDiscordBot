import command.FeedBackCommand;
import command.MusicCommand;
import command.VoteBanCommand;
import configure.JDAConfigures;
import entities.Command;
import services.MessageFilterServices;
import net.dv8tion.jda.api.JDA;

public class MainClass {
    public static void main(String[] args) throws Exception {

        JDAConfigures jdaCon = new JDAConfigures("ODk5NTY5ODA2MjY3Nzg1MjQ2.YW0rqw.NToNmwvHaDQTig5jr813PASnLQg");
        JDA jda = jdaCon.getJdaBuilder().build();
        jda.addEventListener(new MessageFilterServices());
        jda.addEventListener(new VoteBanCommand());
        jda.addEventListener(new MusicCommand());
        jda.addEventListener(new FeedBackCommand());
    }
}
