package org.nightshade.multiplayer;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Level implements Serializable {
    private final ArrayList<Sprite> platformSprites;
    private final ArrayList<Sprite> lavaSprites;
    private final ArrayList<Sprite> groundSprites;
    private final ArrayList<Sprite> endSprites;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<MovingPlatform> movingPlatforms;

    Image grass = new Image("img/game/dark-grass.png");
    Image lava = new Image("img/game/lava/lava-1.png");
    Image ground = new Image("img/game/dirt.png");
    Image end = new Image("img/game/end.png");

    public Level(int width, ArrayList<ArrayList<Node>> nodeArrayLists) {
        int blockHeight = 12;

        this.platformSprites = new ArrayList<>();
        this.lavaSprites = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.movingPlatforms = new ArrayList<>();
        for (int i = 0; i < blockHeight; i++) {
            ArrayList<Node> nodes = nodeArrayLists.get(i);
            for (int j = 0; j < width; j++) {
                int x = j * 60;
                int y = i * 60;
                Node node = nodes.get(j);
                switch (node) {
                    case PLATFORM: {
                        Sprite sprite = new Sprite(grass, x, y);
                        platformSprites.add(sprite);
                        break;
                    }
                    case LAVA: {
                        Sprite sprite = new Sprite(lava, x, y);
                        lavaSprites.add(sprite);
                        break;
                    }
                    case RIGHT_ENEMY: {
                        int speed = ThreadLocalRandom.current().nextInt(0, 5 + 1);
                        Enemy enemy = new Enemy(speed, x, y,Direction.FORWARD);
                        enemies.add(enemy);
                        break;
                    }
                    case LEFT_ENEMY: {
                        int speed = ThreadLocalRandom.current().nextInt(0, 5 + 1);
                        Enemy enemy = new Enemy(speed, x, y,Direction.BACKWARD);
                        enemies.add(enemy);
                        break;
                    }
                    case RIGHT_MOVING_PLATFORM: {
                        int speed = ThreadLocalRandom.current().nextInt(0, 5 + 1);
                        Direction direction = Direction.FORWARD;
                        Node prevNode = nodes.get(j - 1);
                        if (prevNode == Node.RIGHT_MOVING_PLATFORM) {
                            speed = movingPlatforms.get(movingPlatforms.size() - 1).getSpeed();
                            MovingPlatform lastMovingPlatform = movingPlatforms.get(movingPlatforms.size() - 1);
                            direction = lastMovingPlatform.getDirection();
                        }
                        MovingPlatform newMovingPlatform = new MovingPlatform(x, y, speed, direction);
                        movingPlatforms.add(newMovingPlatform);
                        break;
                    }
                    case LEFT_MOVING_PLATFORM: {
                        int speed = ThreadLocalRandom.current().nextInt(0, 5 + 1);
                        Direction direction = Direction.BACKWARD;
                        Node prevNode = nodes.get(j - 1);
                        if (prevNode == Node.LEFT_MOVING_PLATFORM) {
                            speed = movingPlatforms.get(movingPlatforms.size() - 1).getSpeed();
                            MovingPlatform lastMovingPlatform = movingPlatforms.get(movingPlatforms.size() - 1);
                            direction = lastMovingPlatform.getDirection();
                        }
                        MovingPlatform newMovingPlatform = new MovingPlatform(x, y, speed, direction);
                        movingPlatforms.add(newMovingPlatform);
                        break;
                    }
                }
            }
        }

        this.groundSprites = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            ArrayList<Node> nodes = nodeArrayLists.get(11);
            Node node = nodes.get(i);
            if (node == Node.GROUND) {
                int x = i * 60;
                int y = 11 * 60;
                Sprite sprite = new Sprite(ground, x, y);
                groundSprites.add(sprite);
            }
        }

        this.endSprites = new ArrayList<>();
        for (int i = 0; i < blockHeight; i++) {
            int x = (width - 1) * 60;
            int y = i * 60;
            Sprite sprite = new Sprite(end, x, y);
            endSprites.add(sprite);
        }
    }

    public static ArrayList<ArrayList<Node>> getRandomNodes (int width){
        int blockHeight = 12;
        ArrayList<ArrayList<Node>> nodeArrayLists = new ArrayList<>();
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
                    case RIGHT_MOVING_PLATFORM:
                        nodes.add(Node.RIGHT_MOVING_PLATFORM);
                        count += 2;
                        break;
                    case LEFT_MOVING_PLATFORM:
                        nodes.add(Node.LEFT_MOVING_PLATFORM);
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
            System.out.println(nodes);
        }
        return nodeArrayLists;
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

    public ArrayList<Sprite> getGroundSprites() {
        return this.groundSprites;
    }

    public static ArrayList<ArrayList<Node>> getLevel1 (){
        ArrayList<ArrayList<Node>> nodeArrayLists = new ArrayList<>();
        ArrayList<Node> row1 = new ArrayList<>(Arrays.asList(Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.END));
        ArrayList<Node> row2 = new ArrayList<>(Arrays.asList(Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_ENEMY, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.END));
        ArrayList<Node> row3 = new ArrayList<>(Arrays.asList(Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.END));
        ArrayList<Node> row4 = new ArrayList<>(Arrays.asList(Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_ENEMY, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_ENEMY, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_ENEMY, Node.AIR, Node.AIR, Node.END));
        ArrayList<Node> row5 = new ArrayList<>(Arrays.asList(Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.END));
        ArrayList<Node> row6 = new ArrayList<>(Arrays.asList(Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_ENEMY, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_ENEMY, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.END));
        ArrayList<Node> row7 = new ArrayList<>(Arrays.asList(Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_ENEMY, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_ENEMY, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.END));
        ArrayList<Node> row8 = new ArrayList<>(Arrays.asList(Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_ENEMY, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_ENEMY, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.END));
        ArrayList<Node> row9 = new ArrayList<>(Arrays.asList(Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.RIGHT_MOVING_PLATFORM, Node.RIGHT_MOVING_PLATFORM, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.RIGHT_MOVING_PLATFORM, Node.RIGHT_MOVING_PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_MOVING_PLATFORM, Node.LEFT_MOVING_PLATFORM, Node.RIGHT_MOVING_PLATFORM, Node.RIGHT_MOVING_PLATFORM, Node.END));
        ArrayList<Node> row10 = new ArrayList<>(Arrays.asList(Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.LEFT_MOVING_PLATFORM, Node.LEFT_MOVING_PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.RIGHT_MOVING_PLATFORM, Node.RIGHT_MOVING_PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.END));
        ArrayList<Node> row11 = new ArrayList<>(Arrays.asList(Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.POWERUP, Node.AIR, Node.PLATFORM, Node.PLATFORM, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.AIR, Node.END));
        ArrayList<Node> row12 = new ArrayList<>(Arrays.asList(Node.LAVA, Node.LAVA, Node.LAVA, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.LAVA, Node.LAVA, Node.LAVA, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.LAVA, Node.LAVA, Node.LAVA, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.LAVA, Node.LAVA, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.LAVA, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.LAVA, Node.LAVA, Node.LAVA, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.LAVA, Node.LAVA, Node.LAVA, Node.GROUND, Node.GROUND, Node.LAVA, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.LAVA, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.LAVA, Node.LAVA, Node.LAVA, Node.LAVA, Node.LAVA, Node.LAVA, Node.GROUND, Node.LAVA, Node.LAVA, Node.LAVA, Node.LAVA, Node.LAVA, Node.LAVA, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.LAVA, Node.LAVA, Node.LAVA, Node.GROUND, Node.GROUND, Node.GROUND, Node.GROUND, Node.END));
        nodeArrayLists.add(row1);
        nodeArrayLists.add(row2);
        nodeArrayLists.add(row3);
        nodeArrayLists.add(row4);
        nodeArrayLists.add(row5);
        nodeArrayLists.add(row6);
        nodeArrayLists.add(row7);
        nodeArrayLists.add(row8);
        nodeArrayLists.add(row9);
        nodeArrayLists.add(row10);
        nodeArrayLists.add(row11);
        nodeArrayLists.add(row12);
        return nodeArrayLists;
    }

}
