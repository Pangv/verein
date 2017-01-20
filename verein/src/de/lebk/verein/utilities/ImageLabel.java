package de.lebk.verein.utilities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class ImageLabel extends JLabel {

    private final BufferedImage image;
    private final BufferedImage scaledImage;

    public ImageLabel(String imagePath) throws IOException {
        image = ImageIO.read(new File(imagePath));
        int ratio = image.getWidth() / image.getHeight();
        scaledImage = new BufferedImage(100, 86, BufferedImage.TYPE_INT_ARGB);
        scaledImage.getGraphics().drawImage(image, 0, 0, 100, 86, null);
        this.setIcon(new ImageIcon(scaledImage));
    }

    public ImageLabel(URL imageURL) throws IOException {

        image = ImageIO.read(new File(imageURL.toString()));
        int ratio = image.getWidth() / image.getHeight();
        scaledImage = new BufferedImage(100, 86, BufferedImage.TYPE_INT_ARGB);
        scaledImage.getGraphics().drawImage(image, 0, 0, 100, 86, null);
        this.setIcon(new ImageIcon(scaledImage));
    }
}
