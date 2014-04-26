package models.entity;

import javax.persistence.*;

import models.service.Examination.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.*;
import play.libs.F.Option;

@Entity
public class Account extends Model {

	/**
	 * コンストラクタ
	 * @param id
	 */
	public Account(long id) {
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
	
	public static Finder<Long, Account> find =
			new Finder<Long, Account>(Long.class, Account.class);

    public static Account authenticate(String mail, String password) {
        return find.where()
            .eq("mail", mail)
            .eq("password", password)
            .findUnique();
    }
    
	public Option<Account> unique() {
		return new AccountModelService().findById(id);
	}

	@Override
	public String toString() {
		return ("[id:" + id + ", name:" + name + ", mail:" + mail + ", password" + password + "]");
	}

}
