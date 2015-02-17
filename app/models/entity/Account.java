package models.entity;

import javax.persistence.*;

import models.service.Examination.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.*;
import play.libs.F.Option;

/**
 * アカウントクラス です。
 * Id = 0 の場合はゲストユーザとします。
 * @author Hiroyuki
 *
 */
@Entity
public class Account extends Model {

	public static long GuestId = 9999;

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
	
	/**
	 * ゲストユーザかどうかを判定します。
	 * 
	 * @return ゲストユーザの場合 true を返します。
	 */
	public boolean isGuest() {
		return (this.id == GuestId);
	}
	
	/**
	 * 管理者かどうかを判定します。
	 * 
	 * @return 管理者の場合 true を返します。
	 */
	public boolean isRoot() {
		return (this.mail.equals("mae0003@gmail.com") || this.mail.equals("atsushisuzuki.mail@gmail.com"));
	}

	@Override
	public String toString() {
		return ("[id:" + id + ", name:" + name + ", mail:" + mail + ", password" + password + "]");
	}

}
