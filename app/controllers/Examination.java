package controllers;

import models.entity.*;
import play.mvc.*;
import views.html.*;

public class Examination extends Controller {

	public static Result startExam() {
		Question question = new Question(1L).unique().get();
        return ok(answercolumn.render(1, question));
    }

	public static Result showAnswerSheet() {	
        return ok(answersheet.render(null));
	}

	public static Result finishExam() {	
        return ok(examresult.render(1));
}

}
