package Model.Tiles.Units.Enemies;

import Model.Tiles.Units.Players.Player;
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
    public void EnemyOnGameTick(Player p){
        int dx = position.getX() - p.getPosition().getX();
        int dy = position.getY() - p.getPosition().getY();

        if (getPosition().Range(p.getPosition()) < VisionRange) {
            // The player is within vision range, so the monster will chase the player
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    moveLeft();
                } else {
                    moveRight();
                }
            } else {
                if (dy > 0) {
                    moveUp();
                } else {
                    moveDown();
                }
            }
        } else {
            // The player is not within vision range, perform a random movement action
            randomMove();
        }
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

    private void randomMove() {
        int action =randomGenerator.generate(5) ; // 0: left, 1: right, 2: up, 3: down, 4: stay in place
        switch (action) {
            case 0:
                moveLeft();
                break;
            case 1:
                moveRight();
                break;
            case 2:
                moveUp();
                break;
            case 3:
                moveDown();
                break;
            case 4:
                messageCallback.send("Monster stays in place.");
                break;
        }
    }

    @Override
    public String describe() {
        return super.describe() + "\t\tVisionRange : " + this.VisionRange;
    }
}
