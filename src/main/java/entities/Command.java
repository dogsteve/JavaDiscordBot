package entities;

import command.PrefixConstance;

public class Command {

    private String message;
    private String commandName;
    private String[] commandContext;
    private int numberOfContext;

    public Command(String message) {
        this.message = message;
        if (checkLegalCommand()) {
            this.commandName = this.message.split(PrefixConstance.PREFIX)[1];
            this.commandName = this.commandName.split(" ")[0];
            this.numberOfContext = this.message.split(PrefixConstance.PREFIX + commandName).length;
            if (numberOfContext == 0) {
                return;
            }
            this.commandContext = this.message.split(PrefixConstance.PREFIX + commandName);
            this.commandContext = this.commandContext[1].split("/");
            for (int i = 0; i < this.commandContext.length; i++) {
                this.commandContext[i] = this.commandContext[i].trim();
            }
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
