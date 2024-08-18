import Model.Game.Game;
import Utils.Callbacks.DeathCallback;
import Utils.Callbacks.MessageCallback;

import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        MessageCallback msg = (Message) -> System.out.println(Message);
        Game g = new Game(args[1],msg);

        MessageCallback msg = new MessageCallback() {
            @Override
            public void send(String message) {
                System.out.println(message);
            }
        };
        String levelsDirPath = Paths.get("src//levels_dir").toAbsolutePath().toString();

        Game g = new Game(levelsDirPath,msg);

        g.activateGame();





    }
}