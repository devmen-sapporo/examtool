import java.util.*;

import models.entity.*;
import play.*;
import play.libs.*;

import com.avaje.ebean.*;

public class Global extends GlobalSettings {
	
	
	@Override
	public void onStart(Application app) {
		Logger.info("Application has started");
        InitialData.insert(app);

        List<Question> questions = Question.finder.all();
        for (Question question : questions)
        {
        	Logger.info(question.toString());
        }

//        Question question = new Question(1L).unique().get();
//
//        OptionItem item1 = new OptionItem(1L).unique().get();
//        item1.question = question;
//        item1.update();
//
//        OptionItem item2 = new OptionItem(2L).unique().get();
//        item2.question = question;
//        item2.update();
//
//        OptionItem item3 = new OptionItem(3L).unique().get();
//        item3.question = question;
//        item3.update();
//
//        OptionItem item4 = new OptionItem(4L).unique().get();
//        item4.question = question;
//        item4.update();

//        Question question = new Question(1L).unique().get();
//        question.optionItems.add(new OptionItem(1L).unique().get());
//        question.optionItems.add(new OptionItem(2L).unique().get());
//        question.optionItems.add(new OptionItem(3L).unique().get());
//        question.optionItems.add(new OptionItem(4L).unique().get());
//        question.update();

	}
	
	@Override
	public void onStop(Application app) {
	    Logger.info("Application shutdown...");
	}
	
	static class InitialData {
        
        public static void insert(	Application app) {
            if(Ebean.find(User.class).findRowCount() == 0) {
                
                @SuppressWarnings("unchecked")
				Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");
                
                Ebean.save(all.get("users"));
                Ebean.save(all.get("questions"));
                Ebean.save(all.get("optionItems"));
                
            }
        }
        
    }
	

	
}
