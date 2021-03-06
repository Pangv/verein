package de.lebk.verein.storage;

import de.lebk.verein.club.Club;
import de.lebk.verein.login.Auth;
import de.lebk.verein.utilities.Warning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * @author sopaetzel
 */
public class StorageManager extends JPanel {

    private final Font BIG_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 100);

    private Club club;

    private JLabel lblStoneAmount;
    private JLabel lblStones;

    private JTextField txtAmount;
    private JList<Lease> myLeases;


    private JButton btnLease;
    private JButton btnReturn;


    public StorageManager(Club club) {
        this.club = club;
        this.setLayout(new GridLayout(2, 2));
        createComponent();
    }

    private void createComponent() {
        this.add(initTopLeft());
        this.add(initTopRight());
        this.add(initBottomLeft());
        this.add(initBottomRight());

        this.initActionsListeners();
        this.setVisible(true);
    }


    private JPanel initTopLeft() {
        JPanel topLeft = new JPanel();
        topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.Y_AXIS));

        lblStoneAmount = new JLabel(club.getStorage().getAmount() + "");
        lblStones = new JLabel("Steine");

        lblStoneAmount.setFont(BIG_FONT);
        lblStones.setFont(BIG_FONT);

        topLeft.add(lblStoneAmount, BorderLayout.NORTH);
        topLeft.add(lblStones, BorderLayout.SOUTH);

        return topLeft;
    }

    private JPanel initBottomLeft() {
        JPanel bottomLeft = new JPanel();
        bottomLeft.setLayout(new BoxLayout(bottomLeft, BoxLayout.Y_AXIS));
        JButton btnRefresh = new JButton("Liste aktualisieren");
        myLeases = new JList<>();
        JScrollPane myLeasesScrollPane = new JScrollPane(myLeases);
        JLabel lblMyList = new JLabel("Meine Liste");


        bottomLeft.add(lblMyList);
        bottomLeft.add(myLeasesScrollPane);
        bottomLeft.add(btnRefresh);


        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLeasesToMyList();
            }
        });


        return bottomLeft;
    }

    private JPanel initTopRight() {
        JPanel topRight = new JPanel();
        topRight.setLayout(new GridLayout(1, 2));


        txtAmount = new JTextField("0");
        btnLease = new JButton("Steine Ausleihen");

        JScrollPane scrlTxtAmount = new JScrollPane();
        scrlTxtAmount.setViewportView(txtAmount);

        txtAmount.setHorizontalAlignment(SwingConstants.CENTER);
        txtAmount.setFont(BIG_FONT);

        topRight.add(scrlTxtAmount);
        topRight.add(btnLease);

        return topRight;
    }

    private JPanel initBottomRight() {
        JPanel bottomRight = new JPanel();
        btnReturn = new JButton("Steine zurückgeben");
        btnReturn.setVerticalAlignment(SwingConstants.BOTTOM);
        bottomRight.add(btnReturn);
        return bottomRight;
    }


    private void initActionsListeners() {

        btnLease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    club.getStorage().addLease(Auth.getInstance().getCurrentUser(), Integer.parseInt(txtAmount.getText()), new GregorianCalendar());
                    lblStoneAmount.setText(club.getStorage().getAmount() + "");
                    if (lblStoneAmount.getText().equals("1")) {
                        lblStones.setText("Stein");
                    } else {
                        lblStones.setText("Steine");
                    }
                    addLeasesToMyList();
                } catch (NumberFormatException ex) {
                    Warning.displayWarning(ex.getMessage(), "Bitte nur ganzahlige Zahlen verwenden.");
                } catch (OutOfStonesException ex) {
                    Warning.displayWarning("kleiner Null",
                            "Es können nicht mehr Steine ausgeliehen werden als vorhanden sind.");
                    throw new OutOfStonesException();
                }
            }
        });


        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    club.getStorage().removeLease(myLeases.getSelectedValue());
                    club.getStorage().setAmount(club.getStorage().getAmount() + myLeases.getSelectedValue().getAmount());
                    lblStoneAmount.setText(club.getStorage().getAmount() + "");

                    addLeasesToMyList();

                } catch (NullPointerException ex) {
                    Warning.displayWarning(ex.getMessage(), "Kein Element ausgewählt");
                }
            }
        });


        txtAmount.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtAmount.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

    }

    private void addLeasesToMyList() {
        DefaultListModel<Lease> model = new DefaultListModel<>();
        myLeases.setModel(model);

        Iterator<Lease> leaseIterator = club.getStorage().getLeasesForMember(Auth.getInstance().getCurrentUser()).iterator();
        Lease lease;
        while (leaseIterator.hasNext()) {
            lease = leaseIterator.next();
            model.addElement(lease);
        }

    }


}
