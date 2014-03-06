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
	
	public static Result changeAnswerColumn(Integer index, Long answerSheetId){

		Logger.info("受けたインデックスは => " + index);
		Logger.info("受けた ID は => " + answerSheetId);

		AnswerSheet answerSheet = new AnswerSheet(answerSheetId).unique().get();
		Logger.info("AnswerSheet.Id => " + answerSheet.id);
		Logger.info("AnswerSheet.Count => " + answerSheet.answerColumns);
		Logger.info("AnswerSheet.Question => " + answerSheet.answerColumns.get(1).question);

		return ok(answercolumn.render(index, answerSheet, QuestionSheet.signs));
	}

	private static AnswerSheet createAnswerSheets(QuestionSheet questionSheet) {
    	String mail = ctx().session().get("mail");
		User user = User.find.where().eq("mail", mail).findList().get(0);
		return new AnswerSheet(user, questionSheet);
	}

	public static Result showAnswerSheet(Long answerSheetId) {

		AnswerSheet answerSheet = new AnswerSheet(answerSheetId).unique().get();
        return ok(answersheet.render(0, answerSheet));
	}

	public static Result finishExam(Long answerSheetId) {

		AnswerSheet answerSheet = new AnswerSheet(answerSheetId).unique().get();
        return ok(examresult.render(0, answerSheet));
	}
}
