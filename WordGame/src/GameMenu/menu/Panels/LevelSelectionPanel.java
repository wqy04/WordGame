package GameMenu.menu.Panels;

import GameMenu.button.MainButton;
import GameMenu.menu.BackgroundSettings.BaseMenuPanel;
import GameMenu.menu.Frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Objects;

public class LevelSelectionPanel extends BaseMenuPanel {
    private MainButton level1Button;
    private MainButton level2Button;
    private MainButton level3Button;
    private MainButton level4Button;
    private MainButton returnButton;
    private final MainFrame mainFrame;
    private final ImageIcon level1DemoIcon, level2DemoICon, level3DemoICon, level4DemoIcon, level1IntroduceIcon,
            level2IntroduceIcon, level3IntroduceIcon, level4IntroduceIcon;
    private ImageIcon selectedDemoIcon, selectedIntroduceIcon;

    public LevelSelectionPanel(MainFrame mainFrame, URL imageURL) {
        super(imageURL);
        this.mainFrame = mainFrame;
        setDoubleBuffered(true);

        level1DemoIcon = getNewImageIcon(new ImageIcon(Objects.requireNonNull
                (getClass().getResource("/GameMenu/ui/background/level1_demo.gif"))));
        level2DemoICon = getNewImageIcon(new ImageIcon(Objects.requireNonNull
                (getClass().getResource("/GameMenu/ui/background/level2_demo.gif"))));
        level3DemoICon = getNewImageIcon(new ImageIcon(Objects.requireNonNull
                (getClass().getResource("/GameMenu/ui/background/level3_demo.gif"))));
        level4DemoIcon = getNewImageIcon(new ImageIcon(Objects.requireNonNull
                (getClass().getResource("/GameMenu/ui/background/level4_demo.gif"))));
        level1IntroduceIcon = new ImageIcon(Objects.requireNonNull
                (getClass().getResource("/GameMenu/ui/background/level1_introduce.png")));
        level2IntroduceIcon = new ImageIcon(Objects.requireNonNull
                (getClass().getResource("/GameMenu/ui/background/level2_introduce.png")));
        level3IntroduceIcon = new ImageIcon(Objects.requireNonNull
                (getClass().getResource("/GameMenu/ui/background/level3_introduce.png")));
        level4IntroduceIcon = new ImageIcon(Objects.requireNonNull
                (getClass().getResource("/GameMenu/ui/background/level4_introduce.png")));

        initButtons();
        initLevelIntroduce();
    }

    @Override
    protected void initButtons(){
        JLabel titleLabel = new JLabel();
        ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/background/levelTitle.png")));
        titleLabel.setIcon(image);
        titleLabel.setBounds(300, 10, 400, 120);
        add(titleLabel);

        JLabel prompt = new JLabel();
        ImageIcon promptIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/background/prompt.png")));
        prompt.setIcon(promptIcon);
        prompt.setBounds(705, 45, 212, 80 );
        add(prompt);

        ImageIcon level1ButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/level1_button.png")));
        level1Button = new MainButton(level1ButtonIcon);
        level1Button.setBounds(100, 130, level1Button.getPreferredSize().width, 66);
        level1Button.setIsBlinking(false);
        level1Button.addActionListener(e -> {
            mainFrame.musicStop();
            mainFrame.showPanel("Round1");
        });
        add(level1Button);

        ImageIcon level2ButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/level2_button.png")));
        level2Button = new MainButton(level2ButtonIcon);
        level2Button.setBounds(100, 215, level2Button.getPreferredSize().width, 66);
        level2Button.setIsBlinking(false);
        level2Button.addActionListener(e -> {
            mainFrame.musicStop();
            mainFrame.showPanel("Round2");
        });
        add(level2Button);

        ImageIcon level3ButtonICon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/level3_button.png")));
        level3Button = new MainButton(level3ButtonICon);
        level3Button.setBounds(100, 300, level3Button.getPreferredSize().width, 66);
        level3Button.setIsBlinking(false);
        level3Button.addActionListener(e -> {
            mainFrame.musicStop();
            mainFrame.showPanel("Round3");
        });
        add(level3Button);

        ImageIcon level4ButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/level4_button.png")));
        level4Button = new MainButton(level4ButtonIcon);
        level4Button.setBounds(100, 385, level4Button.getPreferredSize().width, 66);
        level4Button.setIsBlinking(false);
        level4Button.addActionListener(e -> {
            mainFrame.musicStop();
            mainFrame.showPanel("Round4");
        });
        add(level4Button);

        ImageIcon returnButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/return_button.png")));
        returnButton = new MainButton(returnButtonIcon);
        returnButton.setBounds(170, 470, returnButton.getPreferredSize().width, 66);
        returnButton.setIsBlinking(false);
        returnButton.addActionListener(e -> mainFrame.showPanel("MainMenu"));
        add(returnButton);
    }

    private void initLevelIntroduce(){
        JLabel levelDemo = new JLabel();
        JLabel levelIntroduce = new JLabel();

        if (selectedIndex == 0){
            selectedDemoIcon = level1DemoIcon;
            selectedIntroduceIcon = level1IntroduceIcon;
        } else if (selectedIndex == 1){
            selectedDemoIcon = level2DemoICon;
            selectedIntroduceIcon = level2IntroduceIcon;
        } else if (selectedIndex == 2) {
            selectedDemoIcon = level3DemoICon;
            selectedIntroduceIcon = level3IntroduceIcon;
        } else if (selectedIndex == 3 || selectedIndex == 4) {
            selectedDemoIcon = level4DemoIcon;
            selectedIntroduceIcon = level4IntroduceIcon;
        }

        levelIntroduce.setIcon(selectedIntroduceIcon);
        levelIntroduce.setBounds(380, 130, 520, 320);
        add(levelIntroduce);
        setComponentZOrder(levelIntroduce, 1);

        levelDemo.setIcon(selectedDemoIcon);
        levelDemo.setBounds(380, 190, 520, 260);
        add(levelDemo);
        setComponentZOrder(levelDemo, 0);

        revalidate();
        repaint();
    }

    private ImageIcon getNewImageIcon(ImageIcon imageIcon){
        int desiredWidth = 520;
        int desiredHeight = 260;
        Image resizedImage = imageIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_REPLICATE);
        return new ImageIcon(resizedImage);
    }

    /**
     * 更新选中按钮的索引值。
     *
     * @param delta 索引值的增量。
     */
    @Override
    protected void updateSelectedIndex(int delta){
        selectedIndex += delta;
        int buttonCount = 5;
        selectedIndex = (selectedIndex + buttonCount) % buttonCount; // 循环更新选中按钮索引
        updateButtonSelection();
        initLevelIntroduce();
    }

    /**
     * 更新按钮的选中状态。
     */
    @Override
    protected void updateButtonSelection(){
        // 更新按钮选中状态
        level1Button.setSelected(selectedIndex == 0); // 更新第一关按钮的选中状态
        level2Button.setSelected(selectedIndex == 1); // 更新第二关按钮的选中状态
        level3Button.setSelected(selectedIndex == 2); // 更新第三关按钮的选中状态
        level4Button.setSelected(selectedIndex == 3); // 更新第四关按钮的选中状态
        returnButton.setSelected(selectedIndex == 4); // 更新返回按钮的选中状态

        switch (selectedIndex){
            case 0:
                selectedButton = level1Button;
                break;
            case 1:
                selectedButton = level2Button;
                break;
            case 2:
                selectedButton = level3Button;
                break;
            case 3:
                selectedButton = level4Button;
                break;
            case 4:
                selectedButton = returnButton;
                break;
        }
        repaint();
    }

    /**
     * 执行选中按钮的操作。
     */
    @Override
    protected void performSelectedAction(){
        if (selectedIndex == 0){
            level1Button.doClick();
        } else if (selectedIndex == 1) {
            level2Button.doClick();
        } else if (selectedIndex == 2) {
            level3Button.doClick();
        } else if (selectedIndex == 3) {
            level4Button.doClick();
        } else if (selectedIndex == 4){
            returnButton.doClick();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            updateSelectedIndex(-1); // 向上移动选中的按钮
        } else if (key == KeyEvent.VK_DOWN) {
            updateSelectedIndex(1); // 向下移动选中的按钮
        } else if (key == KeyEvent.VK_ENTER) {
            performSelectedAction(); // 执行选中按钮的动作
        }
    }
}