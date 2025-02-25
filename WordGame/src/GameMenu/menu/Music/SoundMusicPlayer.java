package GameMenu.menu.Music;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * 音效音乐播放器类，用于加载和播放音效文件。
 */
public class SoundMusicPlayer {
    private Clip clip;

    /**
     * 构造一个SoundMusicPlayer实例，并加载指定的音效文件。
     *
     * @param resourcePath 音效文件的路径
     */
    public SoundMusicPlayer(String resourcePath){
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
     * 播放音效。
     */
    public void play(){
        if (clip != null){
            if (!clip.isRunning()) {
                clip.setFramePosition(0);
                clip.start();
            }
        }
    }

    /**
     * 停止播放音效。
     */
    public void stop(){
        if (clip != null && clip.isRunning()){
            clip.stop();
            clip.setFramePosition(0);
        }
    }

    public void pause(){
        if(clip != null&& clip.isRunning()){
            clip.stop();
        }
    }

    /**
     * 设置音效的音量。
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
