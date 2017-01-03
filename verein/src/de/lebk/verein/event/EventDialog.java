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

import java.awt.Frame;
import javax.swing.JDialog;

/**
 *
 * @author sopaetzel
 */
public class EventDialog extends JDialog {

    public EventDialog() {
    }

    public EventDialog(Frame owner, String title, EventTypes type) {
        super(owner, title, true);
        createDialog(type);
    }

    private void createDialog(EventTypes type) {

        switch (type) {

            case GENERIC:
                createGenericDialog();
                break;

            case LAPIDATION:
                createLapidationDialog();
                break;

            case CHILDTOURNAMENT:
                createChildTournamentDialog();
                break;
            default:
                System.err.println("Event choice errornous?");
                break;

        }
        
        this.pack();
        this.setVisible(true);

    }

    private void createGenericDialog() {
        this.setTitle("Allgemeine" + this.getTitle());
    }

    private void createLapidationDialog() {
        this.setTitle("Steinigungs-" + this.getTitle());
    }

    private void createChildTournamentDialog() {
        this.setTitle("Kindertuniers-" + this.getTitle());
    }

}
