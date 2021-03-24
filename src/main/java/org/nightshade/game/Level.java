package org.nightshade.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Level {
    private int width;
    private int blockHeight;
    private ArrayList<ArrayList<Node>> nodeArrayLists;
    private ArrayList<Sprite> platformSprites;
    private ArrayList<Sprite> lavaSprites;
    private ArrayList<Sprite> groundSprites;
    private ArrayList<Sprite> endSprites;
    private ArrayList<Enemy> enemies;
    private ArrayList<MovingPlatform> movingPlatforms;

    Image grass = new Image("img/game/dark-grass.png");
    Image water = new Image("img/game/lava/lava-1.png");
    Image ground = new Image("img/game/dirt.png");
    Image end = new Image("img/game/end.png");


    public Level(int width) {
        this.width = width;
        this.blockHeight = 12;

        this.nodeArrayLists = new ArrayList<>();
        for (int i = 0; i < blockHeight; i ++) {
            ArrayList<Node> nodes = new ArrayList<>();
            int count = 0;
            while (count < width) {
                Node node = Node.getRandomNode(i, count, width);
                nodes.add(node);
                switch (node) {
                    case PLATFORM:
                        nodes.add(Node.PLATFORM);
                        count += 2;
                        break;
                    case MOVING_PLATFORM:
                        nodes.add(Node.MOVING_PLATFORM);
                        count += 2;
                        break;
                    case LAVA:
                        int length = ThreadLocalRandom.current().nextInt(0, 2 + 1);
                        for (int j = 0; j < length; j ++) {
                            nodes.add(Node.LAVA);
                        }
                        count += length;
                        break;
                    default:
                        count++;
                        break;
                }
            }
            nodeArrayLists.add(nodes);
        }

        this.platformSprites = new ArrayList<>();
        for (int i = 0; i < this.blockHeight; i++) {
            ArrayList<Node> nodes = nodeArrayLists.get(i);
            for (int j = 0; j < this.width; j++) {
                if (nodes.get(j) == Node.PLATFORM) {
                    Sprite sprite = new Sprite(grass, j * 60, i * 60);
                    platformSprites.add(sprite);
                }
            }
        }

        this.lavaSprites = new ArrayList<>();
        this.groundSprites = new ArrayList<>();
        this.endSprites = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.movingPlatforms = new ArrayList<>();
    }

    public ArrayList<Sprite> getLavaSprites() {
        return this.lavaSprites;
    }

    public ArrayList<Sprite> getEndSprites() {
        return this.endSprites;
    }

    public ArrayList<Sprite> getPlatformSprites() {
        return this.platformSprites;
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public ArrayList<MovingPlatform> getMovingPlatforms() {
        return this.movingPlatforms;
    }

    public ArrayList<MovingPlatform> createMovingPlatforms() {

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < width; j++) {
                if (nodeArrayLists.get(i).get(j) == Node.MOVING_PLATFORM) {
                    int speed = ThreadLocalRandom.current().nextInt(0, (5) + 1);
                    int randomDirectionInt = ThreadLocalRandom.current().nextInt(0, (1) + 1);
                    boolean direction = randomDirectionInt == 1;
                    if (nodeArrayLists.get(i).get(j - 1) == Node.MOVING_PLATFORM) {
                        speed = movingPlatforms.get(movingPlatforms.size() - 1).getSpeed();
                        direction = movingPlatforms.get(movingPlatforms.size() - 1).getDirection();
                    }
                    movingPlatforms.add(new MovingPlatform(j * 60, i * 60, speed, direction));
                }
            }
        }

        return movingPlatforms;

    }

    public ArrayList<Enemy> createEnemies() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < width; j++) {
                if (nodeArrayLists.get(i).get(j) == Node.ENEMY) {
                    int speed = ThreadLocalRandom.current().nextInt(0, (5) + 1);
                    enemies.add(new Enemy(speed, j * 60, i * 60));
                }
            }
        }
        return enemies;
    }

    public ArrayList<Sprite> createLavaSprites() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < width; j++) {
                if (nodeArrayLists.get(i).get(j) == Node.LAVA) {
                    lavaSprites.add(new Sprite(water, j * 60, i * 60));
                }
            }
        }
        return lavaSprites;
    }

    public ArrayList<Sprite> createGroundSprites() {
        for (int j = 0; j < width; j++) {
            if (nodeArrayLists.get(11).get(j) == Node.GROUND) {
                groundSprites.add(new Sprite(ground, j * 60, 11 * 60));
            }
        }
        return groundSprites;
    }

    public ArrayList<Sprite> createEndSprites() {
        for (int i = 0; i < 12; i++) {
            endSprites.add(new Sprite(end, (width - 1) * 60, i * 60));
        }
        return endSprites;
    }
}
