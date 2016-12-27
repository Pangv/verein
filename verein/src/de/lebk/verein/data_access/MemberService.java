package de.lebk.verein.data_access;

import de.lebk.verein.member.Member;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 * Created by marcel on 26.12.16.
 */
public class MemberService extends FileService<Member> {

	public MemberService() {
		super(Paths.get(WriteabelInterface.PATH + "/members.txt"));
		this.read();
	}

	public List<Member> read() {
		try {
			super.fetch();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String rawMember : this.rawEntities) {
			this.entities.add(processToEntity(rawMember));
		}

		return this.entities;
	}

	public Member getMember(String username) {
		for (Member member : this.entities) {
			if (Objects.equals(member.getUsername(), username)) {
				return member;
			}
		}
		return null;
	}

	private Member processToEntity(String line) {
		String[] entries = line.split(",");
		String date = entries[5];
		GregorianCalendar entered = new GregorianCalendar();
		entered.setTimeInMillis(Long.parseLong(date));
		return new Member(entries[0], entries[1], entries[2], entries[3],
			entries[4].toCharArray()[0], entered);
	}

}
