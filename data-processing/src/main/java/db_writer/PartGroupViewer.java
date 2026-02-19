package db_writer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PartGroupViewer {
    private JFrame frame;
    private JLabel imageLabel;

    public PartGroupViewer(Part partGroup) {
        frame = new JFrame(partGroup.getPartType());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        ImageIcon imageIcon = createImageIcon(partGroup.getImage());

        imageLabel = new JLabel();
        imageLabel.setIcon(imageIcon);

        frame.add(imageLabel);
        frame.setVisible(true);
    }

    public static ImageIcon createImageIcon(byte[] imageData) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
            Image image = ImageIO.read(bais);
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
