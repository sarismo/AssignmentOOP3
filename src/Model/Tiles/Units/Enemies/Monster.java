package Model.Tiles.Units.Enemies;

public class Monster extends Enemy{
    Integer VisionRange;
    public Monster(char tile, String Name, int hitPoints, int attack_Points, int defense_Points, int experienceValue, int visionRange) {
        super(tile, Name, hitPoints, attack_Points, defense_Points, experienceValue);
        this.VisionRange = visionRange;
    }
}
