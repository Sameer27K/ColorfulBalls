import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    Clip clip;
    AudioInputStream audioStream;
    String filePath;
    boolean isPaused = false;
    Long currentFrame;

    public MusicPlayer(String filePath) {
        this.filePath = filePath;
        loadClip();
    }

    private void loadClip() {
        try {
            audioStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pause() {
        if (clip.isRunning()) {
            currentFrame = clip.getMicrosecondPosition();
            clip.stop();
            isPaused = true;
        }
    }

    public void resume() {
        if (isPaused) {
            clip.setMicrosecondPosition(currentFrame);
            clip.start();
            isPaused = false;
        }
    }

    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void restart() {
        clip.stop();
        clip.close();
        loadClip();
        playLoop();
    }
}
