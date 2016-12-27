package de.lebk.verein.data_access;

import de.lebk.verein.member.Member;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by marcel on 26.12.16.
 */
@FixMethodOrder public class MemberServiceTest {
	private MemberService memberService = new MemberService();

	@Test public void a_save() throws Exception {
		List<Member> memberList = new ArrayList<Member>();
		memberList.add(
			new Member("Max", "Mustermann", "maxi21", "maxMuster", 'm', new GregorianCalendar()));
		memberService.save(memberList);
	}

	@Test public void b_read() throws Exception {
		List<Member> members = memberService.read();
		Assert.assertEquals("maxMuster", members.get(0).getUsername());
	}

	@Test public void c_getMember() throws Exception {
		Member member = memberService.getMember("maxMuster");
		assertEquals("maxMuster", member.getUsername());
	}

	@Test public void d_getNonExistendMember() {
		assertEquals(null, memberService.getMember("notExistend"));
	}

}
