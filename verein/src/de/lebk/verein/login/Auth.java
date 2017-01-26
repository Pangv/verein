package de.lebk.verein.login;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.Officer;
import de.lebk.verein.member.Role;

/**
 *
 * @author raddatz
 */
public class Auth {

    private Role role;
    private Club club;
    private Member user = null;
    private Member currentUser = null;

    private static Auth ourInstance = new Auth();

    public static Auth getInstance() {
        return ourInstance;
    }

    private Auth() {
    }

    public void logout() {
        this.currentUser = null;
    }

	public boolean login(String username, String password)
		throws UserNotFoundException, WrongPasswordException {
		Member possibleUser = club.getUser(username);
		if (possibleUser != null) {
			if (possibleUser.getPassword().equals(password)) {
               this.currentUser = possibleUser;
               this.role = Role.valueOf(currentUser.getClass().getSimpleName().toUpperCase());
               return true;
           }else {
               throw new WrongPasswordException();
           }
       }else {
           throw new UserNotFoundException();
       }
    }

    public Role getRole() {
        return role;
    }

    public Member getCurrentUser() {
        return currentUser;
    }

	public void setClub(Club club) {
		this.club = club;
	}
}
