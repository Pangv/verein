package de.lebk.verein.storage;


import javax.swing.*;
import java.awt.*;

public class LeaseListCellRenderer extends JLabel implements ListCellRenderer {


    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // cast value to Lease
        Lease lease = (Lease) value;
        // set display text for selected value
        setText(lease.getAmount() + " €, fällig am: " + lease.getDueDate().toString());
        if (isSelected) {
            this.setForeground(UIManager.getColor("List.selectionForeground"));
            this.setBackground(UIManager.getColor("List.selectionBackground"));
        } else {
            this.setBackground(UIManager.getColor("List.background"));
        }


        return this;
    }
}
