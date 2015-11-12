import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Main {

    private static ArrayList<Data> dataSet;
    private static HashMap<Double, Double> outputSet;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JLabel filePathLabel = new JLabel("資料集");
        JTextField filePathField = new JTextField(System.getProperty("user.home"));
        JButton loadFileDialog = new JButton("選擇");
        JButton loadFileButton = new JButton("載入");

        JLabel structureLabel = new JLabel("架構");
        JTextField structureField = new JTextField("3,3,2");

        JLabel learnRateLabel = new JLabel("學習率");
        JTextField learnRateField = new JTextField("0.2");

        JLabel learnTimesLabel = new JLabel("學習次數");
        JTextField learnTimesField = new JTextField("10");

        JLabel convergeLabel = new JLabel("收斂小於");
        JTextField convergeField = new JTextField("0.5");

        JButton startButton = new JButton("開始");
        JButton pauseButton = new JButton("暫停");
        JButton resetButton = new JButton("重置");

        filePathLabel.setPreferredSize(new Dimension(60, 40));
        filePathField.setPreferredSize(new Dimension(280, 40));
        loadFileDialog.setPreferredSize(new Dimension(60, 40));
        loadFileButton.setPreferredSize(new Dimension(60, 40));

        structureLabel.setPreferredSize(new Dimension(60, 40));
        structureField.setPreferredSize(new Dimension(400, 40));

        structureLabel.setPreferredSize(new Dimension(60, 40));
        structureField.setPreferredSize(new Dimension(400, 40));

        learnRateLabel.setPreferredSize(new Dimension(60, 40));
        learnRateField.setPreferredSize(new Dimension(90, 40));

        learnTimesLabel.setPreferredSize(new Dimension(60, 40));
        learnTimesField.setPreferredSize(new Dimension(90, 40));

        convergeLabel.setPreferredSize(new Dimension(60, 40));
        convergeField.setPreferredSize(new Dimension(90, 40));

        startButton.setPreferredSize(new Dimension(150, 40));
        pauseButton.setPreferredSize(new Dimension(150, 40));
        resetButton.setPreferredSize(new Dimension(150, 40));

        loadFileDialog.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser("選擇");
            chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
            chooser.setAcceptAllFileFilterUsed( false );
            if ( chooser.showOpenDialog( frame ) == JFileChooser.APPROVE_OPTION ) {
                filePathField.setText( chooser.getSelectedFile().getAbsolutePath() );
            }
        });

        loadFileButton.addActionListener(e -> {
            File rawDataSet = new File(filePathField.getText());
            try (BufferedReader br = new BufferedReader(new FileReader(rawDataSet))) {
                dataSet = new ArrayList<>();
                outputSet = new HashMap<>();
                for (String line; (line = br.readLine()) != null;) {
                    double[] values = Arrays.stream(line.split(" |\t"))
                                            .filter(s -> s.length() > 0)
                                            .mapToDouble(Double::parseDouble).toArray();
                    dataSet.add(new Data(values));
                }
                for (Data data : dataSet) {
                    outputSet.put(data.expected, null);
                }
                inspect();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "無法解析所選檔案，請換一個試試看");
                return;
            }
        });

        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(500, 500));

        panel.add(filePathLabel);
        panel.add(filePathField);
        panel.add(loadFileDialog);
        panel.add(loadFileButton);

        panel.add(structureLabel);
        panel.add(structureField);

        panel.add(learnRateLabel);
        panel.add(learnRateField);

        panel.add(learnTimesLabel);
        panel.add(learnTimesField);

        panel.add(convergeLabel);
        panel.add(convergeField);

        panel.add(startButton);
        panel.add(pauseButton);
        panel.add(resetButton);

        frame.setContentPane(panel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void inspect() {
        for(int i = 0; i < dataSet.size(); i++) {
            for(double v : dataSet.get(i).values) {
                System.out.print(v + " ");
            }
            System.out.println(dataSet.get(i).expected);
        }
        Set<Double> keys = outputSet.keySet();
        for(Double key : keys) {
            System.out.println("key" + key);
        }
    }

}
