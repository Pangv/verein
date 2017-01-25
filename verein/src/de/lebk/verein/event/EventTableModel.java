package de.lebk.verein.event;

import de.lebk.verein.club.Club;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

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
        return 20;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return new Integer(rowIndex * columnIndex);
    }

}
