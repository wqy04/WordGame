package GameMenu.button;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

/**
 * 自定义的设置滑块类，继承自BasicSliderUI。
 * 该滑块具有自定义样式和鼠标事件处理。
 */
public class SettingSlider extends BasicSliderUI {
    private final Image thumbImage;

    /**
     * 构造一个SettingSlider实例。
     *
     * @param slider 要设置的滑块组件
     */
    public SettingSlider(JSlider slider){
        super(slider);

        // 加载滑块图片
        thumbImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/GameMenu/ui/button/slide_thump_1.png"))).getImage();

        slider.setOpaque(false);
        slider.setDoubleBuffered(true);
        slider.setFocusable(false);

        // 添加鼠标事件监听器
        slider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // 当鼠标释放时，将焦点返回给SettingsPanel或其他适当的组件
                Container parent = slider.getParent();
                if (parent != null) {
                    parent.requestFocusInWindow();
                }
            }
        });

    }

    /**
     * 绘制Slider的滑块样式
     *
     * @param g 绘图上下文
     * @param image 自定义样式的图片
     */
    private void drawThumb(Graphics g, Image image){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int thumbWidth = 20;
        int thumbHeight = 20;
        int x = thumbRect.x + (thumbRect.width - thumbWidth) / 2;
        int y = thumbRect.y + (thumbRect.height - thumbHeight) / 2;
        g2d.drawImage(image, x, y, thumbWidth, thumbHeight, slider);
        g2d.dispose();
    }

    /**
     * 重写父类的paintThumb方法，用于绘制滑块的样式。
     *
     * @param g 绘图上下文
     */
    @Override
    public void paintThumb(Graphics g){
        if (thumbImage != null) {
            drawThumb(g, thumbImage);
        } else {
            super.paintThumb(g);
        }
    }

    /**
     * 重写父类的paintTrack方法，用于绘制滑块轨道的样式。
     *
     * @param g 绘图上下文
     */
    @Override
    public void paintTrack(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Rectangle trackBounds = trackRect;

        g2d.setColor(Color.decode("#4978A0"));
        g2d.fillRect(trackBounds.x, trackBounds.y + trackBounds.height / 2 -3, trackBounds.width, 6);

    }

    /**
     * 重写父类的paintTicks方法，用于绘制滑块刻度。
     *
     * @param g 绘图上下文
     */
    @Override
    public void paintTicks(Graphics g){
        super.paintTicks(g);
    }
}
