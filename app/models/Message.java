package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
public class Message extends Model {
	
	@Id
	public Long id;
	
	@Required
	public String name;
	
	@Required
	public String mail;
	public String message;
	
	@CreatedTimestamp
	public Date postDate;
	
	public static Finder<Long, Message> finder = new Finder<>(Long.class, Message.class);
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append("id:" + id + ", ");
		builder.append("name: " + this.name + ", ");
		builder.append("mail: " + this.mail + ", ");
		builder.append("message: " + this.message + ", ");
		builder.append("date: " + this.postDate + ", ");
		builder.append("]\r\n");
		
		return builder.toString();
	}
}
