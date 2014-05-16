package models;
 
import play.mvc.Http.Context;
import play.mvc.*;
 
/**
 * 認証クラスです。
 *
 */
public class Secured extends Security.Authenticator {
 
	/**
	 * 現在ログインしているユーザーのユーザー名 (メールアドレス) を取得します。
	 * ログインしていない場合 null を返します。
	 * @return メールアドレス
	 */
	@Override
	public String getUsername(Context context) {
		return context.session().get("mail");
	}
 
	/**
	 * 未認証でアクセスされた場合のアクションです。
	 */
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