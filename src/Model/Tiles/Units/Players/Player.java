package Model.Tiles.Units.Players;

import Model.Tiles.Tile;
import Model.Tiles.Units.Units;
import Model.Tiles.Units.Enemies.Enemy ;
import Utils.Callbacks.MessageCallback;
import Utils.Generators.Generator;
import Utils.Position;
import view.CLI;

import java.util.List;

public abstract class Player extends Units {
    public static  final char PLAYER_TILE = '@';
    protected static final int LEVEL_REQUIREMENT = 50 ;
    protected static final int HEALTH_GAIN = 10 ;
    protected static final int ATTACK_GAIN = 4 ;
    protected static final int DEFENSE_GAIN = 1 ;

    protected MessageCallback CallBack;
    private CLI view ;
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
        health.heal();
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
    @Override
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
    protected void AttackAbilityDamage(Enemy e, int abilityDamage) {
        int damageDone = Math.max(abilityDamage - e.defend(), 0);
        e.getHealth().takeDamage(damageDone);
        CallBack.send(String.format("%s hit %s for %d Ability damage Done On Enemy ", getName(), e.getName(), damageDone));
        if (!e.alive())
            kill(e);
    }

    @Override
    public String describe() {
        return super.describe() + "\t\tLevel: " + this.level  + "\t\tExp: " + this.experience ;
    }

    @Override
    public void Death() {
        Tile tile = new Tile('X') {
            @Override
            public void accept(Units unit) {

            }
        };

        this.swapPosition(tile);
        CallBack.send("Player has Died , you have Lost !!!");
    }
    public abstract void CastAbility(List<Enemy> enemies);

    public Position getPosition() {
        return this.position;
    }
}
