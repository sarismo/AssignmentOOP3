package Control.intializers;

import Model.Tiles.Empty;
import Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Enemy;
import Model.Tiles.Units.Players.Player;
import Model.Tiles.Wall;
import Utils.Callbacks.DeathCallback;
import Utils.Callbacks.MessageCallback;
import Utils.Generators.Generator;
import Utils.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class TileFactory {
    private Player p;
    private static final List<Supplier<Player>> playerTypes = Arrays.asList(
            () -> new Player("Player1", 10, 5, 2));

    private static final  Map<Character,Supplier<Enemy>> enemyTypes = Map.of(
           's', () -> new Enemy('s',"enemy",5,2,2,0),
          'q' , () -> new Enemy('q',"enemy2",15,3,3,1)
    );


    public TileFactory() {
    }

    public Player producePlayer(int PlayerID) {
        Supplier<Player> supp = playerTypes.get(PlayerID-1);
        this.p = supp.get();
        return this.p;
    }
    public Player producePlayer(){
        return this.p;
    }

    public Enemy produceEnemy(char tile, Position p , DeathCallback c , Generator g , MessageCallback m){
        Enemy enemy = enemyTypes.get(tile).get();
        enemy.intialiize(p,g,c,m);
        return enemy;

    }

    public Tile produceEmpty(Position p){
        return new Empty().intialiize(p);
    }
    public Tile produceWall(Position p ){
        return new Wall().intialiize(p);
    }


}
