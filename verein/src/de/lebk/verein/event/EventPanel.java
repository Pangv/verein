/*
 * The MIT License
 *
 * Copyright 2016 Sven-Oliver Pätzel.
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
package de.lebk.verein.event;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Sven-Oliver Pätzel
 */
public class EventPanel extends JPanel {

    private JTable jTblEvents;
    private TableModel defaultModel;
    private JScrollPane scrollPane;

    private JButton jBtnJoin;
    private JButton jBtnLeave;
    private JButton jBtnCreate;
    private JButton jBtnInfo;
    private JComboBox<EventTypes> jCmbEventType;

    public EventPanel() {
        this.createComponent();
    }

    private TableModel createTableModel() {
        String[] tableColumns = {"Titel", "Datum"};
        String[][] tableData = {{"Titel A", "2000-12-12"}, {"Titel A", "2000-12-12"},
        {"Titel A", "2000-12-12"}, {"Titel A", "2000-12-12"}, {"Titel A", "2000-12-12"}};
        return defaultModel = new DefaultTableModel(tableData, tableColumns);
    }

    private void createComponent() {
        GridBagConstraints grid = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        
        jTblEvents = new JTable();
        jTblEvents.setModel(createTableModel());
        scrollPane = new JScrollPane(jTblEvents);
        scrollPane.setPreferredSize(new Dimension(200, 50));

        jBtnJoin = new JButton("Anmelden");
        jBtnLeave = new JButton("Abmelden");        
        jBtnInfo = new JButton("Info");
        
        
        jCmbEventType = new JComboBox<>();
        jCmbEventType.addItem(EventTypes.LAPIDATION);
        jCmbEventType.addItem(EventTypes.CHILDTOURNAMENT);
        jCmbEventType.addItem(EventTypes.OFFICERSELECTION);
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

            }
        });

    }

}
