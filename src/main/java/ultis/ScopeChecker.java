package ultis;

import entities.Command;

public class ScopeChecker {
    public static boolean checks (String[] args, Command cmd) {
        for (String arg : args) {
            if (cmd.getCommandName().equals(arg)) {
                return true;
            }
        }
        return false;
    }
}
