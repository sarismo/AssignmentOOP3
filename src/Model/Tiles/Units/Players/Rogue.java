package Model.Tiles.Units.Players;

import Model.Tiles.Units.Enemies.Enemy;
import Utils.Generators.Generator;
import Utils.Position;

import java.util.List;

public class Rogue extends Player {
    int Cost;
    int Current_Energy;

    public Rogue(String name, int healthCapacity, int attack, int defense, int cost) {
        super(name, healthCapacity, attack, defense);
        this.Cost = cost;
        Current_Energy = 100;
    }


    public void levelUp() {
        super.levelUp();
        Current_Energy = 100;
        int rogueAttackGained = RogueGainAttack();
        Attack_Points += rogueAttackGained;

    }

    @Override
    public void CastAbility(List<Enemy> enemies) {

    }



    private int RogueGainAttack()
    {
        return 3 * level;
    }

    @Override
    public void intialiize(Position position1, Generator generator) {

    }

    @Override
    public String describe() {
        return super.describe() + "\t\tCurrentEnergy: "+ this.Current_Energy;
    }
}
