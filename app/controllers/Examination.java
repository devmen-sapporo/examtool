package controllers;

import java.util.*;

import models.entity.*;
import play.mvc.*;
import views.html.*;

public class Examination extends Controller {

	List<String> optionSings = new ArrayList<>();

	/**
	 * -> メニュー -> 試験画面
	 * 
	 * @param examCategory
	 * @return
	 */
	public static Result startExam(String category, Integer year) {
		List<Question> questions = Question.finder.where().eq("year", year).findList();

		QuestionSheet questionSheet = new QuestionSheet(questions);
		AnswerSheet answerSheet = createAnswerSheets(questionSheet);
		answerSheet.save();

		AnswerSheet selectedAnswerSheet = new AnswerSheet(answerSheet.id).unique().get();

		return ok(answercolumn.render(0, answerSheet, questionSheet.signs));
	}

	/**
	 * [*] -> 解答欄画面
	 * 
	 * @param index
	 * @param answerSheetId
	 * @return
	 */
	public static Result changeAnswerColumn(Integer index, Long answerSheetId) {
		AnswerSheet answerSheet = new AnswerSheet(answerSheetId).unique().get();
		return ok(answercolumn.render(index, answerSheet, QuestionSheet.signs));
	}

	/**
	 * 解答し、問題を切り替えます。
	 * 
	 * @return
	 */
	public static Result answerAndChange() {
		Map<String, String[]> form = request().body().asFormUrlEncoded();

		int currentIndex = Integer.parseInt(form.get("currentIndex")[0]);
		int nextIndex = Integer.parseInt(form.get("nextIndex")[0]);

		long id = Long.parseLong(form.get("answerSheetId")[0]);
		AnswerSheet answerSheet = new AnswerSheet(id).unique().get();

		if (form.get("optionItemId") != null) {
			long selectedOptionItemId = Long.parseLong(form.get("optionItemId")[0]);
			// 解答を記録する
			answerSheet.answerColumns.get(currentIndex).selectedOptionItem = new OptionItem(
					selectedOptionItemId).unique().get();
			answerSheet.update();
		}

		return ok(answercolumn.render(nextIndex, answerSheet,
				QuestionSheet.signs));
	}

	private static AnswerSheet createAnswerSheets(QuestionSheet questionSheet) {
		String mail = ctx().session().get("mail");
		
		Account account;
		if (mail != null) {
			account = Account.find.where().eq("mail", mail).findList().get(0);
		} else {
			account = Account.find.where().eq("id", Account.GuestId).findList().get(0);
		}
		
		return new AnswerSheet(account, questionSheet);
	}

	/**
	 * 解答欄画面を表示します。
	 * 
	 * @param answerSheetId
	 * @return
	 */
	public static Result showAnswerSheet(Long answerSheetId) {
		AnswerSheet answerSheet = new AnswerSheet(answerSheetId).unique().get();
		return ok(answersheet.render(0, answerSheet));
	}

	/**
	 * 試験を終了します。 サインイン済の場合は解答履歴を保存します。
	 * 
	 * @param answerSheetId
	 * @return
	 */
	public static Result finishExam(Long answerSheetId) {
		AnswerSheet answerSheet = new AnswerSheet(answerSheetId).unique().get();
		answerSheet.mark();
		answerSheet.update();

		String mail = ctx().session().get("mail");

		if (mail != null) { 
			Account account = Account.find.where().eq("mail", mail).findList().get(0);
	
			for (AnswerColumn column : answerSheet.answerColumns) {
				new AnswerHistory(account, column).save();
			}
		}

		return ok(examresult.render(0, answerSheet));
	}
}
