package Model.Tiles.Units;
import Model.Tiles.Empty;
import  Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Enemy;
import Model.Tiles.Units.Players.Player;
import Utils.Callbacks.DeathCallback;
import Utils.Callbacks.MessageCallback;
import Utils.Generators.Generator;
import Utils.Generators.RandomGenerator;
import Utils.Health;
import Utils.Position;
import Model.Tiles.Wall;


public abstract class Units extends  Tile {

    protected String Name ;
    protected Health health;
    protected int Attack_Points;
    protected int Defense_Points;

    protected Generator generator;
    protected DeathCallback deathCallback;
    protected MessageCallback messageCallback = (Message)-> System.out.println(Message);

    public Units(char tile,String Name , int hitPoints ,int attack_Points, int defense_Points){
        super(tile);
        this.Name = Name;
        this.health =new Health(hitPoints);
        this.Attack_Points = attack_Points;
        this.Defense_Points = defense_Points;
        this.generator=new RandomGenerator();
    }

    public abstract void addExperience(int experienceValue);
    public void initialize(Position position1, Generator generator, DeathCallback deathCallback, MessageCallback messageCallback) {
        super.initialize(position1);
//        this.generator = generator;
        this.deathCallback = deathCallback;
//        this.messageCallback = messageCallback;
    }

    public int attack(){
    return generator.generate(Attack_Points);
    }
    public int defend(){
    return generator.generate(Defense_Points);
    }
    public boolean alive(){
        return health.getHealthAmount() > 0 ;
    }
    public void battle(Units enemy){
    int attack = this.attack();
    int defense = enemy.defend();
    int damage_taken = attack - defense;

        if(attack > defense)
        {
            enemy.health.takeDamage(damage_taken);
            messageCallback.send(  "enemy lost " + (attack - defense)+ "amount of health" );
        }
        else
            messageCallback.send("the attack was too low to break the enemy defense");
        if(!enemy.alive())
            enemy.Death(this);
    }
    public void Interact(Tile t) {
        t.accept(this);

    }
    public void visit(Empty e){
        swapPosition(e);
    }
    public void visit(Wall  w) {
        // DO nothing
    }
    public String getName(){
        return Name;
    }

    public Health getHealth(){
        return health;
    }

    protected int getHealthAmount(){
        return health.getHealthAmount();
    }
    protected int getAttack(){
        return Attack_Points;
    }
    protected int getDefense(){
        return Defense_Points;
    }
    public String describe() {
        return "\t\tName: "+ getName() + "\t\tHealth: " + getHealthAmount() + "\t\tAttack: " + getAttack() + "\t\tDefense: " + getDefense();
    }
    public abstract void visit(Player p );
    public abstract void visit(Enemy e);
    public abstract void Death(Units Killer);
}
