package controllers;

import java.util.*;

import models.entity.*;
import play.*;
import play.mvc.*;
import views.html.*;

public class Examination extends Controller {

	List<String> optionSings = new ArrayList<>();

	/**
	 * -> メニュー -> 試験画面
	 * @param examCategory
	 * @return
	 */
	public static Result startExam(String category, Integer year) {

    	List<Question> questions = Question.finder.where().eq("year", year).findList();

    	QuestionSheet questionSheet = new QuestionSheet(questions);

    	AnswerSheet answerSheet = createAnswerSheets(questionSheet);

    	Logger.info("AnswerSheet.Id => " + answerSheet.id);
		Logger.info("AnswerSheet.Count => " + answerSheet.getQuestionCount());
		Logger.info("AnswerSheet.Question => " + answerSheet.answerColumns.get(1).question);
    	answerSheet.save();

		AnswerSheet selectedAnswerSheet = new AnswerSheet(answerSheet.id).unique().get();
		Logger.info("selectedAnswerSheet.Id => " + selectedAnswerSheet.id);

        return ok(answercolumn.render(0, answerSheet, questionSheet.signs));
    }
	
	/**
	 * [*] -> 解答欄画面
	 * @param index
	 * @param answerSheetId
	 * @return
	 */
	public static Result changeAnswerColumn(Integer index, Long answerSheetId){
		
		AnswerSheet answerSheet = new AnswerSheet(answerSheetId).unique().get();
		Logger.info("AnswerSheet.Id => " + answerSheet.id);
		Logger.info("AnswerSheet.Count => " + answerSheet.answerColumns);
		Logger.info("AnswerSheet.Question => " + answerSheet.answerColumns.get(1).question);
		
		return ok(answercolumn.render(index, answerSheet, QuestionSheet.signs));
	}

	/**
	 * 
	 * @return
	 */
	public static Result answerAndChange() {

		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		Logger.info("受けたインデックスは => " + form.get("currentIndex")[0]);
		Logger.info("次のインデックスは => " + form.get("nextIndex")[0]);
		
		int currentIndex = Integer.parseInt(form.get("currentIndex")[0]);
		int nextIndex = Integer.parseInt(form.get("nextIndex")[0]);

		long id = Long.parseLong(form.get("answerSheetId")[0]);
		AnswerSheet answerSheet = new AnswerSheet(id).unique().get();
		
		if ( form.get("optionItemId") != null ) {
			Logger.info("受けた optionItemId は => " + form.get("optionItemId")[0]);
			
			long selectedOptionItemId = Long.parseLong(form.get("optionItemId")[0]);
			// 解答を記録する
			answerSheet.answerColumns.get(currentIndex).selectedOptionItem = new OptionItem(selectedOptionItemId).unique().get();
			answerSheet.update();
		}

		return ok(answercolumn.render(nextIndex, answerSheet, QuestionSheet.signs));
	}
	
	private static AnswerSheet createAnswerSheets(QuestionSheet questionSheet) {
    	String mail = ctx().session().get("mail");
		Account account = Account.find.where().eq("mail", mail).findList().get(0);
		return new AnswerSheet(account, questionSheet);
	}

	public static Result showAnswerSheet(Long answerSheetId) {

		AnswerSheet answerSheet = new AnswerSheet(answerSheetId).unique().get();
        return ok(answersheet.render(0, answerSheet));
	}

	public static Result finishExam(Long answerSheetId) {
		AnswerSheet answerSheet = new AnswerSheet(answerSheetId).unique().get();
		answerSheet.mark();
		answerSheet.update();
        return ok(examresult.render(0, answerSheet));
	}
}
