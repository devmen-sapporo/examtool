package controllers;

import java.util.*;

import models.entity.*;
import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;

public class Examination extends Controller {

	List<String> optionSings = new ArrayList<>();

	public static Result startExam() {
		Form<ExamCategory> formExamCategory = new Form<>(ExamCategory.class).bindFromRequest();
    	ExamCategory examCategory = formExamCategory.get();
    	
    	List<Question> questions = Question.finder.where().findList();
 
    	QuestionSheet questionSheet = new QuestionSheet(questions);
    	Logger.info("sss" + questionSheet.getQuestions().size());

		// TODO: どこからユーザ情報を取得するのか確認すること
    	AnswerSheet answerSheet = createAnswerSheets(questionSheet);
    	
        return ok(answercolumn.render(0, answerSheet, questionSheet.signs));
    }
	
	public static Result changeAnswerColumn(){
		Map<String,String[]> form = request().body().asFormUrlEncoded();
		String param = form.get("selectedOptionIndex")[0];
		AnswerColumn answerColumn = null;
		if (param != null) {
			int selectedOptionIndex = Integer.parseInt(param);
			answerColumn = new AnswerColumn(1L).unique().get();
		}
		
        return ok(answercolumn.render(0, null, QuestionSheet.signs));
	}

	private static AnswerSheet createAnswerSheets(QuestionSheet questionSheet) {
		User user = User.find.byId(1L);
		return new AnswerSheet(user, questionSheet);
	}

	public static Result showAnswerSheet() {	
        return ok(answersheet.render(null));
	}

	public static Result finishExam() {	
        return ok(examresult.render(1));
	}
}