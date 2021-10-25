import GUI.GUI;
import GUI.*;
import stocks.VNDIRECRestAPI;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws FileNotFoundException {

        String token = "";
        File tokenFile = new File("token.txt");
        if (tokenFile.exists()) {
            Scanner scn = new Scanner(tokenFile);
            if (scn.hasNextLine()) {
                token = scn.nextLine().trim();
            }
            else {
                token = null;
            }
        }
        if (token == null || token.equals("")) {
            token = JOptionPane.showInputDialog(null, "Bot token");
        }
        if (token == null || token.equals("")) {
            JOptionPane.showConfirmDialog(null, "You are missing bot token", "MISSING", 2);
            return;
        }
        GUI gui = new GUI(token);
    }
}
