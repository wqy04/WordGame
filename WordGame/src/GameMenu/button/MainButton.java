package GameMenu.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

/**
 * 自定义的开始按钮类，继承类JButton
 * 该按钮有闪烁效果和自定义样式
 */
public class MainButton extends JButton {
    // 设置按钮宽度、闪烁时间和闪烁颜色的常量
    private static final int BUTTON_HEIGHT = 66;
    private static final int BLINK_DURATION = 500;
    private static final Color BLINK_COLOR = Color.RED;

    private boolean blinking; // 标记按钮是否处于闪烁状态
    private boolean isBlinking = true; // 标记是否需要闪烁
    private ImageIcon buttonIcon; // 按钮图标
    private final Timer blinkTimer; // 用于实现闪烁效果的计时器

    /**
     * 构造一个MainButton实例
     *
     * @param buttonIcon 按钮的图标
     */
    public MainButton(ImageIcon buttonIcon){
        super();
        this.buttonIcon = buttonIcon;
        setPreferredSize(new Dimension(calculateButtonWidth(), BUTTON_HEIGHT));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        // 初始化闪烁计时器，并设置闪烁动作
        blinkTimer = new Timer(BLINK_DURATION, new ActionListener() {
            private boolean isVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                repaint();
            }
        });

        blinkTimer.setRepeats(true);
    }

    /**
     * 根据固定的Button高度来计算Button的宽度
     *
     * @return Button的宽度
     */
    private int calculateButtonWidth(){
        if (buttonIcon != null){
            double aspectRatio = (double) buttonIcon.getIconWidth() / buttonIcon.getIconHeight();
            return (int) (BUTTON_HEIGHT * aspectRatio);
        }
        return 200;
    }

    /**
     * 重写父类的paintComponent方法，用于绘制按钮的样式
     *
     * @param g 绘图上下文
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // 如果按钮被选中且处于闪烁状态，绘制一个红色的边框
        if (isSelected() && blinking){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(BLINK_COLOR);

            // 增加边框宽度
            float borderWidth = 3.0f; // 可以根据需要调整这个值
            g2d.setStroke(new BasicStroke(borderWidth));

            // 圆角矩形的圆角大小
            int arcWidth = 25;
            int arcHeight = 25;

            // 创建并绘制圆角矩形
            RoundRectangle2D roundRectangle = new RoundRectangle2D.Float(
                    0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
            g2d.draw(roundRectangle);

            g2d.dispose();
        }

        // 绘制按钮图标
        if (buttonIcon != null) {
            int x = (getWidth() - buttonIcon.getIconWidth()) / 2;
            int y = (getHeight() - buttonIcon.getIconHeight()) / 2;
            buttonIcon.paintIcon(this, g, x, y);
        }
    }

    /**
     * 重写父类的setPreferredSize方法，确保按钮的大小适应图标。
     *
     * @param preferredSize 按钮的首选大小
     */
    @Override
    public void setPreferredSize(Dimension preferredSize){
        super.setPreferredSize(new Dimension(calculateButtonWidth(), BUTTON_HEIGHT));
        if (buttonIcon != null){
            buttonIcon = new ImageIcon(buttonIcon.getImage().getScaledInstance(
                    calculateButtonWidth(), BUTTON_HEIGHT, Image.SCALE_SMOOTH
            ));
        }
    }

    /**
     * 设置按钮是否闪烁。
     *
     * @param isBlinking 指示按钮是否闪烁
     */
    public void setIsBlinking(boolean isBlinking){
        this.isBlinking = isBlinking;
    }

    /**
     * 设置按钮的选中状态，开始或停止闪烁。
     *
     * @param selected  指示按钮是否被选中
     */
    @Override
    public void setSelected(boolean selected){
        super.setSelected(selected);
        if (isBlinking) {
            if (selected) {
                startBlinking();
            } else {
                stopBlinking();
            }
        }
    }

    /**
     * 开始按钮的闪烁效果。
     */
    private void startBlinking(){
        blinking = true;
        repaint();
        if (!blinkTimer.isRunning()){
            blinkTimer.start();
        }
    }

    /**
     * 停止按钮的闪烁效果。
     */
    private void stopBlinking(){
        blinking = false;
        repaint();
        if (blinkTimer.isRunning()){
            blinkTimer.stop();
            setBackground(null);
        }
    }
}
