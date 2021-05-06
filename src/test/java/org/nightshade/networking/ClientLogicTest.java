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
public class ClientLogicTest {
    ClientLogic cl1;
    ClientLogic cl2;
    @Start
    public void start(Stage stage)  throws IOException {
        cl1 = new ClientLogic();
        cl2 = new ClientLogic("127.0.0.1", 2222, new Client());
    }
    @Test
    public void testGetMsgsList() {
        Assertions.assertEquals(0, cl1.getMsgsList().size());
    }
    @Test
    public void testsendToServer() throws IOException {
        cl1.sendToServer("p1", 100, 100,  true);
    }
    @Test
    public void testgetPlayersList() {
        Assertions.assertEquals(0, cl1.getPlayersList().size());
    }
}
