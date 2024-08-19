package Model.Tiles;

import Model.Tiles.Units.Units;
import Utils.Generators.Generator;
import Utils.Position;

public class Empty extends Tile{
    public  static final char EMPTY_TILE = '.';
    public Empty() {
        super(EMPTY_TILE);
    }


//    public void intialiize(Position position1, Generator generator)
//    {
//        return;
//    }

    @Override
    public void accept(Units unit) {
            unit.visit(this);
    }
}
