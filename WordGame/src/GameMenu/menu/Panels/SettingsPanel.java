package GameMenu.menu.Panels;

import GameMenu.button.MainButton;
import GameMenu.button.SettingSlider;
import GameMenu.menu.Music.AudioController;
import GameMenu.menu.Music.BackgroundMusicPlayer;
import GameMenu.menu.BackgroundSettings.BaseMenuPanel;
import GameMenu.menu.Frame.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Objects;

/**
 * 设置面板是游戏菜单的一个面板，用于调整游戏设置。
 * 继承自BaseMenuPanel类。
 */
public class SettingsPanel extends BaseMenuPanel {
    private final MainFrame mainFrame;
    private MainButton musicVolumeButton;
    private MainButton soundVolumeButton;
    private MainButton returnButton;
    private MainButton creditsButton;
    private JSlider musicVolumeSlider;
    private JSlider soundVolumeSlider;
    private JSlider selectedSlider;
    private final Timer sliderMoveTimer; // 新增定时器属性
    private int currentKeyPressed = -1; // 当前按下的键
    private final BackgroundMusicPlayer musicPlayer;
    private final AudioController audioController;

    /**
     * 创建一个SettingsPanel对象。
     * @param mainFrame 主窗体对象，用于管理界面切换。
     */
    public SettingsPanel(MainFrame mainFrame, URL imageURL){
        super(imageURL);
        this.mainFrame = mainFrame;
        musicPlayer = BackgroundMusicPlayer.getInstance();
        audioController = mainFrame.getAudioController();

        // 初始化计时器
        sliderMoveTimer = new Timer(30, e -> updateSliderValue());
    }

    /**
     * 初始化设置面板中的按钮和滑块。
     */
    @Override
    protected void initButtons(){
        musicVolumeSlider = new JSlider(JSlider.HORIZONTAL,0, 100, 80);
        musicVolumeSlider.setUI(new SettingSlider(musicVolumeSlider));
        musicVolumeSlider.setBounds(530, 90, 300, 50);
        add(musicVolumeSlider);

        soundVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        soundVolumeSlider.setUI(new SettingSlider(soundVolumeSlider));
        soundVolumeSlider.setBounds(530, 190, 300, 50);
        add(soundVolumeSlider);


        ImageIcon musicVolumeButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/musicVolume_button.png")));
        musicVolumeButton = new MainButton(musicVolumeButtonIcon);
        musicVolumeButton.setBounds(170, 80, musicVolumeButton.getPreferredSize().width, 66);
        musicVolumeButton.setIsBlinking(false);
        musicVolumeButton.addActionListener(e -> {
            // 设置滑块被选中状态
            boolean selected = musicVolumeButton.isSelected();
            musicVolumeSlider.setEnabled(!selected);
        });
        add(musicVolumeButton);

        ImageIcon soundVolumeButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/soundVolume_button.png")));
        soundVolumeButton = new MainButton(soundVolumeButtonIcon);
        soundVolumeButton.setBounds(170, 180, soundVolumeButton.getPreferredSize().width, 66);
        soundVolumeButton.setIsBlinking(false);
        soundVolumeButton.addActionListener(e ->  {
                // 设置按钮被选中状态
                boolean selected = soundVolumeButton.isSelected();
                soundVolumeSlider.setEnabled(!selected);
        });
        add(soundVolumeButton);

        ImageIcon creditsButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/credits_button.png")));
        creditsButton = new MainButton(creditsButtonIcon);
        creditsButton.setBounds(170, 280, creditsButton.getPreferredSize().width, 66);
        creditsButton.setIsBlinking(false);
        creditsButton.addActionListener(e -> mainFrame.showPanel("Credits"));
        add(creditsButton);

        ImageIcon returnButtonIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/button/return_button.png")));
        returnButton = new MainButton(returnButtonIcon);
        returnButton.setBounds(170, 380, returnButton.getPreferredSize().width, 66);
        returnButton.setIsBlinking(false);
        returnButton.addActionListener(e -> mainFrame.showPanel("MainMenu"));
        add(returnButton);
    }

    /**
     * 更新滑块的值。
     */
    private void updateSliderValue(){
        if (selectedSlider != null && (currentKeyPressed == KeyEvent.VK_RIGHT || currentKeyPressed == KeyEvent.VK_LEFT)){
            int value = selectedSlider.getValue();
            int minValue = selectedSlider.getMinimum();
            int maxValue = selectedSlider.getMaximum();

            // 每次移动的步长
            int SLIDER_STEP = 1;
            if (currentKeyPressed == KeyEvent.VK_LEFT){
                selectedSlider.setValue(Math.max(value - SLIDER_STEP, minValue));
                selectedSlider.repaint();
                setMusicVolume(selectedSlider.getValue()); // 更新音乐音量
            } else if (currentKeyPressed == KeyEvent.VK_RIGHT) {
                selectedSlider.setValue(Math.min(value + SLIDER_STEP, maxValue));
                selectedSlider.repaint();
                setMusicVolume(selectedSlider.getValue()); // 更新音乐音量
            }
        }
    }

    /**
     * 设置音乐的音量。
     * @param volume 音量值，范围从0到100。
     */
    private void setMusicVolume(float volume){
        // 检查音量值是否超过范围
        float normalizedVolume = volume / 100.0f;

        // 将标准化的音量值应用到音乐播放器
        if (selectedSlider == musicVolumeSlider){
            musicPlayer.setVolume(normalizedVolume);
        } else if (selectedSlider == soundVolumeSlider) {
            audioController.setVolumeForAll(normalizedVolume);
        }
    }

    /**
     * 处理键盘按下事件。
     * @param e 键盘事件对象。
     */
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        currentKeyPressed = key;

        if (key == KeyEvent.VK_UP) {
            updateSelectedIndex(-1); // 向上移动选中的按钮
        } else if (key == KeyEvent.VK_DOWN) {
            updateSelectedIndex(1); // 向下移动选中的按钮
        } else if (key == KeyEvent.VK_ENTER) {
            performSelectedAction(); // 执行选中按钮的动作
        }

        if (selectedButton == musicVolumeButton || selectedButton == soundVolumeButton){
            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT){
                if (!sliderMoveTimer.isRunning()){
                    sliderMoveTimer.start();
                }
            }
        }
    }

    /**
     * 更新选中按钮的索引值。
     *
     * @param delta 索引值的增量。
     */
    @Override
    protected void updateSelectedIndex(int delta){
        selectedIndex += delta;
        int buttonCount = 4;
        selectedIndex = (selectedIndex + buttonCount) % buttonCount; // 循环更新选中按钮索引
        updateButtonSelection();
    }

    /**
     * 更新按钮的选中状态。
     */
    @Override
    protected void updateButtonSelection(){
        // 更新按钮选中状态
        musicVolumeButton.setSelected(selectedIndex == 0); // 更新背景音乐背景按钮的选中状态
        soundVolumeButton.setSelected(selectedIndex == 1); // 更新音效音量按钮的选中状态
        creditsButton.setSelected(selectedIndex == 2); // 更新制作者名单按钮的选中状态
        returnButton.setSelected(selectedIndex == 3); // 更新返回按钮的选中状态

        switch (selectedIndex){
            case 0:
                selectedButton = musicVolumeButton;
                selectedSlider = musicVolumeSlider;
                break;
            case 1:
                selectedButton = soundVolumeButton;
                selectedSlider = soundVolumeSlider;
                break;
            case 2:
                selectedButton = creditsButton;
                break;
            case 3:
                selectedButton = returnButton;
        }
        repaint();
    }

    /**
     * 执行选中按钮的操作。
     */
    @Override
    protected void performSelectedAction(){
        if (selectedIndex == 0){
            musicVolumeButton.doClick(); // 模拟点击开始按钮
        } else if (selectedIndex == 1) {
            soundVolumeButton.doClick(); // 模拟点击设置按钮
        } else if (selectedIndex == 2) {
            creditsButton.doClick(); // 模拟点击退出按钮
        } else if (selectedIndex == 3) {
            returnButton.doClick();
        }
    }

    /**
     * 处理键盘释放事件。
     * @param e 键盘事件对象。
     */
    @Override
    public void keyReleased(KeyEvent e){
        // 当键盘左键或右键释放时停止定时器
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (sliderMoveTimer.isRunning()) {
                sliderMoveTimer.stop();
            }
        }
        currentKeyPressed = -1; // 重置当前按下的键
    }
}
