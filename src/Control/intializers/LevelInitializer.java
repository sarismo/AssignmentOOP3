package Control.intializers;

import Model.Game.Board;
import Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Enemy;
import Model.Tiles.Units.Players.Player;
import Utils.Callbacks.DeathCallback;
import Utils.Callbacks.MessageCallback;
import Utils.Generators.Generator;
import Utils.Generators.RandomGenerator;
import Utils.Position;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LevelInitializer {
    private  Player p;
    protected int playerID;
    protected Board board;
    protected TileFactory tileFactory;

    protected DeathCallback d=new DeathCallback() {
        @Override
        public void Death() {

        }
    };
    protected MessageCallback m;
    protected Generator g=new RandomGenerator();


    public  LevelInitializer(MessageCallback m , int id, Player player){
        this.playerID = id;
        this.p =player;
        this.m = m;
        this.tileFactory = new TileFactory(m);
        board=new Board(p);
    }

    public Board initLevel(String levelPath){
//        List<Tile> tiles=new ArrayList<>();
        List<Enemy> enemies= new ArrayList<>();
        List<String> lines;
        try{
            lines = Files.readAllLines(Paths.get(levelPath));

        } catch (IOException e){

            throw new RuntimeException(e);
        }
        int x =0;
        int y = 0;
        for(String line : lines){
            y++;
            x=-1;

            for(char c : line.toCharArray()){
                x++;
                Position position = new Position(x,y);
                switch (c){
                    case '.' :
                        // create empty tile // TODO
                        Tile e = tileFactory.produceEmpty(position);
                        e.initialize(position,g);
                        board.SetPosition(position,e);
                        break;
                    case '#':
                        // create wall tile // TODO
                        Tile w= tileFactory.produceWall(position);
                        w.initialize(position,g);
                        board.SetPosition(position,w);
                        break;
                    case  '@':
                        //create player tile // TODO
                        Tile p= tileFactory.producePlayer(playerID, d, m, g);
                        p.initialize(position,g);
                        board.SetPosition(position,p);
                        break;
                    default:
                        // create enemy tile // TODO
                        Enemy enemy= tileFactory.produceEnemy(c,position,d,g,m);
                        board.SetPosition(position,enemy);
                        enemy.initialize(position);
                        enemies.add(enemy);
                        break;
                }

            }
        }
        board.setWidth(x);
        board.setEnemies(enemies);
        return board;
    }


    public boolean levelExists(String levelFilePath) {
        return  true;
    }
}