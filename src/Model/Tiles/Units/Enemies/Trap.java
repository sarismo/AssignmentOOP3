package Model.Tiles.Units.Enemies;

import Utils.Generators.Generator;
import Utils.Position;

public class Trap extends Enemy{
    public int VisibilityTime;
    public int InvisibilityTime;
    public int TicksCount;
    public boolean Visible;

    public Trap(char tile, String name, int healthCapacity, int attack, int defense, int experience, int visibilityTime, int invisibilityTime){
        super(tile, name, healthCapacity, attack, defense, experience);
        VisibilityTime =visibilityTime;
        InvisibilityTime = invisibilityTime;
        TicksCount = 0;
        Visible = true;
    }

    @Override
    public void intialiize(Position position1, Generator generator) {

    }

    @Override
    public String describe() {
        return super.describe() + "\t\tVisibilityTime: " + this.VisibilityTime + "\t\tInvisiblityTime: " + this.InvisibilityTime + "\t\tTickCounts : " + this.TicksCount;
    }
}
