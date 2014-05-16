package models;
 
import java.security.*;

import models.entity.*;
import play.data.*;
import play.data.validation.*;
import play.db.ebean.*;
 
public class Login {
	
	@Constraints.Required
	private String mail;
	
	@Constraints.Required
	private String password;
	
	public String getMail() {
		return this.mail;
	}
	
	public void setMail(String value) {
		this.mail = value;
	}
 
	public String getPassword() {
		return this.mail;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String validate() throws NoSuchAlgorithmException {
		if (authenticate(this.mail, this.password) == null) {
			return "Invalid user or password";
		}
		return null;
	}
	
	/**
	 * 認証を行います。成功時はアカウント情報を返します。
	 * @param mail
	 * @param password
	 * @return アカウント情報
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public static Account authenticate(String mail, String password) throws java.security.NoSuchAlgorithmException {
		Model.Finder<Long, Account> finder = new Model.Finder<Long, Account>(Long.class, Account.class);
		String hashedPassword = "";
		if (password != null) {
			hashedPassword = sha512(password);
		}
		
		return finder.where()
				.eq("mail", mail)
				.eq("password", hashedPassword)
				.findUnique();
	}
	
	public static String sha512(String message) throws java.security.NoSuchAlgorithmException {
		java.security.MessageDigest messageDigest =java.security.MessageDigest.getInstance("SHA-512");
		StringBuilder stringBuilder = new StringBuilder();
		messageDigest.update(message.getBytes());
		byte[] messageDigestBytes = messageDigest.digest();
		for (byte messageByte : messageDigestBytes) {
			String hex = String.format("%02x", messageByte);
			stringBuilder.append(hex);
		}
		return stringBuilder.toString();
	}
 
}