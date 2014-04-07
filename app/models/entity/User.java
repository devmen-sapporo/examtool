package models.entity;

import javax.persistence.*;

import models.service.Examination.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.*;
import play.libs.F.Option;

@Entity
public class User extends Model {

	/**
	 * コンストラクタ
	 * @param id
	 */
	public User(long id) {
		this.id = id;
	}

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

    public static User authenticate(String mail, String password) {
        return find.where()
            .eq("mail", mail)
            .eq("password", password)
            .findUnique();
    }
    
	public Option<User> unique() {
		return new UserModelService().findById(id);
	}

	@Override
	public String toString() {
		return ("[id:" + id + ", name:" + name + ", mail:" + mail + ", password" + password + "]");
	}

}
