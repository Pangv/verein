package de.lebk.verein.login;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.Role;

/**
 * @author raddatz
 */
public class Auth {

    private static Auth ourInstance = new Auth();
    private Role role;
    private Club club;
    private Member user = null;
    private Member currentUser = null;

    private Auth() {
    }

    public static Auth getInstance() {
        return ourInstance;
    }

    public void logout() {
        this.currentUser = null;
    }

    public boolean login(String username, String password)
            throws UserNotFoundException, WrongPasswordException {
        Member possibleUser = club.getUser(username);
        if (possibleUser.getPassword().equals(password)) {
            this.currentUser = possibleUser;
            this.role = Role.valueOf(currentUser.getClass().getSimpleName().toUpperCase());
            return true;
        } else {
            throw new WrongPasswordException();
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
