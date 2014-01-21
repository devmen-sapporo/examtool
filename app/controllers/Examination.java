package controllers;

import play.mvc.*;
import views.html.*;

public class Examination extends Controller {

	public static Result startExam() {	
            return ok(answercolumn.render(1));
    }

	public static Result showAnswerSheet() {	
        return ok(answersheet.render(null));
	}

	public static Result finishExam() {	
        return ok(examresult.render(1));
}

}
