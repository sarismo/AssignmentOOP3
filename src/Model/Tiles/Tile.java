package Model.Tiles;

import Model.Tiles.Units.Units;
import Utils.Generators.Generator;
import Utils.Position;

public abstract class Tile {
    protected Position  position ;
    protected char tile ;

    public Tile(char tile){
        this.tile = tile;
    }
    public  Tile initialize(Position position1){
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

    public void initialize(Position position1, Generator generator){
        this.position = position1;
    }


//    public abstract void intialiize(Position position1, Generator generator);

    public abstract void accept(Units unit) ;

    public Position getPosition() {
        return  this.position;
    }


    public char getTile() {
        return tile ;
    }

    public char getTileCharacter() {
        return tile;
    }
    protected void changeTileView(char x) {
        tile = x;
    }

}
