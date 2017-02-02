package de.lebk.verein.member;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author mraddatz
 */
@XmlType(propOrder = {"firstName", "lastName", "password", "username", "sex", "entered"})
public class Member {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private char sex;
    private GregorianCalendar entered;

    public Member() {
    }

    public Member(String firstName, String lastName, String username, String password, char sex, GregorianCalendar entered) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.entered = entered;
    }

    public Member(String firstName, String lastName, String username, String password, char sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.sex = sex;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDateTimeEntered() {
        return entered.get(Calendar.DATE) + "." + entered.get(Calendar.MONTH) + 1 + "."
                + entered.get(Calendar.YEAR);
    }

    @XmlElement(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public GregorianCalendar getEntered() {
        return entered;
    }

    public void setEntered(GregorianCalendar entered) {
        this.entered = entered;
    }


}
