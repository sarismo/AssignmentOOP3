package Model.Tiles.Units.Players;

import Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Enemy;

import java.util.List;

public class Rogue extends Player {
    int Cost;
    int Current_Energy;

    public Rogue(String name, int healthCapacity, int attack, int defense, int cost) {
        super(name, healthCapacity, attack, defense);
        this.Cost = cost;
        Current_Energy = 100;
    }
    public void info()
    {
        this.CallBack.send("Rogue " +this.getName() + "\n" + this.describe() + "\n");
    }

    public void levelUp() {
        super.levelUp();
        Current_Energy = 100;
        int rogueAttackGained = RogueGainAttack();
        Attack_Points += rogueAttackGained;

    }





    @Override
    public void CastAbility(List<Enemy> enemies) {
        if (Current_Energy >= Cost) {
            Current_Energy -= Cost;
            for (Enemy enemy : enemies) {
                if (this.position.Range(enemy.getPosition()) < 2) {
                    AttackAbilityDamage(enemy, this.Attack_Points);
                }
            }
        }
    }

    @Override
    public void onGameTick(Tile t) {
        this.Interact(t);
        Current_Energy = Math.min(Current_Energy + 10, 100);
    }

    private int RogueGainAttack()
    {
        return 3 * level;
    }


    @Override
    public String describe() {
        return super.describe() + "\t\tCurrentEnergy: "+ this.Current_Energy;
    }
}
