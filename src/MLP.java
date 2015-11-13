import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by rueian on 2015/11/12.
 */
public class MLP {
    private List<Data> dataSet;
    private HashMap<Double, Output> outputSet = new HashMap<>();
    private List<List<Perceptron>> network = new ArrayList<>();

    private int[] structure;

    private int learningTimes;
    private double learningRate;
    private double converge;


    public MLP(List<Data> dataSet, int[] structure, double learningRate, int learningTimes, double converge) {
        this.dataSet = dataSet;
        this.structure = structure;
        this.learningRate = learningRate;
        this.learningTimes = learningTimes;
        this.converge = converge;

        // 正規化輸出
        double[] expected = dataSet.stream().mapToDouble(d -> d.expected).distinct().toArray();
        double outputNum = expected.length;
        double outputMax = structure[structure.length - 1];

        for (int i = 0; i < outputNum; i ++) {
            double desire = i * (outputMax / (outputNum - 1));
            double low    = (desire + (i - 1) * (outputMax / (outputNum - 1))) / 2;
            double high   = (desire + (i + 1) * (outputMax / (outputNum - 1))) / 2;
            outputSet.put(expected[i], new Output(low, desire, high));
        }

        // 初始化網路
        network.add(new ArrayList<>());
        int dimension = dataSet.get(0).dimension();
        for (int i = 0; i < dimension; i ++) {
            network.get(0).add(new Perceptron(0));
        }
        for (int i = 0; i < structure.length; i ++) {
            network.add(new ArrayList<>());
            for (int j = 0; j < structure[i]; j ++) {
                network.get(i + 1).add(new Perceptron(network.get(i).size()));
            }
        }
    }

    private void train(Data data) {
        int dimension = data.dimension();

        // 將輸入放在 y0
        for (int i = 0; i < dimension; i ++) {
            network.get(0).get(i).y = data.get(i);
        }

        // 開始走訪每一層
        for (int i = 1; i < network.size(); i ++) {
            double[] input = network.get(i - 1).stream().mapToDouble(s -> s.y).toArray();
            for (int j = 0; j < network.get(i).size(); j ++) {
                network.get(i).get(j).calc(input);
            }
        }

        // 倒傳遞階段 1 輸出層



        inspect();
    }

    private void inspect() {
        Set<Double> keys = outputSet.keySet();
        for(Double key : keys) {
            Output o = outputSet.get(key);
            System.out.println("key " + key + " low " + o.low + " desire " + o.desired + " high " + o.high);
        }

        for(List<Perceptron> list : network) {
            for(Perceptron p : list)
                System.out.print(Math.round( p.y * 100.0 ) / 100.0 + " ");
            System.out.println();
        }

        System.out.println();
        for(List<Perceptron> list : network)
            for(Perceptron p : list) {
                for(double ww : p.w)
                    System.out.print(Math.round( ww * 100.0 ) / 100.0 + " ");
                System.out.println();
            }
    }

    public void training() {
        train(dataSet.get(0));
    }
}
