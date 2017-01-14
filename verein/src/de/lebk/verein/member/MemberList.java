package de.lebk.verein.member;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sopaetzel
 */
@XmlRootElement
public class MemberList extends ArrayList<Member> {

    public MemberList() {
    }

    // root element name
    //@XmlElementWrapper(name = "memberList")
    // name of entities
    @XmlElement(name = "member")
    public ArrayList<Member> getMemberList() {
        return this;
    }

    public void addMember(Member member) {
        this.add(member);
    }

    @XmlAttribute
    public int getMemberCount() {
        return this.size();
    }

}
