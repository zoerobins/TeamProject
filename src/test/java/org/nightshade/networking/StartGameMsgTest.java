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
public class StartGameMsgTest {
    StartGameMsg s1;
    StartGameMsg s2;
    @Start
    public void start(Stage stage)  throws IOException {
        s1 = new StartGameMsg(100, new ArrayList<>());
        s2 = new StartGameMsg(200, new ArrayList<>());
    }
    @Test
    public void testgetNumPlayers() {
        Assertions.assertNotEquals(101, s1.getNumPlayers());
        Assertions.assertEquals(200, s2.getNumPlayers());
    }
    @Test
    public void testgetPlayers() {
        Assertions.assertEquals(0, s1.getPlayers().size());
        Assertions.assertEquals(0, s2.getPlayers().size());
    }

}

