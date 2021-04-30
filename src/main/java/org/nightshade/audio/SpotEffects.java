package org.nightshade.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SpotEffects {


    /*
     * This method starts playing the specified music file
     * @param file     The music file to be played
     * @param autoPlay Boolean for determining if the music should be played immediately
     * @param volume   The volume for the music file to be played at
     */
    public void playSound(File file, boolean autoPlay, double volume) {

        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.setAutoPlay(autoPlay);
    }




    /**
     * This method plays the specified music file until it has finished
     * @param file     The music file to be played
     * @param autoPlay Boolean for determining if the music should be played immediately
     * @param volume   The volume for the music file to be played at
     */
    public void playSoundUntilEnd(File file, boolean autoPlay, double volume) {

        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
        });

        mediaPlayer.setAutoPlay(autoPlay);
    }
}
