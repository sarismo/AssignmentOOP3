package Model.Tiles.Units.Enemies;

import Model.Tiles.Units.Players.Player;
import Model.Tiles.Units.Units;

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
        if(TicksCount == (VisibilityTime + InvisibilityTime)) {
            TicksCount = 0;
        }
        if (this.Visible) {
            this.changeTileView(tile);
        } else {
            this.changeTileView('.');
        }
        TicksCount++;
        // Attack the player if they are within range
        if (this.getPosition().Range(player.getPosition()) < 2) {
            this.battle(player);
        }
    }
    @Override
    public void Death(Units Killer){
        Killer.addExperience(this.experienceValue);
        messageCallback.send("Trap " + this.getName() + " died.");
    }
    @Override
    public String describe() {
        return super.describe() + "\t\tVisibilityTime: " + this.VisibilityTime + "\t\tInvisiblityTime: " + this.InvisibilityTime + "\t\tTickCounts : " + this.TicksCount;
    }
}
