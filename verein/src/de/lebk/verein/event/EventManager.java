package de.lebk.verein.event;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Sven-Oliver Pätzel
 */
public class EventManager extends JPanel {

    private Club club;

    private EventDialog eventDialog;

    private JTable jTblEvents;
    private TableModel defaultModel;
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

        jTblEvents = new JTable();
        jTblEvents.setModel(new EventTableModel(this.club));
        scrollPane = new JScrollPane(jTblEvents);
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

    public void defineActions() {
        /**
         *
         */
        jBtnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedRow = jTblEvents.getSelectedRow();
            }
        });

        jBtnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked");
                eventDialog = new EventDialog(null, "Veranstaltung: " + jCmbEventType.getSelectedItem() + " erstellen", jCmbEventType.getSelectedItem().toString(), club, loggedMember);
            }
        });

    }

}

class EventTableModel extends DefaultTableModel {

    List<Event> events = new ArrayList<>();
    String[] columns = {"Titel", "Datum", "Leitung"};

    public EventTableModel(Club clubEvents) {
//        if (clubEvents != null && clubEvents.getEvents().size() > 0) {
//            for (Event event : clubEvents.getEvents()) {
//                events.add(event);
//            }
//        }
    }

    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return new Integer(rowIndex * columnIndex);
    }

}
