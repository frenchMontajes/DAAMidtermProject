import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TSPGUI extends JFrame {
    static int[][] distanceMatrix = {
            {0, 300, 150, 200},
            {150, 0, 200, 300},
            {100, 120, 0, 200},
            {200, 200, 100, 0}
    };

    static String[] locations = {"St. Peter", "St. John", "Lanao", "Maguindanao"};
    static int numberOfNodes = locations.length;
    static boolean[] visited = new boolean[numberOfNodes];
    static int[] path = new int[numberOfNodes + 1];
    static int minimumCost = Integer.MAX_VALUE;

    private JButton backButton;

    private JComboBox<String> startingPointComboBox;
    private static JTextArea outputTextArea;

    Integer frameHeight= 600;
    Integer frameWidth = 700;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public TSPGUI() {
        setLocation((dim.width - frameWidth)/ 2,(dim.height - frameHeight) / 2);
        setSize(frameWidth, frameHeight);
        setTitle("Traveling Salesman Problem Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Customer's Name:");
        nameLabel.setBounds(20, 20, 150, 25);
        add(nameLabel);



        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(180, 20, 200, 25);
        add(nameTextField);

        JLabel addressLabel = new JLabel("Select Starting Point:");
        addressLabel.setBounds(20, 60, 150, 25);
        add(addressLabel);

        startingPointComboBox = new JComboBox<>(locations);
        startingPointComboBox.setBounds(180, 60, 200, 25);
        add(startingPointComboBox);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(180, 100, 100, 30);
        add(calculateButton);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton){
                    dispose();
                    try {
                        FirstPage firstPage = new FirstPage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        backButton.setBounds(280, 100, 100, 30);
        add(backButton);

        outputTextArea = new JTextArea();
        outputTextArea.setBounds(20, 150, 650, 300);
        add(outputTextArea);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName = nameTextField.getText();
                String selectedLocation = (String) startingPointComboBox.getSelectedItem();
                int startingPointIndex = startingPointComboBox.getSelectedIndex();

                // Reset minimumCost
                minimumCost = Integer.MAX_VALUE;

                // Clear the output text area
                outputTextArea.setText("");

                path[0] = startingPointIndex;
                tsp(1, 0);

                // Display selected address in the output text area
                displaySelectedAddress(selectedLocation);

                // Print tour cost to the output text area
                printTourCost();

            }
        });
    }

    private void displaySelectedAddress(String selectedLocation) {
        switch(selectedLocation.toLowerCase()) {
            case "st. peter":
                outputTextArea.append("Address: #1 St. Peter street, Peter subdivision, Culiat, Quezon City\n\n");
                break;
            case "st. john":
                outputTextArea.append("Address: #2 St. John street, Barangay 123, UP Diliman, Quezon City\n\n");
                break;
            case "lanao":
                outputTextArea.append("Address: #3 Lanao street, Barangay 789, Teachers Village, San Juan\n\n");
                break;
            case "maguindanao":
                outputTextArea.append("Address: #4 Maguindanao Street, Metro Heights Subdivision, Xavierville, Pasig\n\n");
                break;
            default:
                outputTextArea.append("Address not found\n");
        }
    }

    static void tsp(int currentVertex, int currentCost) {
        if (currentVertex == numberOfNodes) {
            if (distanceMatrix[path[currentVertex - 1]][0] != 0) {
                int totalCost = currentCost + distanceMatrix[path[currentVertex - 1]][0];
                if (totalCost < minimumCost) {
                    minimumCost = totalCost;
                }
            }
            return;
        }

        for (int i = 0; i < numberOfNodes; i++) {
            if (!visited[i] && distanceMatrix[path[currentVertex - 1]][i] != 0) {
                visited[i] = true;
                path[currentVertex] = i;
                tsp(currentVertex + 1, currentCost + distanceMatrix[path[currentVertex - 1]][i]);
                visited[i] = false;
            }
        }
    }

    static void printTourCost() {
        int[][] permutations = {
                {0, 1, 2, 3, 0},
                {0, 1, 3, 2, 0},
                {0, 2, 1, 3, 0},
                {0, 2, 3, 1, 0},
                {0, 3, 1, 2, 0},
                {0, 3, 2, 1, 0}
        };
        int cost1 = Integer.MAX_VALUE;
        for (int[] permutation : permutations) {
            int cost = 0;

            StringBuilder sb = new StringBuilder(locations[path[0]]);

            for (int i = 0; i < permutation.length - 1; i++) {
                sb.append("->");
                sb.append(locations[path[permutation[i]]]);
                sb.append(" ");
                int distance = distanceMatrix[path[permutation[i]]][path[permutation[i + 1]]];
                sb.append("(").append(distance).append(") "); // Add distance to the route
                cost += distance;
            }

            sb.append("->").append(locations[path[0]]);
            int lastDistance = distanceMatrix[path[permutation[permutation.length - 1]]][path[0]];
            sb.append(" (").append(lastDistance).append(")");
            cost += lastDistance;

            if (cost < cost1) {
                cost1 = cost;
            }

            outputTextArea.append(sb.toString() + " Total Distance: " + cost + "\n");
        }
        outputTextArea.append("Shortest Route: " + cost1);
    }

    }
