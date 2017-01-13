/*
 * The MIT License
 *
 * Copyright 2016 sopaetzel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.lebk.verein.login;

import de.lebk.verein.member.Role;
import de.lebk.verein.member.User;

import java.util.Objects;

/**
 *
 * @author raddatz
 */
public class Auth {
	private Role role;
	private User currentUser = null;

	private static Auth ourInstance = new Auth();

	public static Auth getInstance() {
		return ourInstance;
	}

	private Auth() {
	}

	public void logout() {
		this.currentUser = null;
	}

	public Auth login(User user, String password) {
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

	public User getCurrentUser() {
		return currentUser;
	}

}
