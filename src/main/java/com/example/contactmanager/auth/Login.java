package com.example.contactmanager.auth;

public class Login {
	private String userName, passWord;

	Login(String name, String pass) {
		userName = name;
		passWord = pass;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

}
