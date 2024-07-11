package Model.Tiles;

import Model.Tiles.Units.Units;
import Utils.Generators.Generator;
import Utils.Position;

import java.util.Map;

public abstract class Tile {
    protected Position  position ;
    protected char tile ;

    public Tile(char tile){
        this.tile = tile;
    }
    public  Tile intialiize(Position position1){
        this.position = position1;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }


    public void swapPosition(Tile t){
        Position temp = t.position;
        t.position = this.position;
        this.position = temp ;
    }

    public abstract void accept(Units unit) ;

    public Position getPostion() {
        return  this.position;
    }


}
