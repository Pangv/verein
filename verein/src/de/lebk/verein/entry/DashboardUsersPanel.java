package de.lebk.verein.entry;

import de.lebk.verein.member.Member;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class DashboardUsersPanel extends JPanel {

    private Dimension prefDimension
            = new Dimension(this.getPreferredSize().width, this.getPreferredSize().width);

    private final Member member;

    public DashboardUsersPanel(Member member) {
        this.member = member;
        this.createComponent();
    }

    public void createComponent() {
        this.setLayout(new GridLayout(2, 4));

    }

}
