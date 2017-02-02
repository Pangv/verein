package de.lebk.verein.member;

import javax.xml.bind.annotation.XmlElement;
import java.util.GregorianCalendar;

/**
 * @author mraddatz
 */
public class Officer extends Member {

    private GregorianCalendar officerSince;

    public Officer(String firstName, String lastName, String username, String password, char sex,
                   GregorianCalendar entered) {
        super(firstName, lastName, username, password, sex, entered);
    }

    public Officer(String firstName, String lastName, String username, String password, char sex) {
        super(firstName, lastName, username, password, sex);
        this.officerSince = new GregorianCalendar();
    }

    public Officer() {
    }

    @XmlElement
    public GregorianCalendar getOfficerSince() {
        return officerSince;
    }

    public void setOfficerSince(GregorianCalendar officerSince) {
        this.officerSince = officerSince;
    }

}
