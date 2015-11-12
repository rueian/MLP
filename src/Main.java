import javax.swing.*;
import java.awt.*;

public class Main {

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

}
