package Model.Utils.Generators;

public class FixedGenerator implements Generator{
    @Override
    public int generate(int point) {
        return point / 2;
    }
}
