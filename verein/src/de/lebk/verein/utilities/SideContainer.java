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
package de.lebk.verein.utilities;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class SideContainer extends JPanel {

  private JButton jBtnConfig;
  private JButton jBtnLogout;
  private JLabel jLblUserImage;

  public SideContainer() {
    this.createComponent();
  }

  public void createComponent() {
    try {
      jLblUserImage =
          new ImageLabel("D:\\workspaces\\MultiProjects\\verein\\verein\\resources\\profile.png");
      this.add(jLblUserImage);
    

    jBtnConfig = new JButton("Einstellung");
    jBtnConfig.setMaximumSize(
        new Dimension(Short.MAX_VALUE, (int) jBtnConfig.getPreferredSize().getHeight()));
    jBtnLogout = new JButton("Ausloggen");
    jBtnLogout.setMaximumSize(
        new Dimension(Short.MAX_VALUE, (int) jBtnLogout.getPreferredSize().getHeight()));

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


    this.add(Box.createVerticalGlue());
    this.add(jBtnConfig);
    this.add(jBtnLogout);
    
    } catch (IOException e) {
        System.err.println("File not Exception" + e.getMessage());
    }

  }

}
