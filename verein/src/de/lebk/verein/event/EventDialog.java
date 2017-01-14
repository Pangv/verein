/*
 * The MIT License
 *
 * Copyright 2016 sopaetzel.
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

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author sopaetzel
 */
public class EventDialog extends JDialog {

    private String eventType;
    private Club club;
    private Member member;

    JLabel jLblTitle;
    JLabel jLblDateTime;

    JTextField jTFTitle;

    JComboBox<Integer> jCombYear;
    JComboBox<Integer> jCombMonth;
    JComboBox<Integer> jCombDay;
    JComboBox<Integer> jCombHour;
    JComboBox<Integer> jCombMinute;

    JButton jBtnAbort;
    JButton jBtnCreate;

    public EventDialog() {
    }

    public EventDialog(Frame owner, String title, String eventType, Club club, Member member) {
        super(owner, title, true);
        this.eventType = eventType;
        this.club = club;
        this.member = member;
        this.createDialog();
        
    }

    private void createDialog() {
        GridBagConstraints grid = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        jTFTitle = new JTextField();

        jLblTitle = new JLabel("Titel");
        jLblDateTime = new JLabel("Datum / Uhrzeit");

        jCombYear = new JComboBox<>(getYearModel());
        jCombYear.setSelectedIndex(0);
        jCombMonth = new JComboBox<>(getMonthModel());
        jCombMonth.setSelectedIndex(0);
        jCombDay = new JComboBox<>(getDayModel());
        jCombDay.setSelectedIndex(0);

        jCombHour = new JComboBox<>(getHourModel());
        jCombMinute = new JComboBox<>(getMinuteModel());

        jBtnAbort = new JButton("Abbruch");
        jBtnCreate = new JButton("Erstellen");

        grid.anchor = GridBagConstraints.FIRST_LINE_START;
        grid.insets = new Insets(2, 2, 2, 2);

        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.weightx = 1;
        grid.weighty = 1;
        grid.gridheight = 1;
        grid.gridwidth = 5;
        grid.gridx = 0;
        grid.gridy = 0;
        this.add(jLblTitle, grid);

        grid.gridheight = 1;
        grid.gridwidth = 5;
        grid.gridx = 0;
        grid.gridy = 1;
        this.add(jTFTitle, grid);

        grid.gridheight = 1;
        grid.gridwidth = 5;
        grid.gridx = 0;
        grid.gridy = 2;
        this.add(jLblDateTime, grid);

        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 0;
        grid.gridy = 3;
        this.add(jCombDay, grid);

        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 1;
        grid.gridy = 3;
        this.add(jCombMonth, grid);

        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 2;
        grid.gridy = 3;
        this.add(jCombYear, grid);

        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 3;
        grid.gridy = 3;
        this.add(jCombHour, grid);

        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 4;
        grid.gridy = 3;
        this.add(jCombMinute, grid);

        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 3;
        grid.gridy = 5;
        this.add(jBtnAbort, grid);

        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.gridx = 4;
        grid.gridy = 5;
        this.add(jBtnCreate, grid);

        defineActions();

        this.pack();
        this.setVisible(true);

    }

    private Integer[] getYearModel() {
        Integer currentYear = new GregorianCalendar().get(Calendar.YEAR);
        Integer[] model = new Integer[25];
        for (int i = 0; i < model.length; i++) {
            model[i] = currentYear++;
        }
        return model;
    }

    private Integer[] getMonthModel() {
        int monthOfYear = 1;
        Integer[] model = new Integer[12];
        for (int i = 0; i < model.length; i++) {
            model[i] = monthOfYear++;
        }
        return model;
    }

    private Integer[] getDayModel() {
        int dayOfMonth = 1;
        Integer[] model = new Integer[31];
        for (int i = 0; i < model.length; i++) {
            model[i] = dayOfMonth++;
        }
        return model;
    }

    private Integer[] getHourModel() {
        int hourOfDay = 1;
        Integer[] model = new Integer[23];
        for (int i = 0; i < model.length; i++) {
            model[i] = hourOfDay++;
        }
        return model;
    }

    private Integer[] getMinuteModel() {
        int minuteOfHour = -15;
        Integer[] model = new Integer[5];
        for (int i = 0; i < model.length; i++) {
            if (minuteOfHour + 15 == 60) {
                model[i] = 59;
            } else {
                model[i] = minuteOfHour += 15;
            }
        }
        return model;
    }

    private void defineActions() {

        jBtnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event creation executed");
                club.addEvent(new Event(eventType, jTFTitle.getText(), member, new GregorianCalendar((int) jCombYear.getSelectedItem(), (int) jCombMonth.getSelectedItem() - 1,
                        (int) jCombDay.getSelectedItem(), (int) jCombHour.getSelectedItem(), (int) jCombMinute.getSelectedItem())));

                JOptionPane.showMessageDialog(null, "Veranstaltung hinzugefügt");
                dispose();
            }
        });

        jBtnAbort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}
