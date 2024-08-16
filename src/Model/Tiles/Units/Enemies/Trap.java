package Model.Tiles.Units.Enemies;

import Model.Tiles.Units.Players.Player;
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
    public void onEnemyTurn(Player player) {
        // Update visibility based on ticksCount
        Visible = TicksCount < VisibilityTime;

        // Reset ticksCount if it has reached the full cycle of visibility + invisibility
        if (TicksCount == (VisibilityTime + InvisibilityTime)) {
            TicksCount = 0;
        } else {
            TicksCount++;
        }

        // Attack the player if they are within range
        if (this.getPosition().Range(player.getPosition()) < 2) {
            this.battle(player);
        }
    }
    @Override
    public String describe() {
        return super.describe() + "\t\tVisibilityTime: " + this.VisibilityTime + "\t\tInvisiblityTime: " + this.InvisibilityTime + "\t\tTickCounts : " + this.TicksCount;
    }
}
