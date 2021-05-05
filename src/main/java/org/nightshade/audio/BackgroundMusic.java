package org.nightshade.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BackgroundMusic {

    private MediaPlayer mediaPlayer;
    private double volume = 100.0f;
    private int volumeDecrementTime = 30;
    private double volumeStepAmount = 100.0f;

    /**
     * Initialises the class to set up the MediaPlayer object
     */
    public BackgroundMusic() {
        File soundFile = new File("src/main/resources/audio/jump_01.mp3");
        Media media = new Media(soundFile.toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
    }

    /**
     * This method takes a music file and an initial volume and begins to play it with the given settings.
     * @param file   The music file to be played
     * @param volumeIn The initial volume of the music
     */
    public void startBackgroundMusic(File file, double volumeIn) {
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volumeIn);
        mediaPlayer.setAutoPlay(true);
        //mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void startBackgroundMusic(File file) {
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * This method takes the currently playing music and fades it out and into a new file.
     * The volume is the same at the start and at the end.
     * @param file                  The music file to be played
     * @param currentVolume         The volume to fade from and to
     * @throws InterruptedException
     */
    public void fadeIntoNewTrack(File file, float currentVolume) throws InterruptedException {
        for (float i = currentVolume; i >= 0; i-=(currentVolume/volumeStepAmount)) {
            TimeUnit.MILLISECONDS.sleep(volumeDecrementTime);
            mediaPlayer.setVolume(i);
        }
        startBackgroundMusic(file, 0.0f);
        for (float i = 0; i <= currentVolume; i+=(currentVolume/volumeStepAmount)) {
            TimeUnit.MILLISECONDS.sleep(volumeDecrementTime);
            mediaPlayer.setVolume(i);
        }
        mediaPlayer.setVolume(currentVolume);
    }

    /**
     * This method fades out the currently playing music.
     * @param currentVolume         The current volume level of the music
     * @throws InterruptedException
     */
    public void fadeOutOfTrack(float currentVolume) throws InterruptedException {
        for (float i = currentVolume; i >= 0; i-=(currentVolume/volumeStepAmount)) {
            TimeUnit.MILLISECONDS.sleep(volumeDecrementTime);
            mediaPlayer.setVolume(i);
        }
        mediaPlayer.stop();
    }

    public void setVolume(double volumeIn) {
        this.volume = volumeIn;
        mediaPlayer.setVolume(volumeIn);
    }
    /**
     * This method stops all the music from playing.
     */
    public void stopMusic(){
        mediaPlayer.stop();
    }
}
