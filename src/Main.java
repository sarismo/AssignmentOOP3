import Model.Game.Game;
import Utils.Callbacks.MessageCallback;

public class Main {

    public static void main(String[] args) {
        MessageCallback msg = new MessageCallback() {
            @Override
            public void send(String message) {
                System.out.println(message);
            }
        };
        Game g = new Game(args[2],msg);
        g.activateGame();





    }
}