package GameMenu.menu.BackgroundSettings;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 一个自定义的面板类，用于显示带有背景图片的面板。
 * 继承自JPanel类。
 */
public class ImageBackgroundPanel extends JPanel {
    private Image backgroundImage;

    /**
     * 构造一个ImageBackgroundPanel实例，并加载指定路径的背景图片。
     *
     * @param imagePath 背景图片的文件路径
     */
    // 构造函数，用于加载背景图片
    public ImageBackgroundPanel(URL imagePath) {
        try {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写夫欸的paintComponent方法，用于绘制面板的背景图片。
     *
     * @param g 绘图上下文
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制背景图片
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}

