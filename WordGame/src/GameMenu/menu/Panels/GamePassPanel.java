package GameMenu.menu.Panels;

import GameMenu.button.MainButton;
import GameMenu.menu.BackgroundSettings.ImageBackgroundPanel;
import GameMenu.menu.Frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Objects;

public class GamePassPanel extends ImageBackgroundPanel {
    private MainButton nextRoundButton;
    private MainButton returnMenuButton;
    private final MainFrame mainFrame;
    private final long usedTime;
    private final int step;

    private int selectedIndex;

    public GamePassPanel(MainFrame mainFrame, URL imageURL, int step, long usedTime){
        super(imageURL);
        this.mainFrame = mainFrame;
        this.usedTime = usedTime;
        this.step = step;
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
        String currentLevel = mainFrame.getCurrentPanelName();
        char lastCharacter = currentLevel.charAt(currentLevel.length() - 1);

        String text = "步数: " + step + "\n用时: " + usedTime;
        JTextArea passArea = new JTextArea();
        passArea.setText(text);
        passArea.setBounds(40,58, 70, 45);
        Font font = new Font("SimSum", Font.BOLD, 16);
        passArea.setFont(font);
        passArea.setOpaque(false);
        passArea.setBackground(new Color(0, 0, 0, 0));
        passArea.setEditable(false);
        passArea.setFocusable(false);
        add(passArea);

        JTextArea introduceArea = new JTextArea();
        int currentCharacter = Character.getNumericValue(lastCharacter);
        String introduceText = "";
        if (currentCharacter == 1){
            introduceText = "下一关: 看！那是一头鹿，作为猎人你可不能让它跑掉，想办法猎到这头鹿吧。";
        } else if (currentCharacter == 2){
            introduceText = "下一关: 作为一位大诗人，今日你诗兴大发要作诗一首。";
        } else if (currentCharacter == 3) {
            introduceText = "下一关: 作为一位将领，组建军队收复失地，讨伐贼寇吧。";
        } else if (currentCharacter == 4) {
            introduceText = "您已全部通关！";
        }
        introduceArea.setText(introduceText);
        introduceArea.setBounds(112, 58, 300, 45);
        introduceArea.setLineWrap(true);
        introduceArea.setWrapStyleWord(true);
        introduceArea.setFont(font);
        introduceArea.setOpaque(false);
        introduceArea.setBackground(new Color(0, 0, 0, 0));
        introduceArea.setEditable(false);
        introduceArea.setFocusable(false);
        add(introduceArea);

        JLabel verticalLine = new JLabel();
        verticalLine.setOpaque(true);
        verticalLine.setBackground(Color.BLACK);
        verticalLine.setBounds(110, 63, 2, 37);
        add(verticalLine);

        JLabel titleLabel = new JLabel();
        ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/background/celebration.png")));
        titleLabel.setIcon(image);
        titleLabel.setBounds(125, 10, 200, 45);
        add(titleLabel);

        ImageIcon nextRoundButtonIcon = new ImageIcon(Objects.requireNonNull
                (GamePassPanel.class.getResource("/GameMenu/ui/button/nextRound_button.png")));
        nextRoundButton = new MainButton(nextRoundButtonIcon);
        nextRoundButton.setBounds(93, 110, nextRoundButton.getPreferredSize().width, 66);
        nextRoundButton.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // 获取当前的Frame
            currentFrame.dispose();
            int nextCharacter = Character.getNumericValue(lastCharacter) + 1;
            String nextLevel = currentLevel.substring(0, currentLevel.length() - 1) + nextCharacter;
            mainFrame.getAudioController().stopAll();
            if (nextCharacter == 5) {
                mainFrame.getAudioController().stopAll();
                mainFrame.showPanel("MainMenu");
                mainFrame.musicPlay();
                mainFrame.showPanel("LevelSelection");
            } else if (nextCharacter < 5){
                mainFrame.showPanel(nextLevel);
            }
        });
        add(nextRoundButton);

        ImageIcon returnMenuButtonIcon = new ImageIcon(Objects.requireNonNull
                (GamePassPanel.class.getResource("/GameMenu/ui/button/returnManu_button.png")));
        returnMenuButton = new MainButton(returnMenuButtonIcon);
        returnMenuButton.setBounds(93, 185, returnMenuButton.getPreferredSize().width, 66);
        returnMenuButton.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // 获取当前的Frame
            currentFrame.dispose();
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
        int buttonCount = 2;
        selectedIndex = (selectedIndex + buttonCount) % buttonCount; // 循环更新选中按钮索引
        updateButtonSelection();
    }

    /**
     * 更新按钮的选中状态。
     */
    protected void updateButtonSelection(){
        // 更新按钮选中状态
        nextRoundButton.setSelected(selectedIndex == 0); // 更新下一关按钮的选中状态
        returnMenuButton.setSelected(selectedIndex == 1); // 更新返回菜单按钮的选中状态

        switch (selectedIndex){
            case 0, 1:
                break;
        }
        repaint();
    }

    /**
     * 执行选中按钮的动作。
     */
    private void performSelectedAction(){
        if (selectedIndex == 0){
            nextRoundButton.doClick(); // 模拟点击下一关按钮
        } else if (selectedIndex == 1) {
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
