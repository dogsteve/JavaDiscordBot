package configure;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class JDAConfigures {

    private JDABuilder jdaBuilder;

    public JDAConfigures(String token) {
        this.jdaBuilder = JDABuilder.createDefault(token);
        this.jdaBuilder.setActivity(Activity.watching("Porn Hub"));
        this.jdaBuilder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.ACTIVITY);
        this.jdaBuilder.setChunkingFilter(ChunkingFilter.NONE);
        this.jdaBuilder.disableIntents(GatewayIntent.DIRECT_MESSAGE_TYPING);
        this.jdaBuilder.setLargeThreshold(50);
    }

    public JDABuilder getJdaBuilder() {
        return this.jdaBuilder;
    }


}
