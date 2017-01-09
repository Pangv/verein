package de.lebk.verein.member;

import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marcel on 15.12.16.
 */
@XmlRootElement(name = "officer")
public class Officer extends Member {

    private GregorianCalendar officerSince;

    public Officer(String firstName, String lastName, String password, String username, char sex,
            GregorianCalendar entered) {
        super(firstName, lastName, password, username, sex, entered);
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
