package Model.Tiles.Units.Players;

import Model.Tiles.Units.Units;
import Model.Tiles.Units.Enemies.Enemy ;

public class Player extends Units {
    public static  final char PLAYER_TILE = '@';
    protected static final int LEVEL_REQUIREMENT = 50 ;
    protected static final int HEALTH_GAIN = 10 ;
    protected static final int ATTACK_GAIN = 4 ;
    protected static final int DEFENSE_GAIN = 1 ;


    protected int level ;
    protected int experience;

    public Player(String name , int hitPoints , int attack , int defense){
        super(PLAYER_TILE,name,hitPoints,attack,defense);
        this.level = 1 ;
        this.experience = 0 ;
    }
    public void kill(Enemy enemy){
        addExperience(enemy.experienceValue());
        enemy.Death();
    }
    public void addExperience(int experienceValue){
        this.experience += experienceValue;
        while(experience >= levelRequirment()){
            levelUp();
        }
    }
    public  void levelUp(){
        this.experience += levelRequirment() ;
        this.level++;
        int healthGain = healthGain();
        int attackGain = attackGain();
        int defenseGain = defenseGain();
        health.IncreaseMax(healthGain);
        health.heal(healthGain);
        this.Attack_Points += attackGain;
        this.Defense_Points += defenseGain;
    }
    protected int levelRequirment(){
        return LEVEL_REQUIREMENT* level;
    }
    protected int healthGain(){
     return  HEALTH_GAIN * level ;
    }
    protected int attackGain(){
        return ATTACK_GAIN * level ;
    }
    protected int defenseGain(){
        return DEFENSE_GAIN * level;
    }
    public void accept(Units unit) {
        unit.visit(this);
    }
    public void visit(Player p){
        // Do nothing
    }
    public void visit(Enemy e){
        battle(e);
        if(!e.alive()){
            addExperience(e.experienceValue());
            e.Death();
        }
    }

    @Override
    public void Death() {
        // TODO : implement this
    }
}
