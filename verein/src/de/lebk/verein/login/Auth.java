package de.lebk.verein.login;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.Officer;
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
    private Member possibleUser = null;

    private static Auth ourInstance = new Auth();

    public static Auth getInstance() {
        return ourInstance;
    }

	private Auth() {
	}

    //TODO username aus Memberliste in Club auslesen, falls es ihn gibt.
    public boolean login(Club club, String username, String password) throws UserNotFoundException, WrongPasswordException {
       if (userExists(club, username)){
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
	public void logout() {
		this.currentUser = null;
	}

	public Role getRole() {
		return role;
	}

    public Member getCurrentUser() {
        return currentUser;
    }
    
    
    private boolean userExists(Club club, String username){
        boolean userExists = false;
        
        for (Member member : club.getMembers()) {
            if (member.getUsername().equals(username)){
               this.possibleUser = member;
               return true;
            }
        }  
        for (Officer officer : club.getOfficers()) {
            if (officer.getUsername().equals(username)){
               this.possibleUser = officer;
               return true;
            }
        }             
        return userExists;
    }
}
