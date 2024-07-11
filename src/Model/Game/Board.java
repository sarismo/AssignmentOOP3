package Model.Game;

import Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Enemy;
import Model.Tiles.Units.Players.Player;
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
            board.put(t.getPostion(), t);
        }
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
}

