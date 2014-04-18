package controllers;

import static play.data.Form.*;

import java.util.*;

import models.*;
import models.entity.*;

import org.pac4j.core.profile.*;
import org.pac4j.play.java.*;

import play.*;
import play.data.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.filters.csrf.*;
import play.mvc.*;
import views.html.*;

import com.avaje.ebean.*;

public class Application extends JavaController {

	public static class SignInData {
		@Email
		@Required(message = "メールアドレスを入力してください。")
		public String mail;

		@Required(message = "パスワードを入力してください。")
		public String password;
	}

	public static class SampleData {
		public String message;
		public String name;
	}

	/**
	 * -> ログイン画面 -> メニュー画面
	 * 
	 * @return
	 */
	@play.mvc.Security.Authenticated(models.Secured.class)
	public static Result index() {
		List<ExamCategory> categorys = getCategoryList();
		return ok(menu.render("", categorys));
	}

	public static Result signIn() {
		Form<SignInData> formSignInData = form(SignInData.class)
				.bindFromRequest();
		if (formSignInData.hasErrors()) {
			return badRequest(index.render("サインインに失敗しました。",
					form(SampleData.class)));
		}

		SignInData data = formSignInData.get();
		String mail = data.mail;
		String password = data.password;
		if (User.authenticate(mail, password) == null) {
			return badRequest(index.render("メールアドレス、またはパスワードに誤りがあります。",
					form(SampleData.class)));
		}

		List<ExamCategory> categorys = getCategoryList();
		return ok(menu.render(mail, categorys));
	}

	/**
	 * @return
	 */
	private static List<ExamCategory> getCategoryList() {
		List<ExamCategory> examCategorys = new ArrayList<>();

		String sql = " SELECT category_id, year, season_id FROM question GROUP BY category_id, year, season_id";
		List<SqlRow> sqlRows = Ebean.createSqlQuery(sql).findList();

		for (SqlRow row : sqlRows) {
			Logger.info("getCategoryList started");

			long category_id = row.getLong("category_id");
			long season_id = row.getLong("season_id");

			Season season = new Season(season_id).unique().get();
			Category category = new Category(category_id).unique().get();

			Logger.info("[category]" + category.name);
			Logger.info("[season_id]" + season.name);

			examCategorys.add(new ExamCategory(category, row.getInteger("year"), season));
		}
		return examCategorys;
	}

	@AddCSRFToken
	public static Result login() {
		String url = getRedirectAction("FacebookClient", "/result").getLocation();
		return ok(login.render("", url, "", form(Login.class)));
	}

	@RequireCSRFCheck
	public static Result authenticate() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			String url = getRedirectAction("FacebookClient", "/result").getLocation();
			return badRequest(login.render("サインインに失敗しました。", url, "", loginForm));
		} else {
			session("mail", loginForm.get().getMail());
			String returnUrl = ctx().session().get("returnUrl");
			if (returnUrl == null
					|| returnUrl.equals("")
					|| returnUrl.equals(routes.Application.login().absoluteURL(
							request()))) {
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

	public static Result result() {
		CommonProfile profile = getUserProfile();
		String urlPiciture = "https://graph.facebook.com/"
				+ profile.getUsername() + "/picture";
		return ok(login.render("", "", urlPiciture, form(Login.class)));
	}
}
