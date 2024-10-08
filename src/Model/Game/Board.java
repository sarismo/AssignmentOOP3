package Model.Game;

import Model.Tiles.Empty;
import Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Enemy;
import Model.Tiles.Units.Players.Player;
import Model.Tiles.Units.Units;
import Utils.Generators.RandomGenerator;
import Utils.Position;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Board {
    private Map<Position, Tile> board;
    private Player p;
    private List<Enemy> enemies;
    private int width ;

    public Board(List<Tile> tiles, Player p, List<Enemy> enemies, int width) {
        this.p = p;
        this.enemies = enemies;
        this.width = width;
        this.board = new TreeMap<>();
        for (Tile t : tiles) {
            board.put(t.getPosition(), t);
        }
    }
    public Board(Player player){
        width=0;
        this.p=player;
        this.board = new TreeMap<>();
    }
    public void setEnemies(List<Enemy> l){
        this.enemies=l;
    }
    public void setWidth(int width){
        this.width=width;
    }


    public Tile check(Position p) {
        Tile[] tiles = board.values().toArray(new Tile[0]);
        for(Tile t : tiles){
            if (t.getPosition().compareTo(p) == 0){
                return t;
            }
        }
        throw new RuntimeException("No such tile in this board");
    }
    public void swapPosition(Position p1,Position p2)
    {
        Tile temp = this.board.get(p2);
        this.board.replace(p2,this.board.get(p1)) ;
        this.board.replace(p1,temp);
    }

    public void removeEnemy(Enemy e) {
        Position p = e.getPosition();

        this.board.remove(p,e);
        this.enemies.remove(e);
        Tile tile=new Empty();
        tile.initialize(p,new RandomGenerator());
        this.board.put(p,tile);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Position, Tile> entry : board.entrySet()) {
            sb.append(entry.getValue().toString());
            if(entry.getKey().getX() == width) { //
                sb.append("\n");
            }
        }
        return  sb.toString();

    }

    public int getLength() {
        return  board.size();
    }
    public void SetPosition(Position p,Tile tile){
        board.put(p,tile);
    }

    public Tile getTileInPosition(Position newPosition) {
        return board.get(newPosition);
    }

    public Tile[] getTiles() {
        Tile[] tiles = board.values().toArray(new Tile[0]);
        return tiles;
    }
}


