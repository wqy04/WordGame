package GameMenu.menu.Music;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * 背景音乐播放器类，用于加载和播放背景音乐文件。
 */
public class BackgroundMusicPlayer {
    public static BackgroundMusicPlayer instance;
    private Clip clip;

    /**
     * 构造一个BackgroundMusicPlayer实例。
     */
    public BackgroundMusicPlayer(){
    }

    /**
     * 获取BackgroundMusicPlayer的单例实例。
     *
     * @return BackgroundMusicPlayer的单例实例
     */
    public static BackgroundMusicPlayer getInstance(){
        if (instance == null){
            instance = new BackgroundMusicPlayer();
        }
        return instance;
    }

    /**
     * 加载背景音乐文件。
     *
     * @param resourcePath 背景音乐文件的路径
     */
    public void loadMusic(String resourcePath){
        try {
            URL musicURL = getClass().getResource(resourcePath);
            if (musicURL != null) {
                try {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicURL);
                    // 加载和播放音乐
                    AudioFormat format = audioStream.getFormat();

                    // 创建音频数据行
                    DataLine.Info info = new DataLine.Info(Clip.class, format);
                    clip = (Clip) AudioSystem.getLine(info);

                    // 打开音频数据流
                    clip.open(audioStream);
                } catch (UnsupportedAudioFileException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Resource not found: " + resourcePath);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 播放背景音乐。
     */
    public void play(){
        if (clip != null){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop(){
        if (clip != null && clip.isRunning()){
            clip.stop();
            clip.setFramePosition(0);
        }
    }

    /**
     * 设置背景音乐的音量。
     *
     * @param volume 音量值，范围从0.0到1.0
     */
    public void setVolume(float volume){
        if (clip != null){
            FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB;
            if (volume <= 0.0f){
                dB = floatControl.getMinimum();
            } else if (volume >= 1.0f) {
                dB = floatControl.getMaximum();
            } else {
                dB = (float) (20 * Math.log10(volume));
                dB = Math.max(dB, floatControl.getMinimum());
                dB = Math.min(dB, floatControl.getMaximum());
            }
            floatControl.setValue(dB);
        }
    }
}
