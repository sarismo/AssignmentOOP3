import Model.Tiles.Units.Players.Player;
import Model.Tiles.Units.Units;
import Utils.Generators.FixedGenerator;
import Utils.Position;

public class Main {
    public static void main(String[] args) {
        Units p = new Player("Faroq",100,10,5);
        p.intialiize(new Position(0,0),new FixedGenerator(),()-> System.out.println("Player Death"),  System.out :: println );
        System.out.println("Hello world!");
    }
}