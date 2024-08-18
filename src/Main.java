import Model.Game.Game;
import Utils.Callbacks.MessageCallback;

public class Main {

    public static void main(String[] args) {
        MessageCallback msg = (Message) -> System.out.println(Message);
        Game g = new Game(args[1],msg);
        g.activateGame();





    }
}