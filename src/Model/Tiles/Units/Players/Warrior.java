package Model.Tiles.Units.Players;

import Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Enemy;
import Utils.Generators.Generator;
import Utils.Position;

import java.util.ArrayList;
import java.util.List;
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



    @Override
    public void CastAbility(List<Enemy> enemies) {
        if (RemainingCooldown <= 0) {
            RemainingCooldown = AbilityCooldown;
            List<Enemy> PossibleEnemy = new ArrayList<>();
            RemainingCooldown = AbilityCooldown;
            health.Health_amount = Math.min(health.getHealthAmount() + (10 * Defense_Points), health.getHealthPool());
            for (Enemy e : enemies) {
                if (this.position.Range(e.getPosition()) < 3)
                    PossibleEnemy.add(e);
            }
            if (PossibleEnemy.isEmpty()) {
                System.out.println("nothing");
                return;
            }else {
                randomEnemy = new Random();
                int random = (int) (Math.random() * PossibleEnemy.size());
                AttackAbilityDamage(PossibleEnemy.get(random),(int) (health.Health_pool/10));
            }
        } else {
            messageCallback.send("The player can't use his special ability yet ,"+ "wait" +RemainingCooldown +"Seconds till he cools down" );
        }
    }
    @Override
    public void onGameTick(){

        if (RemainingCooldown > 0)
            RemainingCooldown--;


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
    public void info()
    {
        this.CallBack.send("Warrior " +this.getName() + "\n" + this.describe() + "\n");
    }

    @Override
    public String describe() {
        return super.describe() + "\t\tAbilityCoolDown : " + this.AbilityCooldown + "\t\tRemaningCoolDown : " +RemainingCooldown ;
    }
}
