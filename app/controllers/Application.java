package controllers;

import static play.data.Form.*;
import models.*;
import models.entity.*;
import play.data.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.filters.csrf.*;
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
	
	@play.mvc.Security.Authenticated(models.Secured.class)
    public static Result index() {

    	return ok(menu.render(""));
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
    		return badRequest(index.render("メールアドレス、またはパスワードに誤りがあります。", form(SampleData.class)));
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
    
    @AddCSRFToken
    public static Result login() {
    	return ok(login.render("", form(Login.class)));
    }
    
    @RequireCSRFCheck
    public static Result authenticate() {
    	Form<Login> loginForm = form(Login.class).bindFromRequest();
    	if (loginForm.hasErrors()) {
    		return badRequest(login.render("サインインに失敗しました。", loginForm));
    	} else {
    		session("mail", loginForm.get().getMail());
    		String returnUrl = ctx().session().get("returnUrl");
    		if (returnUrl == null || returnUrl.equals("") || returnUrl.equals(routes.Application.login().absoluteURL(request()))) {
    			returnUrl = routes.Application.index().url();
    		}
    		
    		return redirect(returnUrl);
    	}
    }
 
    @play.mvc.Security.Authenticated(models.Secured.class)
    public static Result logout() {
    	session().clear();
    	return redirect(routes.Application.login());
    }
}
