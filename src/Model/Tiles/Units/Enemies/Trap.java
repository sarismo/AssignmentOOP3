package Model.Tiles.Units.Enemies;

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
}
