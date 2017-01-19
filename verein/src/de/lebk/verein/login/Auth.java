package de.lebk.verein.login;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.Role;

import java.util.Objects;

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

    //TODO username aus Memberliste in Club auslesen, falls es ihn gibt.
    public Auth login(Club club, String username, String password) throws UserNotFoundException {

        for (Member clubMember : club.getMembers()) {
            if (clubMember.getUsername().equalsIgnoreCase(username)) {
              user = clubMember;
            } else {
                throw new UserNotFoundException();
            }
        }
        
           if (!Objects.equals(user.getPassword(), password)) {
                    throw new WrongPasswordException();
                }

        this.currentUser = user;
        this.role = Role.valueOf(user.getClass().getSimpleName().toUpperCase());

        return this;
    }

    public Role getRole() {
        return role;
    }

    public Member getCurrentUser() {
        return currentUser;
    }

}
