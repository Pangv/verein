package de.lebk.verein.event;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Sven-Oliver Pätzel
 */
public class EventManager extends JPanel {

    private Club club;

    private EventDialog eventDialog;

    private JList<Event> lstEvents;
    private JScrollPane scrollPane;

    private JButton jBtnJoin;
    private JButton jBtnLeave;
    private JButton jBtnCreate;
    private JButton jBtnInfo;
    private JComboBox<String> jCmbEventType;

    private Member loggedMember;

    public EventManager(Club club, Member member) {
        this.createComponent();
        this.club = club;
        this.defineActions();
        this.loggedMember = member;
    }

    private void createComponent() {
        GridBagConstraints grid = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        lstEvents = new JList<>();

        scrollPane = new JScrollPane(lstEvents);
        scrollPane.setPreferredSize(new Dimension(200, 50));

        jBtnJoin = new JButton("Anmelden");
        jBtnLeave = new JButton("Abmelden");
        jBtnInfo = new JButton("Info");

        jCmbEventType = new JComboBox<>();
        jCmbEventType.addItem(EventTypes.GENERIC.returnType(EventTypes.LAPIDATION));
        jCmbEventType.addItem(EventTypes.GENERIC.returnType(EventTypes.CHILDTOURNAMENT));
        jCmbEventType.addItem(EventTypes.GENERIC.returnType(EventTypes.GENERIC));
        jBtnCreate = new JButton("Erstellen");

        grid.anchor = GridBagConstraints.FIRST_LINE_START;
        grid.insets = new Insets(2, 2, 2, 2);

        grid.fill = GridBagConstraints.BOTH;
        grid.weightx = 1;
        grid.weighty = 1;
        grid.gridheight = 10;
        grid.gridwidth = 5;
        grid.gridx = 0;
        grid.gridy = 0;
        this.add(scrollPane, grid);

        // adding join btn
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        grid.gridx = 5;
        grid.gridy = 0;
        this.add(jBtnJoin, grid);
        // adding leave btn
        grid.gridheight = 1;
        grid.gridwidth = 2;
        grid.gridx = 5;
        grid.gridy = 1;
        this.add(jBtnLeave, grid);
        // adding info btn
        grid.gridheight = 1;
        grid.gridwidth = 2;
        grid.gridx = 5;
        grid.gridy = 2;
        this.add(jBtnInfo, grid);
        // adding combobox btn
        grid.gridheight = 1;
        grid.gridwidth = 2;
        grid.gridx = 7;
        grid.gridy = 8;
        this.add(jCmbEventType, grid);
        // adding create btn
        grid.gridheight = 1;
        grid.gridwidth = 2;
        grid.gridx = 7;
        grid.gridy = 9;
        this.add(jBtnCreate, grid);

    }

    private void defineActions() {
        jBtnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedRow = lstEvents.getSelectedIndex();
            }
        });

        jBtnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked");
                eventDialog = new EventDialog("Veranstaltung: " + jCmbEventType.getSelectedItem() + " erstellen", jCmbEventType.getSelectedItem().toString(), club, loggedMember);
            }
        });

    }

}

