package GameMenu.util;

import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

public class GameUtil {

    // 根据路径读取图片，自动处理文件系统路径和类路径资源
    public static Image LoadBufferedImages(String imgPath) {
        URL resourceUrl = GameUtil.class.getResource(imgPath);
        if (resourceUrl != null) {
            // 资源存在于类路径中
            return new ImageIcon(resourceUrl).getImage();
        } else {
            // 尝试作为文件系统路径处理
            return Toolkit.getDefaultToolkit().createImage(imgPath);
        }
    }
}
