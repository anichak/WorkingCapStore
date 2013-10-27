package com.cg.domain;

public class Email {
@Override
	public String toString() {
		return userEmail;
	}

private String userEmail;

public Email() {
	super();
	// TODO Auto-generated constructor stub
}

public String getUserEmail() {
	return userEmail;
}

public Email(String userEmail) {
	super();
	this.userEmail = userEmail;
}

public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}
}
