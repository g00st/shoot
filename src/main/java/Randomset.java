import java.util.ArrayList;
import java.util.Random;

class Randomset{
    ArrayList<weightedcolor> colors;
    private Random rand = new Random();
    private float sum = 0;
    Randomset(){
        colors = new ArrayList<weightedcolor>();
    }
    void addcolor(int color, float probability){
        if (probability + sum > 1.1f){
            throw new IllegalArgumentException("sum of propabilitys is greater than 1");
        }
        weightedcolor wc = new weightedcolor();
        wc.color = color;
        sum += probability;
        wc.propability = sum;

        colors.add(wc);
        System.out.println(wc.propability);
    }

    int getrandomcolor(){
        float r = rand.nextFloat() ;
        for (weightedcolor wc : colors){
            if (r < wc.propability){
                return wc.color;
            }
        }
        return colors.get(colors.size()-1).color;
    }

    class weightedcolor{
        public int color;
        public float propability;
    }
}