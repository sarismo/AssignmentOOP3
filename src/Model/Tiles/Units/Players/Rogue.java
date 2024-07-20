package Model.Tiles.Units.Players;

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

    private int RogueGainAttack()
    {
        return 3 * level;
    }

}
