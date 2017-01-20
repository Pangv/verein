package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class DashboardUsersPanel extends JPanel {

    private Dimension prefDimension
            = new Dimension(this.getPreferredSize().width, this.getPreferredSize().width);

    private Club club;

    public DashboardUsersPanel(Club club) {
        this.club = club;
        this.createComponent();
    }

    public void createComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (Member member : club.getMembers()) {
            
           this.add(new JLabel(member.getFullName() + " ist: " + member.getUsername()));
            
        }
    }

}
