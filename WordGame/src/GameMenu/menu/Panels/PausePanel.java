package GameMenu.menu.Panels;

import GameMenu.button.MainButton;
import GameMenu.menu.BackgroundSettings.ImageBackgroundPanel;
import GameMenu.menu.Frame.HelpFrame;
import GameMenu.menu.Frame.MainFrame;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Objects;

public class PausePanel extends ImageBackgroundPanel {
    private MainButton keepGameButton;
    private MainButton restartButton;
    private MainButton returnMenuButton;
    private final MainFrame mainFrame;
    private final HelpFrame helpFrame;
    private int selectedIndex;

    public PausePanel(MainFrame mainFrame, HelpFrame helpFrame, URL imageURL){

        super(imageURL);
        this.mainFrame = mainFrame;
        this.helpFrame = helpFrame;
        setLayout(null);
        setFocusable(true);

        initButtons();
        selectedIndex = 0;
        updateButtonSelection(); // 使用 updateButtonSelection 方法更新按钮选中状态

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP) {
                    updateSelectedIndex(-1); // 向上移动选中的按钮
                } else if (key == KeyEvent.VK_DOWN) {
                    updateSelectedIndex(1); // 向下移动选中的按钮
                } else if (key == KeyEvent.VK_ENTER) {
                    performSelectedAction(); // 执行选中按钮的动作
                }
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                resetFocusAndSelection();
            }
        });
    }

    private void initButtons(){
        ImageIcon keepGameButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/keepGame_button.png")));
        keepGameButton = new MainButton(keepGameButtonIcon);
        keepGameButton.setBounds(93, 20, keepGameButton.getPreferredSize().width, 66);
        keepGameButton.addActionListener(e -> helpFrame.dispose());
        add(keepGameButton);

        ImageIcon restartButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/restart_button.png")));
        restartButton = new MainButton(restartButtonIcon);
        restartButton.setBounds(93, 100, restartButton.getPreferredSize().width, 66);
        restartButton.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // 获取当前的Frame
            currentFrame.dispose();
            mainFrame.showPanel(mainFrame.getCurrentPanelName());
        });
        add(restartButton);

        ImageIcon returnMenuButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/returnManu_button.png")));
        returnMenuButton = new MainButton(returnMenuButtonIcon);
        returnMenuButton.setBounds(93, 180, returnMenuButton.getPreferredSize().width, 66);
        returnMenuButton.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // 获取当前的Frame
            currentFrame.dispose();
            mainFrame.getAudioController().stopAll();
            mainFrame.showPanel("MainMenu");
            mainFrame.musicPlay();
        });
        add(returnMenuButton);
    }

    /**
     * 更新选中按钮的索引。
     *
     * @param delta 索引变化量
     */
    protected void updateSelectedIndex(int delta){
        selectedIndex += delta;
        int buttonCount = 3;
        selectedIndex = (selectedIndex + buttonCount) % buttonCount; // 循环更新选中按钮索引
        updateButtonSelection();
    }

    /**
     * 更新按钮的选中状态。
     */
    protected void updateButtonSelection(){
        // 更新按钮选中状态
        keepGameButton.setSelected(selectedIndex == 0); // 更新继续游戏按钮的选中状态
        restartButton.setSelected(selectedIndex == 1); // 更新重新开始按钮的选中状态
        returnMenuButton.setSelected(selectedIndex == 2); // 更新返回菜单按钮的选中状态

        switch (selectedIndex){
            case 0, 1, 2:
                break;
        }
        repaint();
    }

    /**
     * 执行选中按钮的动作。
     */
    private void performSelectedAction(){
        if (selectedIndex == 0){
            keepGameButton.doClick(); // 模拟点击继续游戏按钮
        } else if (selectedIndex == 1) {
            restartButton.doClick(); // 模拟点击重新开始按钮
        } else if (selectedIndex == 2) {
            returnMenuButton.doClick(); // 模拟点击返回菜单按钮
        }
    }

    private void resetFocusAndSelection() {
        requestFocusInWindow();
        updateButtonSelection();
    }

    @Override
    public void addNotify(){
        super.addNotify();
        requestFocusInWindow();
    }
}
