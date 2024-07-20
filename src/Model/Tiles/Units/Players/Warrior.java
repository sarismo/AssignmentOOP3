package Model.Tiles.Units.Players;

import java.util.Random;

public class Warrior extends Player {
    public static final String specialAbility = "Avenger's Shield";
    private int AbilityCooldown;
    private int RemainingCooldown;
    Random randomEnemy;

    public Warrior(String name, int hitPoints, int attack, int defense, int abilityCooldown) {
        super(name, hitPoints, attack, defense);
        AbilityCooldown = abilityCooldown;
        RemainingCooldown = 0;
    }

    public void levelUp()
    {
        super.levelUp();
        int warriorHealthGained = WarriorGainHealth();
        int warriorAttackGained = WarriorGainAttack();
        int warriorDefenseGained = WarriorGainDefense();
        health.IncreaseMax(warriorHealthGained);
        health.heal();
        Attack_Points += warriorAttackGained;
        Defense_Points += warriorDefenseGained;
    }
    private int WarriorGainHealth()
    {
        return 5 * level;
    }

    private int WarriorGainAttack()
    {
        return 2 * level;
    }

    private int WarriorGainDefense() { return level; }
}
