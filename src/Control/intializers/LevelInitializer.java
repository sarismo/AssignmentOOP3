package Control.intializers;

import Model.Game.Board;
import Model.Tiles.Tile;
import Model.Tiles.Units.Units;
import Model.Tiles.Wall;
import Utils.Callbacks.DeathCallback;
import Utils.Callbacks.MessageCallback;
import Utils.Generators.Generator;
import Utils.Position;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class LevelInitializer {
    protected int playerID;
    protected Board board;
    protected TileFactory tileFactory= new TileFactory();

    protected DeathCallback d;
    protected MessageCallback m;
    protected Generator g;


    public  LevelInitializer(int playerID,Board board){
        this.playerID = playerID;
        this.board = board;
    }
    public void initLevel(String levelPath){
        List<String> lines;
        try{
            lines = Files.readAllLines(Paths.get(levelPath));

        } catch (IOException e){

            throw new RuntimeException(e);
        }
        for(String line : lines){
           int x =0;
           int y = 0;
            Position position = new Position(x,y);
            for(char c : line.toCharArray()){
                switch (c){
                    case '.' :
                        // create empty tile // TODO
                      Tile e = tileFactory.produceEmpty(position);
                        board.SetPosition(position,e);
                        break;
                    case '#':
                        // create wall tile // TODO
                       Tile w= tileFactory.produceWall(position);
                       board.SetPosition(position,w);
                        break;
                    case  '@':
                        //create player tile // TODO
                     Tile p= tileFactory.producePlayer(playerID,d,m,g);
                     board.SetPosition(position,p);
                        break;
                    default:
                        // create enemy tile // TODO
                    Tile enemy= tileFactory.produceEnemy(c,position,d,g,m);
                        board.SetPosition(position,enemy);
                        break;
                }

            }
        }
    }
 

}
