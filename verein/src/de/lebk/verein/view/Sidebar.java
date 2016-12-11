/*
 * The MIT License
 *
 * Copyright 2016 sopaetzel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.lebk.verein.view;

import de.lebk.verein.view.panel.ImagePanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class Sidebar extends JPanel {

  private JButton jBtnConfig;
  private JButton jBtnLogout;
  private ImagePanel profileImage;

  public Sidebar() {
    try {
      this.createAndShowComponent();
    } catch (IOException ex) {
      Logger.getLogger(Sidebar.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void createAndShowComponent() throws IOException {
    BufferedImage image =
        ImageIO.read(new File("D:\\workspaces\\MultiProjects\\verein\\verein\\assets\\logo.png"));
    BufferedImage img = new BufferedImage(100, 86, BufferedImage.TYPE_INT_ARGB);
    img.getGraphics().drawImage(image, 0, 0, 100, 86, null);

    JLabel label = new JLabel(new ImageIcon(img));
    this.add(label);

    try {
      ClassLoader cl = getClass().getClassLoader();
      profileImage =
          new ImagePanel("D:\\workspaces\\MultiProjects\\verein\\verein\\assets\\logo.png");
      jBtnConfig = new JButton("Einstellung");
      jBtnConfig.setMaximumSize(
          new Dimension(Short.MAX_VALUE, (int) jBtnConfig.getPreferredSize().getHeight()));
      jBtnLogout = new JButton("Ausloggen");
      jBtnLogout.setMaximumSize(
          new Dimension(Short.MAX_VALUE, (int) jBtnLogout.getPreferredSize().getHeight()));

      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      this.add(profileImage);
      this.add(Box.createVerticalGlue());
      this.add(jBtnConfig);
      this.add(jBtnLogout);

    } catch (MalformedURLException e) {
      Logger.getLogger(Sidebar.class.getName()).log(Level.SEVERE, null, e);
      e.printStackTrace();
    } catch (IOException e) {
      Logger.getLogger(Sidebar.class.getName()).log(Level.SEVERE, null, e);
      e.printStackTrace();
    }

  }

}
