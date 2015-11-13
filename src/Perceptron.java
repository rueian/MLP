import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rueian on 2015/11/13.
 */
public class Perceptron {
    public List<Double> w = new ArrayList<>();
    public List<Double> wp = new ArrayList<>();
    public double y = 0.0;
    public double yp = 0.0;

    public Perceptron(int dimension) {
        Random random = new Random(System.nanoTime());
        for (int i = 0; i <= dimension; i ++) {
            w.add(random.nextDouble());
            wp.add(0.0);
        }
    }

    public double calc(double[] input) {
        if (input.length + 1 != w.size()) {
            System.out.println("長度不符合～～");
        }

        this.yp = this.y; // save previous output

        double sum = -1 * w.get(w.size() - 1);
        for (int i = 0; i < input.length; i ++) {
            sum += input[i] * w.get(i);
        }

        this.y = sigmoid(sum); // save new output

        return this.y;
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}