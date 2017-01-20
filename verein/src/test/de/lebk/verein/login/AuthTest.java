package de.lebk.verein.login;

import de.lebk.verein.member.Member;
import de.lebk.verein.member.Officer;
import de.lebk.verein.member.Role;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by marcel on 13.01.17.
 */
public class AuthTest {
	private Auth auth = Auth.getInstance();
	private Member member = new Member("12345", "max_mustermann", 'm');
	private Officer officer = new Officer("12345", "officer", 'm');


	@Test public void memberLogin() throws Exception {
		auth.login(member, "12345");

		Assert.assertEquals(Role.MEMBER, auth.getRole());
	}

	@Test(expected = WrongPasswordException.class) public void wrongLogIn() throws Exception {
		auth.login(officer, "123");
	}


	@Test public void logout() throws Exception {
		auth.login(officer, "12345");
		Assert.assertEquals(Role.OFFICER, auth.getRole());

		auth.logout();
		Assert.assertEquals(null, auth.getCurrentUser());
	}

}
