package org.nightshade.networking;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.game.Game;
import org.nightshade.game.Level;
import org.nightshade.game.Sprite;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class PlayerMoveMsgTest {
    PlayerMoveMsg p1;
    PlayerMoveMsg p2;
    @Start
    public void start(Stage stage)  throws IOException {
        p1 = new PlayerMoveMsg("player1", 200, 200, true);
        p2 = new PlayerMoveMsg("player2", 100, 100, false);
    }
    @Test
    public void testGetName() {
        Assertions.assertNotEquals("pp", p1.getName());
        Assertions.assertEquals("player2", p2.getName());
    }
    @Test
    public void testGetX() {
        Assertions.assertNotEquals(300, p1.getX());
        Assertions.assertEquals(100, p2.getX());
    }
    @Test
    public void testGetY() {
        Assertions.assertNotEquals(300, p1.getY());
        Assertions.assertEquals(100, p2.getY());
    }
    @Test
    public void testIsalive() {
        Assertions.assertEquals(true, p1.isAlive());
        Assertions.assertEquals(false, p2.isAlive());
    }
}
