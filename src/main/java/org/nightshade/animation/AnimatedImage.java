package org.nightshade.animation;

import javafx.scene.image.Image;

public class AnimatedImage {
    public Image[] frames;
    public double duration;

    public Image getFrame(double time) {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public double getWidth() { return frames[0].getWidth(); }

    public double getHeight() { return frames[0].getHeight(); }
}
