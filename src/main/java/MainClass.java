import command.MessageFilterCommand;
import command.VoteBanCommand;
import configure.JDAConfigures;
import contentfilter.TextMessageFilter;
import messageservices.MessageFilterServices;
import net.dv8tion.jda.api.JDA;

public class MainClass {
    public static void main(String[] args) throws Exception {

        JDAConfigures jdaCon = new JDAConfigures("ODk5NTY5ODA2MjY3Nzg1MjQ2.YW0rqw.NToNmwvHaDQTig5jr813PASnLQg");
        JDA jda = jdaCon.getJdaBuilder().build();
        TextMessageFilter.initDefaultFilter();
        jda.addEventListener(new MessageFilterServices());
        jda.addEventListener(new MessageFilterCommand());
        jda.addEventListener(new VoteBanCommand());

    }
}
