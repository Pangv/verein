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
package de.lebk.verein.view.component_thirdlevel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class DashboardUsersPanel extends JPanel {

  private JLabel userProfileImage;
  private String userName = "Test User";
  private Dimension prefDimension =
      new Dimension(this.getPreferredSize().width, this.getPreferredSize().width);

  public DashboardUsersPanel() {
    this.createComponent();
  }

  public void createComponent() {
    try {
      this.setLayout(new GridLayout(2, 4));
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 4; j++) {
          this.add(userProfileImage =
              new ImageLabel("D:\\workspaces\\MultiProjects\\verein\\verein\\assets\\logo.png"));
          userProfileImage.setToolTipText(userName);
          userProfileImage.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(DashboardUsersPanel.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
