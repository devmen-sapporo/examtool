package models.entity;

import javax.persistence.*;

import play.db.ebean.*;

@Entity
public class User extends Model {
	
	@Id
	public Long id;
	public String name;
	public String mail;
	public String password;
	
	public static Finder<Long, User> find =
			new Finder<Long, User>(Long.class, User.class);
	
	public String toString() {
		return ("[id:" + id + ", name:" + name + ", mail:" + mail + ", password" + password + "]");
	}

}