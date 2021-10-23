package configure;

import GUI.GUI;
import command.*;
import configure.JDAConfigures;
import net.dv8tion.jda.api.JDA;
import services.MessageFilterServices;

import javax.security.auth.login.LoginException;


public class MainBOT {

    private JDAConfigures configure;
    private JDA jda;

    public MainBOT (String token) throws LoginException {
        this.configure = new JDAConfigures(token);
        this.jda = configure.getJdaBuilder().build();
        jda.addEventListener(new MessageFilterServices());
        jda.addEventListener(new VoteBanCommand());
        jda.addEventListener(new MusicCommand());
        jda.addEventListener(new FeedBackCommand());
        jda.addEventListener(new PrefixCommand());
        jda.addEventListener(new FinanceCommand());
    }

    public void shutdownBot() {
        this.jda.shutdown();
    }

    public JDA getJda () {
        return jda;
    }

}
