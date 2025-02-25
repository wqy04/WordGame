package GameMenu.menu.Frame;

import GameMenu.menu.Music.AudioController;
import GameMenu.menu.Music.BackgroundMusicPlayer;
import GameMenu.menu.Panels.CreditsPanel;
import GameMenu.menu.Panels.LevelSelectionPanel;
import GameMenu.menu.Panels.MainMenuPanel;
import GameMenu.menu.Panels.SettingsPanel;
import Round1.main.Round1Panel;
import Round2.main.Round2Panel;
import Round3.main.Round3Panel;
import Round4.main.Round4Panel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 游戏主窗口类，继承自JFrame类，用于显示游戏菜单和面板。
 */
public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel containerPanel = new JPanel(cardLayout);
    private final BackgroundMusicPlayer musicPlayer; // 背景音乐
    private final AudioController audioController = new AudioController();

    /**
     * 构造一个MainFrame实例。
     * 在构造函数中设置窗口的大小、位置，并加载菜单面板和背景音乐。
     */
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        musicPlayer = BackgroundMusicPlayer.getInstance();
        musicPlayer.loadMusic("/GameMenu/ui/music/background_music.wav");

        URL menuImageURL = getClass().getResource("/GameMenu/ui/background/background.jpg");
        containerPanel.add(new MainMenuPanel(this, menuImageURL), "MainMenu");

        URL levelImageURL = getClass().getResource("/GameMenu/ui/background/setting.png");
        containerPanel.add(new LevelSelectionPanel(this, levelImageURL), "LevelSelection");
        containerPanel.add(new SettingsPanel(this, levelImageURL), "Settings");

        URL creditsImageUrl = getClass().getResource("/GameMenu/ui/background/credits.png");
        containerPanel.add(new CreditsPanel(this, creditsImageUrl), "Credits");

        add(containerPanel);
        showPanel("MainMenu");
    }
    public AudioController getAudioController(){
        return this.audioController;
    }

    /**
     * 显示指定名称的面板
     * 在显示面板之前，初始化背景音乐并播放。
     *
     * @param name 面板的名称
     */
    public void showPanel(String name) {
        switch (name) {
            case "Round1" -> {
                // 移除之前的 Round1 面板
                Component[] components = containerPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof Round1Panel) {
                        containerPanel.remove(component);
                        break;
                    }
                }

                // 创建新的 Round1 面板并添加到容器

                Round1Panel round1Panel = new Round1Panel(this);
                round1Panel.setName("Round1");
                containerPanel.add(round1Panel, "Round1");
                cardLayout.show(containerPanel, "Round1");
            }
            case "Round2" -> {
                // 移除之前的 Round2 面板
                Component[] components = containerPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof Round2Panel) {
                        containerPanel.remove(component);
                        break;
                    }
                }

                // 创建新的 Round2 面板并添加到容器
                Round2Panel round2Panel = new Round2Panel(this);
                round2Panel.setName("Round2");
                containerPanel.add(round2Panel, "Round2");
                cardLayout.show(containerPanel, "Round2");
            }
            case "Round3" -> {
                // 移除之前的 Round3 面板
                Component[] components = containerPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof Round3Panel) {
                        containerPanel.remove(component);
                        break;
                    }
                }

                // 创建新的 Round3 面板并添加到容器
                Round3Panel round3Panel = new Round3Panel(this);
                round3Panel.setName("Round3");
                containerPanel.add(round3Panel, "Round3");
                cardLayout.show(containerPanel, "Round3");
            }
            case "Round4" -> {
                // 移除之前的 Round4 面板
                Component[] components = containerPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof Round4Panel) {
                        containerPanel.remove(component);
                        break;
                    }
                }

                // 创建新的 Round4 面板并添加到容器
                Round4Panel round4Panel = new Round4Panel(this);
                round4Panel.setName("Round4");
                containerPanel.add(round4Panel, "Round4");
                cardLayout.show(containerPanel, "Round4");
            }
            default -> cardLayout.show(containerPanel, name);
        }
    }

    public String getCurrentPanelName() {
        // 获取容器内的所有组件
        Component[] components = containerPanel.getComponents();

        // 遍历所有组件
        for (Component component : components) {
            // 检查组件是否是 Round1Panel 或 Round2Panel 的实例并且是否可见
            if (component.isVisible()) {
                // 返回面板的名称
                return component.getName();
            }
        }

        // 如果未找到 Round1Panel 或 Round2Panel，则返回空字符串
        return null;
    }

    public void musicPlay(){
        musicPlayer.play();
    }

    public void musicStop(){
        musicPlayer.stop();
    }

    /**
     * 主方法，程序的入口。
     * 创建并显示游戏主窗口。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}


