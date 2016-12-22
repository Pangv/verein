/*
 * The MIT License
 *
 * Copyright 2016 sopaetzel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.lebk.verein.member;

import de.lebk.verein.lease.Lease;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 *
 * @author raddatz
 * @date 15.12.2016
 */
public class Member {

  private String firstName;
  private String lastName;
  private String password;
  private String username;
  private char sex;
  private GregorianCalendar entered;
  private List<Lease> leases;

	public Member(String firstName, String lastName, String password, String username, char sex,
		GregorianCalendar entered) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.username = generateUsername(firstName, lastName);
    this.sex = sex;
    this.entered = entered;
  }

	public Member(String password, String username, char sex) {
		this.password = password;
		this.username = username;
		this.sex = sex;
	}

	public String getFullName() {
		return firstName + " " + lastName;
  }

  public String getDateTimeEntered() {
    return entered.get(Calendar.DATE) + "." + entered.get(Calendar.MONTH) + "."
        + entered.get(Calendar.YEAR);
  }

  private String generateUsername(String fName, String lName) {
      /* TODO make usernames unique prevent same username
        do this by checking the "data storage"
      */
      Random uniqueNumber = new Random();
    return fName.toLowerCase().substring(0, 2) + (lName.toLowerCase().contains(" ") ? lName.toLowerCase().substring(0, lName.toLowerCase().indexOf(" ")) : lName.toLowerCase()) + uniqueNumber.nextInt(Integer.MAX_VALUE);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

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

  public List<Lease> getLeases() {
    return leases;
  }

  public void setLeases(List<Lease> leases) {
    this.leases = leases;
  }
}
