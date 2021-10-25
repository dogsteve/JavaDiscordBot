package GUI;

import configure.MainBOT;

import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GUI extends JFrame {

    private TextArea txArea;
    private JLabel txtLabel;
    private MainBOT bot;

    public GUI (String token) {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setTitle("BOT Dashboard");
        this.setSize(1280, 768);
        this.txArea = new TextArea();
        this.txtLabel = new JLabel();
        this.txtLabel.setText("BOT BOT BOT BOT!");
        this.txArea.setEditable(false);
        add(this.txtLabel, BorderLayout.NORTH);
        add(this.txArea, BorderLayout.CENTER);
        setVisible(true);
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(output);
            System.setOut(ps);
            System.setErr(ps);
            this.bot = new MainBOT(token);
            this.txArea.setText("Bot is running \n");
            this.txArea.append(output.toString());
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
