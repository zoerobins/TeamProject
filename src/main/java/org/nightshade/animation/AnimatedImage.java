package org.nightshade.animation;

import javafx.scene.image.Image;

public class AnimatedImage {

    private Image[] frames;
    private double duration;

    public void setFrames(Image[] frames) {
        this.frames = frames;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Image[] getFrames() {
        return frames;
    }

    public double getDuration() {
        return duration;
    }

    public Image getFrame(double time) {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public double getWidth() { return frames[0].getWidth(); }

    public double getHeight() { return frames[0].getHeight(); }
}
