package Model.Tiles.Units.Enemies;

import Model.Tiles.Empty;
import Model.Tiles.Tile;
import Model.Tiles.Units.Players.Player;
import Model.Tiles.Units.Units;
import Utils.Position;

import javax.security.auth.callback.Callback;

public abstract class Enemy extends Units {

    protected int experienceValue ;

    public Enemy(char tile, String Name, int hitPoints, int attack_Points, int defense_Points , int experienceValue) {
        super(tile, Name, hitPoints, attack_Points, defense_Points);
        this.experienceValue = experienceValue ;
    }

    public int experienceValue() {
        return experienceValue ;
    }
    public abstract void Death(Units Killer);
    public  void addExperience(int experienceValue){
        //do nothing
    }
    @Override
    public void accept(Units unit) {
            unit.visit(this) ;
    }
    public void visit(Enemy e){
        // Do nothing
    }
    public void visit(Player p ){
        battle(p);
        if(!p.alive()){
            p.Death(this);
        }
    }

    @Override
    public String describe() {
        return super.describe() + "\t\tEXPValue: "+ this.experienceValue;
    }

}
