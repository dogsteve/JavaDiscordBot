package entities;

import command.PrefixConstance;

import java.util.Arrays;

public class Command {

    private String message;
    private String commandName;
    private String[] commandContext;
    private int numberOfContext;

    public Command(String message) {
        this.message = message;
        if(!this.message.startsWith(PrefixConstance.PREFIX)) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(this.message);
        this.commandName = this.message.substring(PrefixConstance.PREFIX.length(), this.message.length()).split(" ")[0];
        commandContext = stringBuilder.delete(0, PrefixConstance.PREFIX.length() + this.commandName.length()).toString().trim().split("/");
        for (int i = 0; i < commandContext.length; i++) {
            commandContext[i] = commandContext[i].trim();
        }
    }

    public String getURL () {
        try {
            return "http" + this.message.split("http")[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return this.commandContext[0];
        }
    }


    public String getCommandName() {
        return commandName;
    }

    public String[] getCommandContext() {
        return commandContext;
    }

    public boolean checkLegalCommand () {
        return this.message.startsWith(PrefixConstance.PREFIX);
    }

    public int getNumberOfContext () {
        return this.numberOfContext;
    }

}
