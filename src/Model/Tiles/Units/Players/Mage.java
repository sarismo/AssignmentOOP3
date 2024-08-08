package Model.Tiles.Units.Players;

import Model.Tiles.Units.Enemies.Enemy;
import Utils.Generators.Generator;
import Utils.Position;

import java.util.List;
import java.util.Random;

public class Mage extends Player {

    protected int Mana_Pool;
    protected int Current_Mana;
    protected int Mana_Cost;
    protected int Spell_Power;
    protected int Hits_Count;
    protected int Ability_Range;
    private Random randomEnemy;

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

    @Override
    public void CastAbility(List<Enemy> enemies) {

    }

    private int MageGainManaPool()
    {
        return 25 * level;
    }

    private int MageGainSpellPower()
    {
        return 10 * level;
    }

    @Override
    public void intialiize(Position position1, Generator generator) {

    }

    @Override
    public String describe() {
        return super.describe() + "\t\tMana_Pool: " + this.Mana_Pool;
    }
}

