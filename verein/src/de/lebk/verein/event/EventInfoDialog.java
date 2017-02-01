package de.lebk.verein.event;

import de.lebk.verein.login.Auth;
import de.lebk.verein.member.Member;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author sopaetzel
 */
public class EventInfoDialog extends JDialog {

    private Event event;
    private JButton btnDeleteEvent;

    public EventInfoDialog(Event event) {
        this.event = event;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


    private void init() {
        btnDeleteEvent = new JButton("Veranstaltung entfernen");
        JLabel lblTitle = new JLabel(event.getTitle());
        JLabel lblEventType = new JLabel(event.getEventType());
        JLabel lblOrganizer = new JLabel(event.getOrganizer().getFullName());

        this.add(lblTitle);
        this.add(lblEventType);
        this.add(lblOrganizer);


        for (Member member : event.getAttendees()) {
            JLabel lblAttendee = new JLabel(member.getFullName());
            this.add(lblAttendee);
        }

        this.add(btnDeleteEvent);
        this.actionListeners();
        this.pack();
        this.setVisible(true);
    }

    private void actionListeners() {
        btnDeleteEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Auth.getInstance().getClub().getEvents().remove(event);
            }
        });
    }

}
