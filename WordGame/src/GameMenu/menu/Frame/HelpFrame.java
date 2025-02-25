package GameMenu.menu.Frame;

import GameMenu.menu.Panels.GamePassPanel;
import GameMenu.menu.Panels.PausePanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HelpFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final MainFrame mainFrame;

    public HelpFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
    }

    public void showPanel(String name, String currentPanel, int step, long usedTime) {
        setVisible(true);
        getContentPane().removeAll();
        URL imageURL = getClass().getResource("/GameMenu/ui/background/helpFrame_background.jpg");

        if (name.equals("Pause")){
            PausePanel panel = new PausePanel(mainFrame, this, imageURL);
            getContentPane().add(panel);
        }else if (name.equals("GamePass")){
            GamePassPanel panel = new GamePassPanel(mainFrame, imageURL, step, usedTime);
            getContentPane().add(panel);
        }

        getContentPane().setLayout(cardLayout);
        cardLayout.show(getContentPane(), name);
        revalidate();
        repaint();
    }
}
