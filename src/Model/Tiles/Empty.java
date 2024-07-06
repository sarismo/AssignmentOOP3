package Model.Tiles;

import Model.Tiles.Units.Units;
import Model.Utils.Generators.Generator;
import Model.Utils.Position;

public class Empty extends Tile{
    public  static final char EMPTY_TILE = '.';
    public Empty(char tile) {
        super(EMPTY_TILE);
    }

    @Override
    public void intialiize(Position position1, Generator generator) {
        // TODO : Implement this ;
    }

    @Override
    public void accept(Units unit) {
            unit.visit(this);
    }
}
