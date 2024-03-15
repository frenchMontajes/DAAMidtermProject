import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExitPage extends JFrame {

    private JFrame frame;
    private JPanel panelCenter;
    private JLabel creditLabel;
    private JLabel newLogo;

    Integer frameHeight = 600;
    Integer frameWidth = 600;

    public ExitPage() throws IOException {

        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\frenc\\IdeaProjects\\Practice Projects\\GUI\\src\\logo.png"));
        Image img = bufferedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(img);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        newLogo = new JLabel();
        newLogo.setIcon(logo);

        panelCenter = new JPanel(new GridBagLayout());

        String creditText = "<html><center>Created by:<br>Angelo Robee Herrera<br>Tj William Yumul<br>Daniel Joshua Saberon<br>French Anthony D. Montajes</center></html>";
        creditLabel = new JLabel(creditText);
        creditLabel.setHorizontalAlignment(JLabel.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 20, 10);
        panelCenter.add(creditLabel, gbc);
        panelCenter.setBackground(new Color(146, 164, 199));

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(newLogo, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.getContentPane().setBackground(new Color(134, 135, 138));
        frame.setLocation((dim.width - frameWidth) / 2, (dim.height - frameHeight) / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Main GUI");
        frame.setResizable(false);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
    }

}