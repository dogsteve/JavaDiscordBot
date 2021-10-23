package command;

import audio.GuildMusicManager;
import audio.MusicPlayer;
import entities.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;
import ultis.ScopeChecker;

public class MusicCommand extends ListenerAdapter {

    private String[] command = {
            "play", "skip", "next", "currentsong", "terminate", "pause", "resume"
    };
    private MusicPlayer player;

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
        if (!ScopeChecker.checks(command, cmd)) {
            return;
        }
        if (event.getMember().getVoiceState().getChannel() == null) {
            event.getChannel().sendMessage("You are not in any voice chanel").queue();
            return;
        }
        // play command
        if (cmd.getCommandName().equals(command[0])) {
            if (this.player != null) {
                if (this.player.getMusicManager(event.getGuild()).scheduler.player.isPaused()) {
                    event.getChannel().sendMessage("Previous song has been paused").queue();
                    return;
                }
                this.player.loadAndPlay(event.getChannel(), cmd.getURL());
            }
            Guild guild = event.getGuild();
            VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel();
            AudioManager manager = guild.getAudioManager();
            manager.openAudioConnection(voiceChannel);
            this.player = new MusicPlayer();
            String zootube;
            if (cmd.getURL().startsWith("http")) {
                zootube = cmd.getURL();
            }
            else {
                zootube = "ytsearch:" + cmd.getURL();
            }
            player.loadAndPlay(event.getChannel(), zootube);
            return;
        }

        // terminate command
        if (cmd.getCommandName().equals(command[4])) {
            if (this.player == null) {
                event.getChannel().sendMessage("I didn't do anything").queue();
                return;
            }
            GuildMusicManager musicManager = player.getMusicManager(event.getGuild());
            musicManager.scheduler.player.destroy();
            this.player = null;
            event.getChannel().sendMessage("Terminated").queue();
            return;
        }

        // pause command
        if (cmd.getCommandName().equals(command[5])) {
            if (this.player.getMusicManager(event.getGuild()).scheduler.player.isPaused()) {
                event.getChannel().sendMessage("Paused already").queue();
                return;
            }
            player.getMusicManager(event.getGuild()).scheduler.player.setPaused(true);
            return;
        }

        // resume command
        if (cmd.getCommandName().equals(command[6])) {
            if (!this.player.getMusicManager(event.getGuild()).scheduler.player.isPaused()) {
                event.getChannel().sendMessage("Playing").queue();
                return;
            }
            player.getMusicManager(event.getGuild()).scheduler.player.setPaused(false);
            return;
        }

        // song info
        if (cmd.getCommandName().equals(command[3])) {
            event.getChannel().sendMessage(this.player.getMusicManager(event.getGuild()).scheduler.player.getPlayingTrack().getInfo().title).queue();
        }
    }
}
