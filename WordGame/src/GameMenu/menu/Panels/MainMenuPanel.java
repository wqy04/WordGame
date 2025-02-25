package GameMenu.menu.Panels;

import GameMenu.button.MainButton;
import GameMenu.menu.BackgroundSettings.BaseMenuPanel;
import GameMenu.menu.Frame.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Objects;

/**
 * MainMenuPanel类是游戏的主菜单面板，显示了开始、设置和退出按钮。
 * 继承自BaseMenuPanel，具有自定义的背景图像和菜单按钮。
 */
public class MainMenuPanel extends BaseMenuPanel {
    private final MainFrame mainFrame;
    private MainButton mainButton; // 开始按钮
    private MainButton settingsButton; // 设置按钮
    private MainButton exitButton; // 退出按钮

    /**
     * 构造一个MainMenuPanel实例。
     *
     * @param mainFrame 游戏的主窗口MainFrame实例
     */
    public MainMenuPanel(MainFrame mainFrame, URL imageURL) {
        super(imageURL);
        this.mainFrame = mainFrame;
        mainFrame.musicPlay();
    }

    /**
     * 初始化菜单按钮。
     */
    @Override
    protected void initButtons() {

        // 创建并初始化 mainButton
        ImageIcon startButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/start_button.png")));
        mainButton = new MainButton(startButtonIcon);
        mainButton.setBounds(400, 290, mainButton.getPreferredSize().width, 66);
        mainButton.addActionListener(e -> mainFrame.showPanel("LevelSelection"));
        add(mainButton);

        // 创建并初始化 settingsButton
        ImageIcon settingsButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/settings_button.png")));
        settingsButton = new MainButton(settingsButtonIcon);
        settingsButton.setBounds(400, 370, settingsButton.getPreferredSize().width, 66);
        settingsButton.addActionListener(e -> mainFrame.showPanel("Settings"));
        add(settingsButton);

        // 创建并初始化 exitButton
        ImageIcon exitButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/exit_button.png")));
        exitButton = new MainButton(exitButtonIcon);
        exitButton.setBounds(400, 450, exitButton.getPreferredSize().width, 66);
        exitButton.addActionListener(e -> System.exit(0));  // 设置退出动作
        add(exitButton);
    }

    /**
     * 处理按键事件。
     *
     * @param e 按键事件对象
     */
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

    /**
     * 更新选中按钮的索引。
     *
     * @param delta 索引变化量
     */
    @Override
    protected void updateSelectedIndex(int delta){
        selectedIndex += delta;
        int buttonCount = 3;
        selectedIndex = (selectedIndex + buttonCount) % buttonCount; // 循环更新选中按钮索引
        updateButtonSelection();
    }

    /**
     * 更新按钮的选中状态。
     */
    @Override
    protected void updateButtonSelection(){
        // 更新按钮选中状态
        mainButton.setSelected(selectedIndex == 0); // 更新开始按钮的选中状态
        settingsButton.setSelected(selectedIndex == 1); // 更新设置按钮的选中状态
        exitButton.setSelected(selectedIndex == 2); // 更新退出按钮的选中状态

        switch (selectedIndex){
            case 0:
                selectedButton = mainButton;
                break;
            case 1:
                selectedButton = settingsButton;
                break;
            case 2:
                selectedButton = exitButton;
        }
        repaint();
    }

    /**
     * 执行选中按钮的动作。
     */
    @Override
    protected void performSelectedAction(){
        if (selectedIndex == 0){
            mainButton.doClick(); // 模拟点击开始按钮
        } else if (selectedIndex == 1) {
            settingsButton.doClick(); // 模拟点击设置按钮
        } else if (selectedIndex == 2) {
            exitButton.doClick(); // 模拟点击退出按钮
        }
    }
}