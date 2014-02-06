package models;
 
import play.mvc.Http.Context;
import play.mvc.*;
 
public class Secured extends Security.Authenticator {
 
	@Override
	public String getUsername(Context context) {
		return context.session().get("mail");
		
	}
 
	@Override
	public Result onUnauthorized(Context context) {
		String returnUrl = context.request().uri();
		if (returnUrl == null) {
			returnUrl = "/";
		}
		context.session().put("returnUrl", returnUrl);
		return redirect(controllers.routes.Application.login());
	}
}