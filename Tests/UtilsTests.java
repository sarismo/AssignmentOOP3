import Control.intializers.TileFactory;
import Model.Game.Board;
import Model.Game.Game;
import Model.Game.Level;
import Model.Tiles.Empty;
import Model.Tiles.Units.Enemies.Enemy;
import Model.Tiles.Units.Enemies.Monster;
import Model.Tiles.Units.Enemies.Trap;
import Model.Tiles.Units.Players.Mage;
import Model.Tiles.Units.Players.Player;
import Model.Tiles.Units.Players.Rogue;
import Model.Tiles.Units.Players.Warrior;
import Model.Tiles.Wall;
import Utils.Callbacks.DeathCallback;
import Utils.Callbacks.MessageCallback;
import Utils.Generators.FixedGenerator;
import Utils.Health;
import Utils.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UtilsTests {
    private Health health;
    private Position position;
    @BeforeEach
    public void UtilsInitializer(){
        health = new Health(100);
        position = new Position(0,0);

    }
    @Test
    public void HealthTakeDamage(){
        health.takeDamage(40);
        assertEquals(60,health.Health_amount,"Health should be 60");
        health.heal();
        health.takeDamage(50);
        assertEquals(50,health.Health_amount,"Health should be 50");
        health.heal();
        health.takeDamage(100);
        assertEquals(0,health.Health_amount,"Health should be 0");
        health.heal();
        health.takeDamage(200);
        assertEquals(0,health.Health_amount,"Health should be 0");
        health.heal();
        health.takeDamage(-40);
        assertEquals(100,health.Health_amount,"Health shouldn't change");


    }
    @Test
    public void HealthHeal(){
        health.takeDamage(50);
        health.heal();
        assertEquals(100,health.Health_amount,"Should heal back to 100");

    }
    @Test
    public void HealthSet(){
        health.IncreaseMax(100);
        assertEquals(200,health.Health_pool,"Health Pool should be 200 now");
    }
    @Test
    public void SetPosition(){
        position.setX(2);
        position.setY(3);
        assertEquals(true,new Position(2,3).equals(position),"Should set the position");

    }
    @Test
    public void RangePositionTest(){
        Position p1 = new Position(3, 4);
        double expected = 5.0;
        assertEquals(expected, position.Range(p1), 0.0001, "Range between (0,0) and (3,4) should be 5.");

        Position p2 = new Position(-1, -1);
        Position p3 = new Position(1, 1);
        double expected1 = Math.sqrt(8);
        assertEquals(expected1, p2.Range(p3), 0.0001, "Range between (-1,-1) and (1,1) should be sqrt(8).");

    }


}


