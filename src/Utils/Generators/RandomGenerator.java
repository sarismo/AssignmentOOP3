package Utils.Generators;

import java.util.Random;

public class RandomGenerator implements Generator {
 private Random random ;

 public RandomGenerator(){
     random = new Random();
 }
 public RandomGenerator(long seed){
     random = new Random(seed);
 }
    @Override
    public int generate(int point) {
        return random.nextInt(point);
    }
}
