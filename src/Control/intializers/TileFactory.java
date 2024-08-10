package Control.intializers;

import Model.Tiles.Empty;
import Model.Tiles.Tile;
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
import Utils.Generators.Generator;
import Utils.Position;
import java.util.Map;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class TileFactory {
    private Player p;
    private static final List<Supplier<Player>> playerTypes = Arrays.asList(   () -> new Warrior("Jon Snow", 300, 30, 4, 3),
            () -> new Warrior("The Hound", 400, 20, 6, 5),
            () -> new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6),
            () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4),
            () -> new Rogue("Arya Stark", 150, 40, 2, 20),
            () -> new Rogue("Bronn", 250, 35, 3, 50) );




    private static final  Map<Character,Supplier<Enemy>> enemyTypes = initEnemies();

    private static Map<Character, Supplier<Enemy>> initEnemies() {
        List<Supplier<Enemy>> enemies = Arrays.asList(
                () -> new Monster('s', "Lannister Solider", 80, 8, 3,25, 3),
                () -> new Monster('k', "Lannister Knight", 200, 14, 8, 50,   4),
                () -> new Monster('q', "Queen's Guard", 400, 20, 15, 100,  5),
                () -> new Monster('z', "Wright", 600, 30, 15,100, 3),
                () -> new Monster('b', "Bear-Wright", 1000, 75, 30, 250,  4),
                () -> new Monster('g', "Giant-Wright",1500, 100, 40,500,   5),
                () -> new Monster('w', "White Walker", 2000, 150, 50, 1000, 6),
                () -> new Monster('M', "The Mountain", 1000, 60, 25,  6, 500),
                () -> new Monster('C', "Queen Cersei", 100, 10, 10,1, 1000),
                () -> new Monster('K', "Night's King", 5000, 300, 150, 8, 5000),
                () -> new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10),
                () -> new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 10),
                () -> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10)
        );

        return enemies.stream().collect(Collectors.toMap(s -> s.get().getTile(), Function.identity()));
    }
    public TileFactory() {
    }


    public Player producePlayer(int PlayerID,DeathCallback deathCallback,MessageCallback messageCallback,Generator generator) {
        Supplier<Player> supp = playerTypes.get(PlayerID-1);
        this.p = supp.get();
        return this.p;
    }
    public Player producePlayer(){
        return this.p;
    }

    public Enemy produceEnemy(char tile, Position p , DeathCallback c , Generator g , MessageCallback m){
        Enemy enemy = enemyTypes.get(tile).get();
        enemy.initialize(p,g,c,m);
        return enemy;

    }


    public Tile produceEmpty(Position p){
        return new Empty().initialize(p);
    }
    public Tile produceWall(Position p ){
        return new Wall().initialize(p);
    }


}
