package de.lebk.verein.utilities;

import de.lebk.verein.member.Member;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.Clock;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class SideContainer extends JPanel {

    private JButton jBtnConfig;
    private JButton jBtnLogout;

    private Member member;

    public SideContainer(Member member) {
        this.createComponent();
        this.defineActions(member);
    }

    private void createComponent() {
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

    }

    private void defineActions(Member member) {

        jBtnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jBtnConfig, "Einstellungen für " + member.getFirstName() + " " + member.getLastName() + ".");
            }
        });

        jBtnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jBtnConfig, "Ausgeloggt!");
                System.exit(0);
            }
        });

    }

}
