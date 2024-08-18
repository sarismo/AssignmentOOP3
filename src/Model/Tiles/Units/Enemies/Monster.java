package Model.Tiles.Units.Enemies;

import Model.Tiles.Units.Players.Player;
import Model.Tiles.Units.Units;

import Utils.Callbacks.MessageCallback;

import Utils.Generators.Generator;
import Utils.Generators.RandomGenerator;
import Utils.Position;

public class Monster extends Enemy{
    Integer VisionRange;
    RandomGenerator randomGenerator= new RandomGenerator();

    public Monster(char tile, String Name, int hitPoints, int attack_Points, int defense_Points, int experienceValue, int visionRange) {
        super(tile, Name, hitPoints, attack_Points, defense_Points, experienceValue);
        this.VisionRange = visionRange;
    }
    public String EnemyOnGameTick(Player p){
        int dx = position.getX() - p.getPosition().getX();
        int dy = position.getY() - p.getPosition().getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance < VisionRange) {
            // The player is within vision range, so the monster will chase the player
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    //moveLeft();
                    return "a";
                } else {
                   // moveRight();
                    return "d";
                }
            } else {
                if (dy > 0) {
                    //moveUp();
                    return "w";

                } else {
                    //moveDown();
                    return "s";

                }
            }
        }
            // The player is not within vision range, perform a random movement action


        return randomMove();
    }

    private void moveLeft() {
        position.setX(position.getX() - 1);
        messageCallback.send("Monster moves left.");
    }

    private void moveRight() {
        position.setX(position.getX() + 1);
        messageCallback.send("Monster moves right.");
    }

    private void moveUp() {
        position.setY(position.getY() - 1);
        messageCallback.send("Monster moves up.");
    }

    private void moveDown() {
        position.setY(position.getY() + 1);
        messageCallback.send("Monster moves down.");
    }

    private String randomMove() {
        String Reaction;
        int action =randomGenerator.generate(5) ; // 0: left, 1: right, 2: up, 3: down, 4: stay in place
        switch (action) {
            case 0:
                //moveLeft();
                Reaction="a";
                break;
            case 1:
                //moveRight();
                Reaction="d";
                break;
            case 2:
                //moveUp();
                Reaction="w";
                break;
            case 3:
                //0moveDown();
                Reaction="s";
                break;
            case 4:
<<<<<<< HEAD
//                System.out.println("Monster stays in place.");
=======

                messageCallback.send("Monster stays in place.");

                System.out.println("Monster stays in place.");
>>>>>>> 87929f4c87d522c1147512e5bd369b057f88630b
                Reaction="";

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
        return Reaction;
    }

    @Override
    public String describe() {
        return super.describe() + "\t\tVisionRange : " + this.VisionRange;
    }
    @Override
    public void Death(Units Killer){
        Killer.addExperience(this.experienceValue);
        messageCallback.send("Monster " + this.getName() + " died.");
    }
}
