package Model.Game;

import Control.intializers.LevelInitializer;
import Control.intializers.TileFactory;
import Model.Tiles.Empty;
import Model.Tiles.Tile;
import Model.Tiles.Units.Enemies.Enemy;
import Model.Tiles.Units.Enemies.Monster;
import Model.Tiles.Units.Enemies.Trap;
import Model.Tiles.Units.Players.Player;
import Model.Tiles.Units.Units;
import Utils.Callbacks.DeathCallback;
import Utils.Callbacks.MessageCallback;
import Utils.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class Level {
    private Board board;
    private List<Monster> monsters;
    private List<Trap> traps;
    private Player player;
    private MessageCallback msg;
    private LevelInitializer buildLevel;
    private TileFactory factory;


    public Level(MessageCallback msg, int pID) {
        this.msg = msg;
        this.monsters = new ArrayList<>();
        this.traps = new ArrayList<>();
        this.factory = new TileFactory(msg);
        this.buildLevel = new LevelInitializer(msg,pID,player);

    }
    public void updateGame()
    {
        List<Monster> aliveMonsters = new ArrayList<Monster>();
        List<Trap> aliveTraps = new ArrayList<Trap>();

        for (Monster m : this.monsters)
        {
            if (!m.alive()) {
                this.removeEnemy(m);
            }
            else
                aliveMonsters.add(m);

        }
        for (Trap t : this.traps)
        {
            if (!t.alive()) {
                this.removeEnemy(t);
            }
            else
                aliveTraps.add(t);
        }
        this.board.swapPosition(this.player.getPosition(),this.player.getPosition());
        this.traps = aliveTraps;
        this.monsters = aliveMonsters;
    }

    public void gameTick(String action) {
        Position tempPosition=player.getPosition();
        System.out.println("action is "+action);
        player.onGameTick();
        if(action.equals("e")){
            System.out.println("excuting ability");
            List<Enemy> enemeis = new LinkedList<>();
            for (Enemy enemy : monsters) {
                enemeis.add(enemy);
            }
            for (Enemy enemy : traps) {
                enemeis.add(enemy);
            }
            player.CastAbility(enemeis);
        }
        else{
            Interact(player, action);

        }
        this.board.swapPosition(this.player.getPosition(),tempPosition);
        updateGame();
        for (Monster monster : monsters) {
//            monster.EnemyOnGameTick(player);
            tempPosition = monster.getPosition();
            Interact(monster,monster.EnemyOnGameTick(this.player));
            this.board.swapPosition(monster.getPosition(),tempPosition);
            updateGame();
        }

        for (Trap trap : traps) {
            trap.onEnemyTurn(player);
            updateGame();
        }

        if (gameOver()) {
            msg.send("Player has been defeated!");
        } else if (isOver()) {
            msg.send("Level completed! All monsters have been defeated.");
        }
    }

    public boolean gameOver() {
        return !player.alive();
    }

    public boolean isOver() {
        return monsters.isEmpty();
    }

    public void loadLevel(String filePath) {
        board = buildLevel.initLevel(filePath);
        for (Tile tile : board.getTiles()) {
            char tileChar = tile.getTileCharacter();
            if (tile instanceof Enemy) {
                addEnemy(tile, tileChar);
            } else if (tile instanceof Player) {
                SetPlayer((Player) tile);
            }
        }

    }

    public void addEnemy(Tile t, char c) {
        if (c != '#' && c != '.') {
            if (c == 'B' || c == 'Q' || c == 'D') {
                traps.add((Trap) t);
            } else {
                monsters.add((Monster) t);
            }
        }
    }

    public void removeEnemy(Enemy enemy) {
        board.removeEnemy(enemy);
//        traps.remove(enemy);
//        monsters.remove(enemy);
    }

    public void Interact(Units u, String action) {
        Position newPosition = null;
        System.out.println(u instanceof Player ?"interacrt action |"+action+"|<":"");
        switch (action) {
            case "w":
                newPosition = new Position(u.getPosition().getX(), u.getPosition().getY() -1 );
                break;
            case "s":
                newPosition = new Position(u.getPosition().getX(), u.getPosition().getY() + 1);
                break;
            case "a":
                newPosition = new Position(u.getPosition().getX() - 1, u.getPosition().getY());
                break;
            case "d":
                newPosition = new Position(u.getPosition().getX() + 1, u.getPosition().getY());
                break;

            default:
                System.out.println(u instanceof Player ?"new pos is null , THE ACTION is -"+action+"- is equal?= "+(action.equals("w")||action.equals("d")||action.equals("a")||action.equals("s")):"");
                return;
        }
//        System.out.println(newPosition.toString());
//        System.out.println(this.board==null);
//        System.out.println(this.board.getTileInPosition(newPosition).toString());
        if (newPosition != null) {
            if( (u instanceof Player) && !(this.board.getTileInPosition(newPosition) instanceof Empty)) System.out.println(this.board.getTileInPosition(newPosition).getClass());
            u.Interact(this.board.getTileInPosition(newPosition));
        }
    }

    public void SetPlayer(Player player) {
        this.player = player;
    }
    public void levelInfo()
    {
        msg.send(this.board.toString());
        this.player.info();
    }
    public boolean hasLevel(String levelFilePath) {
        return buildLevel.levelExists(levelFilePath);
    }
}
