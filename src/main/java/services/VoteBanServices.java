package services;

import command.PrefixConstance;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.internal.utils.PermissionUtil;

import java.util.HashSet;
import java.util.Set;

public class VoteBanServices {

    private User userToVote;
    private String reason;
    private Set<User> listVote, listEnjoy, listDisvote;
    GuildMessageReceivedEvent event;

    public VoteBanServices (User user, String reason, GuildMessageReceivedEvent event) {
        this.userToVote = user;
        this.reason = reason;
        this.event = event;
        this.listVote = new HashSet<User>();
        this.listEnjoy = new HashSet<User>();
        this.listDisvote = new HashSet<User>();
        event.getChannel().sendMessage("Good luck " + user.getAsMention() + " Ban reason : " + this.reason).queue();
    }

    public boolean voteValid () {
        return (this.listVote.size() / this.listVote.size()) > 0.5f;
    }

    public String plusVote (User user) {
        if (!this.listEnjoy.contains(user)) {
            return "You not participate vote yet";
        }
        if (this.listVote.contains(user)) {
            return "You voted agree before";
        }
        if (this.listDisvote.contains(user)) {
            this.listDisvote.remove(user);
            this.listVote.add(user);
            return "You voted agree";
        }
        this.listVote.add(user);
        return "You voted agree";
    }

    public String subVote (User user) {
        if (!this.listEnjoy.contains(user)) {
            return "You not participate vote yet";
        }
        if (this.listDisvote.contains(user)) {
            return "You voted reject before";
        }
        if (this.listVote.contains(user)) {
            this.listVote.remove(user);
            this.listDisvote.add(user);
            return "You voted reject";
        }
        this.listDisvote.add(user);
        return "You voted reject";
    }

    public String addNewVoter (User user) {
        if (this.listEnjoy.contains(user)) {
            return "You participated";
        }
        this.listEnjoy.add(user);
        return PrefixConstance.PREFIX + "+1 to agree or " + PrefixConstance.PREFIX + "-1 to reject";
    }

    public String voteExcute () {
        if (this.listEnjoy.size() < 3) {
            return "I need at least 3 people to start the vote";
        }
        Guild guild = this.event.getGuild();
        try {
            guild.ban(this.userToVote, 0, this.reason).queue();
            return "Executed";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public void cancleVote () {
        this.listVote = null;
        this.userToVote = null;
        this.listDisvote = null;
        this.listEnjoy = null;
        this.reason = null;
    }

}
