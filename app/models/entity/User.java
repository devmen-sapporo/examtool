package models.entity;

import javax.persistence.*;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.*;

@Entity
public class User extends Model {
	
	@Id
	public Long id;

	@Required
	public String name;

	@Email
	public String mail;

	@Required
	public String password;
	
	public static Finder<Long, User> find =
			new Finder<Long, User>(Long.class, User.class);
	
	@Override
	public String toString() {
		return ("[id:" + id + ", name:" + name + ", mail:" + mail + ", password" + password + "]");
	}

}
