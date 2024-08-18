package Model.Tiles.Units.Players;

import Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Enemy;
import Utils.Generators.RandomGenerator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Mage extends Player {

    protected int Mana_Pool;
    protected int Current_Mana;
    protected int Mana_Cost;
    protected int Spell_Power;
    protected int Hits_Count;
    protected int Ability_Range;
    private Random randomEnemy;

    private final static int RANGE_LIMIT = 3;

    public Mage (String name, int healthCapacity, int attack, int defense, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(name, healthCapacity, attack, defense);
        this.Mana_Pool = manaPool;
        this.Current_Mana = manaPool / 4;
        this.Mana_Cost = manaCost;
        this.Spell_Power = spellPower;
        this.Hits_Count = hitsCount;
        this.Ability_Range = abilityRange;
    }
    @Override
    public void levelUp()
    {
        super.levelUp();
        Mana_Pool += MageGainManaPool();
        Current_Mana = Math.min(Current_Mana + Mana_Pool / 4, Mana_Pool);
        Spell_Power += MageGainSpellPower();
        messageCallback.send(String.format("+%d Maximum mana, +%d Spell Power", Mana_Pool, Spell_Power));
    }



    private int MageGainManaPool()
    {
        return 25 * level;
    }

    private int MageGainSpellPower()
    {
        return 10 * level;
    }
    public void info()
    {
        this.CallBack.send("Mage " +this.getName() + "\n" + this.describe() + "\n");
    }


    @Override
    public String describe() {
        return super.describe() + "\t\tMana_Pool: " + this.Mana_Pool;
    }

    @Override
    public void CastAbility(List<Enemy> enemies) {
        if (Current_Mana >= Mana_Cost) {
            Current_Mana -= Mana_Cost;
            int hits = 0;
            RandomGenerator randomGenerator = new RandomGenerator();
            while (hits < Hits_Count) {
                List<Enemy> possibleEnemies = enemies.stream()
                        .filter(e -> e.alive() && this.position.Range(e.getPosition()) < RANGE_LIMIT)
                        .collect(Collectors.toList());

                if (possibleEnemies.isEmpty()) {
                    break;
                }
                int randomIndex = randomGenerator.generate(possibleEnemies.size());
                Enemy enemyToHit = possibleEnemies.get(randomIndex);
                AttackAbilityDamage(enemyToHit, Spell_Power);
                hits++;
            }
        }

    }

    @Override
    public void onGameTick(Tile t) {
        this.Interact(t);
        Current_Mana = Math.min(Mana_Pool, Current_Mana + level);
    }

}

