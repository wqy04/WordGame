package GameMenu.menu.BackgroundSettings;

import GameMenu.button.MainButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Objects;

/**
 * 抽象的基础菜单面板类，继承自ImageBackgroundPanel，并实现了KeyListener接口。
 * 该类提供了菜单面板的基本功能和抽象方法，用于派生具体的菜单面板类。
 */
public abstract class BaseMenuPanel extends ImageBackgroundPanel implements KeyListener {
    protected MainButton selectedButton;
    protected int selectedIndex;
    protected Image sideImage;

    /**
     *构造一个BaseMenuPanel实例。
     *
     * @param imgURL 背景图像的文件路径
     */
    public BaseMenuPanel(URL imgURL){
        super(imgURL);
        setLayout(null);
        setFocusable(true);

        sideImage = new ImageIcon(Objects.requireNonNull(getClass().getResource
                ("/GameMenu/ui/background/cat.png"))).getImage();
        selectedIndex = 0; // 初始选中第一个按钮

        initButtons();
        updateButtonSelection(); // 使用 updateButtonSelection 方法更新按钮选中状态

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                resetFocusAndSelection();
            }
        });
        addKeyListener(this);
    }

    /**
     * 重写父类的paintComponent方法，用于绘制菜单面板的图像。
     *
     * @param g 绘图上下文
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (selectedButton != null && selectedButton.isSelected() && sideImage != null){
            int buttonHeight = selectedButton.getHeight();
            double aspectRatio = (double) sideImage.getWidth(null) / sideImage.getHeight(null);
            int imageWidth = (int) (buttonHeight * aspectRatio);

            int x = selectedButton.getX() - imageWidth - 2;
            int y = selectedButton.getY();
            g.drawImage(sideImage, x, y, imageWidth, buttonHeight, this);
        }
    }

    /**
     * 初始化菜单面板的按钮。
     * 子类需要实现该方法以创建和添加按钮到菜单面板。
     */
    protected abstract void initButtons();

    /**
     * 更新按钮的选中状态。
     * 子类需要实现该方法以根据选中的按钮更新按钮的选中按钮的实例。
     */
    protected abstract void updateButtonSelection();

    /**
     * 执行选中按钮的操作。
     * 子类需要实现该方法以定义选中后按钮的具体操作。
     */
    protected abstract void performSelectedAction();

    /**
     * 更新选中索引
     *
     * @param delta 索引的增量值
     */
    protected void updateSelectedIndex(int delta) {
        // 通用的选中索引更新逻辑
    }

    /**
     * 重置焦点和选中状态。
     * 在组件显示时调用该方法，用于重新设置焦点并更新按钮的选中状态。
     */
    protected void resetFocusAndSelection() {
        requestFocusInWindow();
        updateButtonSelection();
    }

    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyReleased(KeyEvent e){
    }

    @Override
    public void addNotify(){
        super.addNotify();
        requestFocusInWindow();
    }
}
