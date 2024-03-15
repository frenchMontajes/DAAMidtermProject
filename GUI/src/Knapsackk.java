import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Knapsackk {
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JLabel label;
    private int weightGross;

    private JLabel newLogo;

    private Integer frameHeight = 600;
    private Integer frameWidth = 600;
    private JPanel buttonPanel;
    private JPanel newPanel;
    private String[][] data;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


    public Knapsackk(Integer weightGross, Integer numberOfProducts) throws IOException {
        this.weightGross = weightGross;

        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\frenc\\IdeaProjects\\Practice Projects\\GUI\\src\\logo.png"));
        Image img = bufferedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(img);
        FlowLayout flow = new FlowLayout();

        newLogo = new JLabel();
        newLogo.setIcon(logo);

        frame = new JFrame();
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(146, 164, 199));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 30, 100, 30));

        JPanel logoScrollPanePanel = new JPanel(new BorderLayout());
        logoScrollPanePanel.add(newLogo, BorderLayout.NORTH);

        frame.setLocation((dim.width - frameWidth) / 2, (dim.height - frameHeight) / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Knapsack GUI");
        frame.setResizable(false);

        String[] column = {"Product Name", "Weight/box", "Amount"};
        data = new String[numberOfProducts][3];
        for (int i = 0; i < numberOfProducts; i++) {
            for (int j = 0; j < 3; j++) {
                data[i][j] = JOptionPane.showInputDialog("Enter data for product " + (i + 1) + ", field " + (j + 1));
            }
        }

        table = new JTable(data, column);
        JScrollPane scrollPane = new JScrollPane(table);

        logoScrollPanePanel.add(scrollPane, BorderLayout.CENTER);


        panel.add(logoScrollPanePanel, BorderLayout.CENTER);


        double totalAmount = calculateTotalAmount(weightGross, data);


        label = new JLabel("Total Amount: " + totalAmount);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton exit = new JButton("Back");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exit){
                    frame.dispose();
                    try {
                        FirstPage firstpage = new FirstPage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        exit.setBounds(455,450 ,100,35);
        frame.add(exit);

        panel.add(label, BorderLayout.SOUTH);
        addSortingButtons();

        panel.add(buttonPanel, BorderLayout.NORTH);

        frame.add(panel);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);


    }

    private void addSortingButtons() {
        JButton sortProductNameButton = new JButton("Sort Product Name");
        JButton sortWeightButton = new JButton("Sort Weight");
        JButton sortAmountButton = new JButton("Sort Amount");

        buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(sortProductNameButton);
        buttonPanel.add(sortWeightButton);
        buttonPanel.add(sortAmountButton);

        panel.add(buttonPanel, BorderLayout.NORTH);


        // Add action listeners to the buttons
        sortProductNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Sort by product name
                selectionSortByName(data);
                refreshTable();
            }
        });

        sortWeightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Sort by weight
                selectionSortByWeight(data);
                refreshTable();
            }
        });

        sortAmountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Sort by amount
                selectionSortByAmount(data);
                refreshTable();

            }
        });
    }

    private void refreshTable() {

        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Product Name", "Weight/box", "Amount"});
        table.setModel(model);

        table.revalidate();
        table.repaint();

        label.setText("Total Amount: " + calculateTotalAmount(weightGross, data));

        frame.revalidate();
        frame.repaint();
    }

    public double calculateTotalAmount(int weightLimit, String[][] data) {
        int n = data.length;
        int[] dp = new int[weightLimit + 1];

        for (int i = 0; i < n; i++) {
            double weight = Double.parseDouble(data[i][1]);
            int value = Integer.parseInt(data[i][2]);
            for (int j = weightLimit; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - (int) weight] + value);
            }
        }
        return dp[weightLimit];
    }

    private void selectionSortByName(String[][] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j][0].compareTo(data[minIndex][0]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                String[] temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
    }

    private void selectionSortByWeight(String[][] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                double currentWeight = Double.parseDouble(data[j][1]);
                double minWeight = Double.parseDouble(data[minIndex][1]);
                if (currentWeight < minWeight) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                String[] temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
    }

    private void selectionSortByAmount(String[][] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                int currentAmount = Integer.parseInt(data[j][2]);
                int minAmount = Integer.parseInt(data[minIndex][2]);
                if (currentAmount < minAmount) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                String[] temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
    }
}
