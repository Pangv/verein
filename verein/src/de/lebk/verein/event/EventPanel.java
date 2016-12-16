/*
 * The MIT License
 *
 * Copyright 2016 Sven-Oliver Pätzel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.lebk.verein.event;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Sven-Oliver Pätzel
 */
public class EventPanel extends JPanel{
    
    private JTable eventTable;
    private TableModel defaultModel;
    
    private JButton jBtnJoin;
    private JButton jBtnLeave;
    private JButton jBtnCreate;
    private JButton jBtnInfo;

    public EventPanel() {
        this.createComponent();
    }

    private void createTableModel(){
        String[] tableColumns = {"Titel", "Datum"};
        defaultModel = new DefaultTableModel(tableColumns, 0);
    }
    
    private void createComponent(){
        this.setLayout(new GridBagLayout());
        
        
        
        eventTable = new JTable();
        eventTable.setModel(defaultModel);
        
        jBtnJoin = new JButton();
        jBtnLeave = new JButton();
        jBtnCreate = new JButton();
        jBtnInfo = new JButton();
        
    }
    
    
    
    
}
