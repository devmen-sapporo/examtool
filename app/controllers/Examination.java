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

        return ok(answercolumn.render(0, answerSheet, questionSheet.signs));
    }
	
	public static Result changeAnswerColumn(Integer index){

		Logger.info("受けたインデックスは => " + index);

		List<Question> questions = Question.finder.where().eq("year", 2014).findList();

    	QuestionSheet questionSheet = new QuestionSheet(questions);
    	AnswerSheet answerSheet = createAnswerSheets(questionSheet);

		return ok(answercolumn.render(index, answerSheet, QuestionSheet.signs));
	}

	private static AnswerSheet createAnswerSheets(QuestionSheet questionSheet) {
    	String mail = ctx().session().get("mail");
		User user = User.find.where().eq("mail", mail).findList().get(0);
		return new AnswerSheet(user, questionSheet);
	}

	public static Result showAnswerSheet() {
    	List<Question> questions = Question.finder.where().eq("year", 2014).findList();

    	QuestionSheet questionSheet = new QuestionSheet(questions);
    	AnswerSheet answerSheet = createAnswerSheets(questionSheet);
        return ok(answersheet.render(0, answerSheet));
	}

	public static Result finishExam() {	
        return ok(examresult.render(1));
	}
}
