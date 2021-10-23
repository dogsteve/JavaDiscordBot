package GUI;

import configure.MainBOT;
import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GUI extends JFrame {

    private TextArea txArea;
    private JLabel txtLabel;
    private MainBOT bot;

    public GUI () {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        String token = JOptionPane.showInputDialog(null, "Your bot token", "BOT TOKEN", 1);
        if (token.equals("")) {
            JOptionPane.showConfirmDialog(null, "Missing bot token", "Missing", JOptionPane.CLOSED_OPTION);
            return;
        }
        pack();
        this.setSize(1280, 768);
        this.txArea = new TextArea();
        this.txtLabel = new JLabel();
        setTitle("BOT Dashboard");
        this.txtLabel.setText("BOT BOT BOT BOT!");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(this.txtLabel, BorderLayout.NORTH);
        add(this.txArea, BorderLayout.CENTER);
        setVisible(true);
        try {
            this.bot = new MainBOT(token);
            this.txArea.setText("Bot is running");
        }
        catch (LoginException e) {
            this.bot.shutdownBot();
            this.txArea.setText(e.getMessage());
        }

        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                bot.shutdownBot();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }

}
