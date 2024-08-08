package Model.Game;


import Control.intializers.LevelInitializer;
import Control.intializers.TileFactory;
import Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Monster;
import Model.Tiles.Units.Enemies.Trap;
import Model.Tiles.Units.Players.Player;
import Model.Tiles.Units.Units;
import Utils.Callbacks.MessageCallback;

import java.util.List;

public class Level {
    private Board board;
    private List<Monster> monsters;
    private List<Trap> traps;
    private Player player;
    private MessageCallback msg;
    private LevelInitializer buildLevel;
    private TileFactory factory;

    public Level(MessageCallback msg,int PlayerId) {

    }

    public void gameTick(String action)
    {
    }

    public void unitMove(Units u, String action)
    {

    }
    public boolean gameOver()
    {
        return !player.alive();
    }

    public boolean isOver()
    {
        return monsters.size() == 0;
    }

    public void loadLevel(String filePath) {

    }


    public void addEnemy(Tile t, char c) // TODO ::::
    {
        if (c != '#' && c != '.'){

        }
            if (c == 'B' | c == 'Q' | c =='D')
                traps.add((Trap) t);
            else
                monsters.add((Monster)t);
    }



}
