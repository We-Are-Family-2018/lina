package com.example.demo.form;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("deprecation")
public class RegisterForm {	
	@NotBlank
	private String userName;
	@NotBlank
	private String password;
	@NotBlank
	private String Telphone;
	@NotBlank
	private String mail;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelphone() {
		return Telphone;
	}

	public void setTelphone(String telphone) {
		Telphone = telphone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
