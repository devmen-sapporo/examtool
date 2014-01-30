package controllers;

import static play.data.Form.*;
import models.*;
import models.entity.*;
import play.data.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	public static class SignInData {
		@Email
		@Required(message = "メールアドレスを入力してください。")
		public String mail;
		
		@Required(message = "パスワードを入力してください。")
		public String password;
	}
	
	public static class SampleData{
		public String message;
		public String name;
	}
	
    public static Result index() {
    	
        return ok(index.render(
        	null,
        	new Form(SampleData.class)));
    }
    
    public static Result signIn() {
    	Form<SignInData> formSignInData = form(SignInData.class).bindFromRequest();
    	if (formSignInData.hasErrors())
    	{
    		return badRequest(index.render("サインインに失敗しました。", form(SampleData.class)));
    	}
    	
    	SignInData data = formSignInData.get();
    	String mail = data.mail;
    	String password = data.password;
    	if (User.authenticate(mail, password) == null)
    	{
    		return badRequest(index.render("メールアドレス、または、パスワードが正しくありません。", form(SampleData.class)));
    	}
    	
    	return ok(menu.render(mail));
    }

    public static Result add() {
    	Form<Message> formMessage = new Form<>(Message.class);
    	return ok(add.render("add", formMessage));    
    }

    public static Result create() {
    	Form<Message> formMessage = new Form<>(Message.class).bindFromRequest();
    	if (formMessage.hasErrors()) {
    		return badRequest(add.render("Error", formMessage));
    	}
    	
    	Message message = formMessage.get();
    	message.save();
    	
    	return redirect("/");
    }

    /*
    public static Result setitem() {
    	Form<Message> formMessage = new Form<>(Message.class);
    	return ok(item.render("item NO を入力。", formMessage));
    }

    public static Result edit() {
    	Form<Message> formMessage = new Form<>(Message.class);
        return ok(index.render("Jumping boy.", new Form(SampleData.class)));
    }
    */
}
