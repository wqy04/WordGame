package GameMenu.menu.Panels;

import GameMenu.menu.BackgroundSettings.ImageBackgroundPanel;
import GameMenu.menu.Frame.MainFrame;

import java.awt.event.*;
import java.net.URL;

/**
 * CreditsPanel类是一个面板，用于显示游戏的制作人员名单。
 * 继承自ImageBackgroundPanel，具有自定义的背景图像。
 */
public class CreditsPanel extends ImageBackgroundPanel {
    /**
     * 构造一个CreditsPanel实例。
     *
     * @param mainFrame 游戏的主窗口MainFrame实例
     */
    public CreditsPanel(MainFrame mainFrame, URL imagePath){
        super(imagePath);
        setLayout(null);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER){
                    mainFrame.showPanel("Settings");
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.showPanel("Settings");
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                CreditsPanel.this.requestFocusInWindow();
            }
        });
    }

    @Override
    public void addNotify(){
        super.addNotify();
        requestFocusInWindow();
    }
}
