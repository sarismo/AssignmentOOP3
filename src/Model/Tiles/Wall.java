package Model.Tiles;

import Model.Tiles.Units.Units;
import Utils.Generators.Generator;
import Utils.Position;

public class Wall extends  Tile{
    public static final char WALL_TILE = '#';
    public Wall(){
        super(WALL_TILE);
    }


    @Override
    public void accept(Units unit) {
        unit.visit(this);
    }
}
