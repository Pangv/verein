package de.lebk.verein.event;

import de.lebk.verein.club.Club;
import de.lebk.verein.login.Auth;
import de.lebk.verein.member.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author sopaetzel
 */
public class EventManager extends JPanel {

    private Club club;

    private JList<Event> lstAllEvents;
    private JList<Event> lstOwnEvents;

    private JTable tblAllEvents = new JTable(new DefaultTableModel(new Object[]{"Titel", "Veranstalter", "Datum", "Uhrzeit"}, 0));
    private JTable tblOwnEvents = new JTable(new DefaultTableModel(new Object[]{"Titel", "Veranstalter", "Datum", "Uhrzeit"}, 0));

    private JButton jBtnJoin;
    private JButton jBtnLeave;
    private JButton jBtnCreate;
    private JButton jBtnReload;
    private JButton jBtnInfo;
    private JComboBox<String> jCmbEventType;

    private Member loggedMember;

    public EventManager(Club club, Member member) {
        this.createComponent();
        this.club = club;
        this.defineActions();
        this.loggedMember = member;
        addEventsToAllList();
        addEventsToOwnList();
    }

    private void createComponent() {
        GridBagConstraints grid = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        lstAllEvents = new JList<>();
        lstOwnEvents = new JList<>();

        JScrollPane scrlPaneAllEvents = new JScrollPane(lstAllEvents);
        JScrollPane scrlPaneOwnEvents = new JScrollPane(lstOwnEvents);


        jBtnJoin = new JButton("Anmelden");
        jBtnLeave = new JButton("Abmelden");
        jBtnInfo = new JButton("Info");
        jBtnReload = new JButton("Aktualisieren");

        jCmbEventType = new JComboBox<>();
        jCmbEventType.addItem(EventTypes.GENERIC.returnType(EventTypes.LAPIDATION));
        jCmbEventType.addItem(EventTypes.GENERIC.returnType(EventTypes.CHILDTOURNAMENT));
        jCmbEventType.addItem(EventTypes.GENERIC.returnType(EventTypes.GENERIC));
        jBtnCreate = new JButton("Erstellen");

        grid.anchor = GridBagConstraints.FIRST_LINE_START;
        grid.insets = new Insets(2, 2, 2, 2);

        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.weightx = 0.5;
        grid.weighty = 0.5;

        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 0;
        grid.gridy = 0;
        this.add(new JLabel("Alle Veranstaltungen"), grid);

        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 0;
        grid.gridy = 7;
        this.add(new JLabel("Meine Veranstaltungen"), grid);

        grid.fill = GridBagConstraints.BOTH;
        grid.weightx = 1;
        grid.weighty = 1;

        grid.gridheight = 6;
        grid.gridwidth = 5;
        grid.gridx = 0;
        grid.gridy = 1;
        this.add(scrlPaneAllEvents, grid);

        grid.gridheight = 6;
        grid.gridwidth = 5;
        grid.gridx = 0;
        grid.gridy = 8;
        this.add(scrlPaneOwnEvents, grid);

        // adding join btn
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.weightx = .5;
        grid.weighty = .5;
        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 5;
        grid.gridy = 0;
        this.add(jBtnJoin, grid);
        // adding leave btn
        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 5;
        grid.gridy = 1;
        this.add(jBtnLeave, grid);
        // adding info btn
        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 5;
        grid.gridy = 2;
        this.add(jBtnInfo, grid);
        // adding combobox btn
        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 5;
        grid.gridy = 8;
        this.add(jCmbEventType, grid);
        // adding create btn
        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 5;
        grid.gridy = 9;
        this.add(jBtnCreate, grid);
        // adding create btn
        grid.anchor = GridBagConstraints.PAGE_END;
        grid.gridheight = 1;
        grid.gridwidth = 4;
        grid.gridx = 5;
        grid.gridy = 10;
        this.add(jBtnReload, grid);


    }

    private void defineActions() {
        jBtnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Event event = lstAllEvents.getSelectedValue();
                if (event.getAttendees() != null && !event.getAttendees().contains(Auth.getInstance().getCurrentUser())) {
                    event.addAttendee(Auth.getInstance().getCurrentUser());
                    Auth.getInstance().getClub().addEvent(event);

                    addEventsToOwnList();
                } else {
                    JOptionPane.showMessageDialog(null, "Sie sind bereits angemeldet");
                }
            }
        });

        jBtnLeave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Auth.getInstance().getClub().getEvents().remove(lstOwnEvents.getSelectedValue());
                addEventsToOwnList();
            }
        });

        jBtnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EventInfoDialog(lstAllEvents.getSelectedValue());
            }
        });

        jBtnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EventCreateDialog("Veranstaltung: " + jCmbEventType.getSelectedItem() + " erstellen", jCmbEventType.getSelectedItem().toString(), club, loggedMember);
            }
        });

        jBtnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEventsToOwnList();
                addEventsToAllList();
            }
        });

    }

    private void addEventsToAllList() {
        DefaultListModel<Event> model = new DefaultListModel<>();
        lstAllEvents.setModel(model);
        for (Event event : Auth.getInstance().getClub().getEvents()) {
            model.addElement(event);
        }
    }

    private void addEventsToOwnList() {
        DefaultListModel<Event> model = new DefaultListModel<>();
        lstOwnEvents.setModel(model);
        for (Event event : Auth.getInstance().getClub().getEventsForMember(Auth.getInstance().getCurrentUser())) {
            model.addElement(event);
        }

    }

}

