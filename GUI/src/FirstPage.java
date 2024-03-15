import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FirstPage implements ActionListener {
    private JFrame frame;
    private JPanel panelCenter;
    private JButton button1, button2, button3, exitButton;
    Integer frameHeight= 600;
    Integer frameWidth = 600;

    public FirstPage() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\frenc\\IdeaProjects\\Practice Projects\\GUI\\src\\logo.png"));
        Image img = bufferedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(img);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        panelCenter = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        button1 = new JButton("Knapsack");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button1){
                    frame.dispose();
                    try {
                        GUI openGui = new GUI();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        button1.setPreferredSize(new Dimension(120, 25));
        panelCenter.add(button1, gbc);

        gbc.gridy = 1;
        button2 = new JButton("Delivery");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TSPGUI().setVisible(true);
                    }
                });
                }
            }
        );
        button2.setPreferredSize(new Dimension(120, 25));
        panelCenter.add(button2, gbc);

        gbc.gridy = 2;
        button3 = new JButton("String Match");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new StringMatchingGUI().setVisible(true);
                    }
                });
            }
        });
        button3.setPreferredSize(new Dimension(120, 25));
        panelCenter.add(button3, gbc);

        gbc.gridy = 3;
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    ExitPage exit =new ExitPage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        exitButton.setPreferredSize(new Dimension(120, 25));
        panelCenter.add(exitButton, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        JLabel newLogo = new JLabel(logo);
        panelCenter.add(newLogo, gbc);

        panelCenter.setBackground(new Color(146, 164, 199));

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}