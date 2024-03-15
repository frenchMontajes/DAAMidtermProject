import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatchingGUI extends JFrame {
    private JTextField textField;
    private JTextField wordField;
    private JTextArea outputArea;
    private Integer frameWidth= 600;
    private Integer frameHeight = 600;

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public StringMatchingGUI() {
        setTitle("String Matching");
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setLocation((dim.width - frameWidth) / 2, (dim.height - frameHeight) / 2);


        // Panel for text input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel textLabel = new JLabel("Enter Address:");
        textField = new JTextField(40); // Enlarge the address field
        textField.setPreferredSize(new Dimension(400, 30));

        JLabel wordLabel = new JLabel("Enter the word to search:");
        wordField = new JTextField(30);
        wordField.setPreferredSize(new Dimension(200, 30));

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performStringMatching();
            }
        });
        searchButton.setPreferredSize(new Dimension(100, 30));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    FirstPage first = new FirstPage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        backButton.setPreferredSize(new Dimension(100, 30));

        inputPanel.add(textLabel);
        inputPanel.add(textField);
        inputPanel.add(wordLabel);
        inputPanel.add(wordField);
        inputPanel.add(searchButton);
        inputPanel.add(backButton);

        // Output area
        outputArea = new JTextArea();
        outputArea.setRows(10);
        outputArea.setColumns(30);
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(500, 200));


        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void performStringMatching() {
        String text = textField.getText();
        String wordToSearch = wordField.getText();

        // Use regular expression pattern to find word occurrences
        Pattern pattern = Pattern.compile("\\b" + wordToSearch + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        StringBuilder positions = new StringBuilder();

        while (matcher.find()) {
            count++;
            positions.append(getWordPosition(text, matcher.start())).append(" ");
        }

        // Display the results
        outputArea.setText("No of occurrences: " + count + "\nPositions: " + positions.toString());
    }

    // Get the word position in the string
    private int getWordPosition(String text, int index) {
        String[] words = text.split("\\s+");
        int position = 1;
        int currentPosition = 0;

        for (String word : words) {
            currentPosition += word.length();
            if (currentPosition >= index) {
                break;
            }
            currentPosition++; // accounting for the space
            position++;
        }

        return position;
    }

}