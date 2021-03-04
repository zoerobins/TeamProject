package org.nightshade.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class SpotEffects{
    Random rand = new Random();
    public void jumpSound() throws URISyntaxException {
        int rand_int = rand.nextInt(7);
        String audioName = "src/main/resources/audio/jump_0" + rand_int + ".mp3";
        File starting = new File(System.getProperty("user.dir"));
        File audioPath = new File(starting, audioName);
        System.out.println("AudioName: " + audioName);
        Media jump = new Media(audioPath.toURI().toString());
        MediaPlayer jumpPlayer = new MediaPlayer(jump);
        jumpPlayer.setAutoPlay(true);
    }
}
