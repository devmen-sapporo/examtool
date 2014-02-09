import java.util.*;

import models.entity.*;
import play.*;
import play.api.mvc.*;
import play.filters.csrf.*;
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
        	Season season = new Season(1L).unique().get();
        	Category category= new Category(1L).unique().get();

        	question.season = season;
        	question.category = category;
        	question.update();
        }
	}
	
	@Override
	public void onStop(Application app) {
	    Logger.info("Application shutdown...");
	}
	
	@Override
	public <T extends EssentialFilter> Class<T>[] filters() {
		return new Class[] { CSRFFilter.class };
	}
	
	static class InitialData {
        
        public static void insert(	Application app) {
            if(Ebean.find(User.class).findRowCount() == 0) {
                
                @SuppressWarnings("unchecked")
				Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");
                
                Ebean.save(all.get("seasons"));
                Ebean.save(all.get("categories"));
                Ebean.save(all.get("users"));
                Ebean.save(all.get("questions"));
                Ebean.save(all.get("optionItems"));
                
            }
        }
        
    }
}
