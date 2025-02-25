package GameMenu.menu.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * 音频控制器的全局类，用于管理音频播放器的集合和音量设置。
 */
public class AudioController {
    private final List<SoundMusicPlayer> soundMusicPlayers = new ArrayList<>();

    /**
     * 将音频播放器添加到这个音频控制器的集合中。
     *
     * @param soundMusicPlayer 音频播放器对象
     */
    public void addSound(SoundMusicPlayer soundMusicPlayer){
        soundMusicPlayers.add(soundMusicPlayer);
    }

    /**
     * 为所有音效播放器设置音量。
     *
     * @param volume 音量值，范围从0.0到1.0
     */
    public void setVolumeForAll(float volume){
        for (SoundMusicPlayer soundMusicPlayer : soundMusicPlayers){
            soundMusicPlayer.setVolume(volume);
        }
    }

    public void stopAll(){
        for (SoundMusicPlayer soundMusicPlayer : soundMusicPlayers){
            soundMusicPlayer.stop();
        }
    }
}
