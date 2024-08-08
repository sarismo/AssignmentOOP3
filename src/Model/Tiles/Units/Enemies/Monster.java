package Model.Tiles.Units.Enemies;

import Utils.Generators.Generator;
import Utils.Position;

public class Monster extends Enemy{
    Integer VisionRange;
    public Monster(char tile, String Name, int hitPoints, int attack_Points, int defense_Points, int experienceValue, int visionRange) {
        super(tile, Name, hitPoints, attack_Points, defense_Points, experienceValue);
        this.VisionRange = visionRange;
    }

    @Override
    public void intialiize(Position position1, Generator generator) {

    }

    @Override
    public String describe() {
        return super.describe() + "\t\tVisionRange : " + this.VisionRange;
    }
}
