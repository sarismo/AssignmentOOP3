package Utils;

public class Health {
    public int Health_pool;
    public int Health_amount;

    public Health(int capacity){
        this.Health_pool = capacity;
        this.Health_amount = capacity;
    }

    public int takeDamage(int damageTaken) {
        damageTaken = Math.max(0,damageTaken);
        damageTaken = Math.min(Health_amount,damageTaken);
        Health_amount -= damageTaken ;
        return damageTaken;
    }

    public int getHealthAmount() {
        return Health_amount ;
    }
    public int getHealthPool(){
        return Health_pool;
    }

    public void IncreaseMax(int healthGain) {
        Health_pool += healthGain ;
    }

    public void heal() {
        this.Health_amount = Health_pool ;
    }
}
