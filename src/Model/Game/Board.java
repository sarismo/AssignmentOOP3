package Model.Game;

import Model.Tiles.Empty;
import Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Enemy;
import Model.Tiles.Units.Players.Player;
import Model.Tiles.Units.Units;
import Utils.Position;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Board {
    private Map<Position, Tile> board;
    private Player p;
    private List<Enemy> enemies;
    private final int width ;

    public Board(List<Tile> tiles, Player p, List<Enemy> enemies, int width) {
        this.p = p;
        this.enemies = enemies;
        this.width = width;
        this.board = new TreeMap<>();
        for (Tile t : tiles) {
            board.put(t.getPosition(), t);
        }
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

    public void removeEnemy(Enemy e) {
        Position p = e.getPosition();

        board.remove(p,e);

       board.put(p,new Empty());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Position, Tile> entry : board.entrySet()) {
            sb.append(entry.getValue().toString());
            if(entry.getKey().getX() == width-1) { //
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


