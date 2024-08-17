package Model.Game;

import Control.intializers.TileFactory;
import Model.Game.Level;
import Utils.Callbacks.DeathCallback;
import Utils.Callbacks.MessageCallback;

import java.util.Scanner;

public class Game {
    private Level currentLevel;
    private final String directoryPath;
    private final TileFactory tileFactory;
    private final MessageCallback msg;
    private final Scanner scanner;


    public Game(String directoryPath, MessageCallback msg) {
        this.directoryPath = directoryPath;
        this.msg = msg;
        this.tileFactory = new TileFactory(msg);
        this.scanner = new Scanner(System.in);
        initializeGame();
    }

    private void initializeGame() {
        msg.send("Notice: You must place the level_dir folder correctly.");
        msg.send("Have you done it? Enter 0 to continue.");

        if (scanner.nextInt() == 0) {
            selectPlayer();

        } else {
            msg.send("Please reload the game and ensure the directory is correctly set up.");
        }
    }

    private void selectPlayer() {
        tileFactory.printThePlayers();
        msg.send("Choose your player from the list (Enter a number between 1 and 7):");

        int playerChosen = scanner.nextInt();
        while (playerChosen < 1 || playerChosen > 7) {
            msg.send("Invalid player chosen. Please select a valid player number.");
            playerChosen = scanner.nextInt();
        }
        this.currentLevel = new Level(msg,playerChosen);
        currentLevel.SetPlayer(tileFactory.getPlayer(playerChosen));
    }

    public void activateGame() {
        int levelNumber = 1;

        while (currentLevel.hasLevel(getLevelFilePath(levelNumber)) && !currentLevel.gameOver()) {
            currentLevel.loadLevel(getLevelFilePath(levelNumber));
            loadAndPlayLevel(levelNumber);
            levelNumber++;
        }

        msg.send("\n GAME OVER!!!");
    }

    private void loadAndPlayLevel(int levelNumber) {
        while (!currentLevel.gameOver() && !currentLevel.isOver()) {
            EmptyRow();
            currentLevel.levelInfo();
            msg.send("Your turn - ");
            String userAction = scanner.nextLine();
            currentLevel.gameTick(userAction);
        }
    }

    private String getLevelFilePath(int levelNumber) {
        return directoryPath + "\\level" + levelNumber + ".txt";
    }

    private void EmptyRow() {
        msg.send("");
    }
}
