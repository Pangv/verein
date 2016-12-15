package de.lebk.verein.member;

import java.util.GregorianCalendar;

/**
 * Created by marcel on 15.12.16.
 */
public class Officier extends Member {
	private GregorianCalendar officerSince;

	public Officier(String firstName, String lastName, String password, String usernam, char sex,
		GregorianCalendar entered) {
		super(firstName, lastName, password, usernam, sex, entered);
	}

	public GregorianCalendar getOfficerSince() {
		return officerSince;
	}

	public void setOfficerSince(GregorianCalendar officerSince) {
		this.officerSince = officerSince;
	}

}
