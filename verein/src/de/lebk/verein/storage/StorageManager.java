package de.lebk.verein.storage;

import de.lebk.verein.club.Club;
import de.lebk.verein.lease.Lease;
import de.lebk.verein.login.Auth;
import de.lebk.verein.utilities.Warning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

/**
 * @author sopaetzel
 */
public class StorageManager extends JPanel {

    private Club club;

    private JPanel topLeft;
    private JPanel bottomLeft;
    private JPanel topRight;
    private JPanel bottomRight;

    private JLabel lblStoneAmount;
    private JLabel lblAmount;
    private JLabel lblStones;
    private JLabel lblMyList;

    private JTextField txtAmount;

    private JButton btnLease;
    private JButton btnReturn;

    private JList<String> myLeases;
    private JScrollPane myLeasesScrollPane;

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
        topLeft = new JPanel();
        lblStoneAmount = new JLabel(club.getStorage().getAmount() + "");
        lblStones = new JLabel("Steine");

        lblStoneAmount.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));

        topLeft.add(lblStoneAmount, BorderLayout.NORTH);
        topLeft.add(lblStones, BorderLayout.SOUTH);

        return topLeft;
    }

    private JPanel initBottomLeft() {
        bottomLeft = new JPanel();
        bottomLeft.setLayout(new BoxLayout(bottomLeft, BoxLayout.Y_AXIS));



        myLeasesScrollPane = new JScrollPane();
        myLeases = new JList<>(this.createArrayFromList());
        lblMyList = new JLabel("Meine Liste");



        myLeasesScrollPane.add(myLeases);
        bottomLeft.add(lblMyList);
        bottomLeft.add(myLeasesScrollPane);



        return bottomLeft;
    }

    private JPanel initTopRight() {
        topRight = new JPanel();

        lblAmount = new JLabel("Anzahl");
        txtAmount = new JTextField();
        btnLease = new JButton("Steine Ausleihen");

        topRight.add(lblAmount);
        topRight.add(txtAmount);
        topRight.add(btnLease);

        return topRight;
    }

    private JPanel initBottomRight() {
        bottomRight = new JPanel();
        btnReturn = new JButton("Steine zurückgeben");

        bottomRight.add(btnReturn);
        return bottomRight;
    }


    private void initActionsListeners() {

        btnLease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    club.getStorage().addLease(Auth.getInstance().getCurrentUser(), Integer.parseInt(txtAmount.getText()), new GregorianCalendar());
                    lblStoneAmount.setText(club.getStorage().getAmount()+"");
                }catch (NumberFormatException ex){
                    Warning.displayWarning(ex.getMessage(), "Bitte nur ganzahlige Zahlen verwenden.");
                }
            }
        });


        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   // club.getStorage().removeLease();
                    club.getStorage().setAmount(club.getStorage().getAmount()-1);

                }catch (NullPointerException ex){
                    Warning.displayWarning(ex.getMessage(), "Kein Element ausgewählt");
                }
            }
        });

    }

    private String[] createArrayFromList(){
        String[] array = new String[100];

        int i = 0;
        for(Lease lease : club.getStorage().getLeasesForMember(Auth.getInstance().getCurrentUser())){
                array[i++] = lease.getMember() + " " + lease.getAmount() + " bis: " + lease.getDueDate();
        }

        return array;
    }

}
