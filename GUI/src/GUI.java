import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI implements ActionListener {
    private JFrame frame;
    private JPanel panelCenter;
    private JButton button, backButton;
    private JLabel textLabel;
    private JLabel newLogo;
    private JLabel inputText;
    private JTextField weightText;
    private JTextField productField;
    Integer frameHeight= 600;
    Integer frameWidth = 600;

    public GUI() throws IOException {

        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\frenc\\IdeaProjects\\Practice Projects\\GUI\\src\\logo.png"));
        Image img = bufferedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(img);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        weightText = new JTextField("",3);

        button = new JButton("Enter");
        button.addActionListener(this);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==backButton){
                    frame.dispose();
                    try {
                        FirstPage firstPage = new FirstPage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        newLogo = new JLabel();
        newLogo.setIcon(logo);

        textLabel = new JLabel("Choose Weight (1-15) :");
        inputText = new JLabel("Enter number of Products: ");

        productField = new JTextField("", 3);


        panelCenter = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 3, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelCenter.add(textLabel, gbc);

        gbc.gridx = 1;
        panelCenter.add(weightText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCenter.add(inputText, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCenter.add(productField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panelCenter.add(button, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panelCenter.add(backButton, gbc);
        backButton.setPreferredSize(new Dimension(100,25));

        panelCenter.setBackground(new Color(146, 164, 199));
        button.setPreferredSize(new Dimension(100, 25));

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(newLogo, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.getContentPane().setBackground(new Color(134, 135, 138));
        frame.setLocation((dim.width - frameWidth)/ 2,(dim.height - frameHeight) / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Main GUI");
        frame.setResizable(false);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            frame.dispose();
            double selectedWeight = Double.parseDouble(weightText.getText());
            int numberOfProducts = Integer.parseInt(productField.getText());
            textLabel.setText("Weight: " + selectedWeight);
            try {
                new Knapsackk((int) selectedWeight, numberOfProducts);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}