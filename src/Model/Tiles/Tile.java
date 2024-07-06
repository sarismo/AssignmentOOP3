package Model.Tiles;

import Model.Tiles.Units.Units;
import Model.Utils.Generators.Generator;
import Model.Utils.Position;

public abstract class Tile {
    protected Position  position ;
    protected char tile ;

    public Tile(char tile){
        this.tile = tile;
    }
    public  void intialiize(Position position1){
        this.position = position1;
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }

    public abstract void intialiize(Position position1, Generator generator);

    public void swapPosition(Tile t){
        Position temp = t.position;
        t.position = this.position;
        this.position = temp ;
    }

    public abstract void accept(Units unit) ;

}
