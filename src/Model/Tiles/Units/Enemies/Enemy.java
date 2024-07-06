package Model.Tiles.Units.Enemies;

import Model.Tiles.Units.Players.Player;
import Model.Tiles.Units.Units;

public class Enemy extends Units {

    protected int experienceValue ;

    public Enemy(char tile, String Name, int hitPoints, int attack_Points, int defense_Points , int experienceValue) {
        super(tile, Name, hitPoints, attack_Points, defense_Points);
        this.experienceValue = experienceValue ;
    }

    public int experienceValue() {
        return experienceValue ;
    }
    public void Death(){
        // TODO : Implement this
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
            p.Death();
        }
    }
}
