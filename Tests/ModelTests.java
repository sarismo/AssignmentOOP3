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
import Utils.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTests {

    public TileFactory tileFactory ;
    public Player p ;
    public Enemy e ;
    public Wall w ;
    public Empty empty ;
    public Monster monster ;
    public Trap trap ;
    public Mage mage ;
    public Rogue rogue;
    public Warrior warrior;
   public MessageCallback messageCallback ;
   public Position PlayerPosition ;
   public Position EnemyPosition;
   public DeathCallback deathCallback;

   public FixedGenerator fixedGenerator ;
   public List<Enemy> enemyList;

   @BeforeEach
public void ModelTestsInitializer(){
    messageCallback = (Message) -> System.out.println(Message);
    deathCallback = () -> System.out.println("Player has died");
    fixedGenerator = new FixedGenerator();
    this.tileFactory = new TileFactory(messageCallback);
    this.PlayerPosition = new Position(1,1);
    this.EnemyPosition = new Position(1,0);
    this.p = tileFactory.producePlayer(1,deathCallback,messageCallback,fixedGenerator,PlayerPosition);
    this.warrior = (Warrior)p;
    this.mage = (Mage) tileFactory.producePlayer(4,deathCallback,messageCallback,fixedGenerator,new Position(2,6));
    this.rogue = (Rogue) tileFactory.producePlayer(6,deathCallback,messageCallback,fixedGenerator,new Position(5,6));
    this.e = tileFactory.produceEnemy('s',EnemyPosition,()->System.out.println("Enemy has died"),fixedGenerator,messageCallback);
    this.monster = (Monster) e;
    this.trap = (Trap)tileFactory.produceEnemy('B',new Position(2,3),()->System.out.println("Enemy has died"),fixedGenerator,messageCallback);
    this.empty = (Empty) tileFactory.produceEmpty(new Position(0,0));
    this.w = (Wall) tileFactory.produceWall(new Position(2,2));
    enemyList = new ArrayList<>();
    enemyList.add(trap);
    enemyList.add(monster);




}
@AfterEach
public void RestoreHealth(){
    warrior.getHealth().heal();
    mage.getHealth().heal();
    rogue.getHealth().heal();
    monster.getHealth().heal();
    trap.getHealth().heal();
}

@Test
    public void WarriorMovements(){
    Position FormerPosition = empty.getPosition();
    warrior.Interact(empty);
    assertEquals(true, warrior.getPosition().equals(FormerPosition), "Should swap positions with the empty tile");
    warrior.Interact(w);
    assertEquals(false, warrior.getPosition().equals(w.getPosition()), "Should Do Nothing");
}
@Test
public void RougeMovements(){
    Position FormerPosition = empty.getPosition();
    rogue.Interact(empty);
    assertEquals(true, rogue.getPosition().equals(FormerPosition), "Should swap positions with the empty tile");
    rogue.Interact(w);
    assertEquals(false, rogue.getPosition().equals(w.getPosition()), "Should Do Nothing");
}
@Test
public void MageMovements(){
    Position FormerPosition = empty.getPosition();
    mage.Interact(empty);
    assertEquals(true, mage.getPosition().equals(FormerPosition), "Should swap positions with the empty tile");
    mage.Interact(w);
    assertEquals(false, mage.getPosition().equals(w.getPosition()), "Should Do Nothing");
}
@Test
public void MonsterMovements(){
    Position FormerPosition = empty.getPosition();
    monster.Interact(empty);
    assertEquals(true, monster.getPosition().equals(FormerPosition), "Should swap positions with the empty tile");
    monster.Interact(w);
    assertEquals(false, monster.getPosition().equals(w.getPosition()), "Should Do Nothing");
}
@Test
public void TrapMovements(){
    Position FormerPosition = empty.getPosition();
    trap.Interact(empty);
    assertEquals(true, trap.getPosition().equals(FormerPosition), "Should swap positions with the empty tile");
    trap.Interact(w);
    assertEquals(false, trap.getPosition().equals(w.getPosition()), "Should Do Nothing");
}
@Test
    public void WarriorAttacks(){
       warrior.Interact(monster);
       assertEquals(true,monster.getHealth().Health_pool >= monster.getHealth().Health_amount);
       warrior.Interact(trap);
       assertEquals(true,trap.getHealth().Health_pool >= trap.getHealth().Health_amount);

}
@Test
public void MageAttacks(){
    mage.Interact(monster);
    assertEquals(true, monster.getHealth().Health_pool >= monster.getHealth().Health_amount, "player should battle the enemy");
    mage.Interact(trap);
    assertEquals(true, trap.getHealth().Health_pool >= trap.getHealth().Health_amount, "player should battle the enemy");

}
@Test
    public void RougeAttacks(){
    rogue.Interact(monster);
    assertEquals(true, monster.getHealth().Health_pool >= monster.getHealth().Health_amount, "player should battle the enemy");
    rogue.Interact(trap);
    assertEquals(true, trap.getHealth().Health_pool >= trap.getHealth().Health_amount, "player should battle the enemy");
}
@Test
    public void MonsterAttacks(){
    monster.Interact(warrior);
    assertEquals(true, warrior.getHealth().Health_pool >= warrior.getHealth().Health_amount, "Enemy should battle the Player");
    monster.Interact(mage);
    assertEquals(true, mage.getHealth().Health_pool >= mage.getHealth().Health_amount, "Enemy should battle the Player");
    monster.Interact(rogue);
    assertEquals(true, rogue.getHealth().Health_pool >= rogue.getHealth().Health_amount, "Enemy should battle the Player");
}
@Test public void TrapAttacks(){
    trap.Interact(warrior);
    assertEquals(true, warrior.getHealth().Health_pool >= warrior.getHealth().Health_amount, "Enemy should battle the Player");
    trap.Interact(mage);
    assertEquals(true, mage.getHealth().Health_pool >= mage.getHealth().Health_amount, "Enemy should battle the Player");
    trap.Interact(rogue);
    assertEquals(true, rogue.getHealth().Health_pool >= rogue.getHealth().Health_amount, "Enemy should battle the Player");
}



}
