import java.util.*;

import models.entity.*;

import org.pac4j.core.client.*;
import org.pac4j.oauth.client.*;
import org.pac4j.play.*;

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
        	Season season = new Season(2L).unique().get();
        	Category category= new Category(2L).unique().get();

        	question.season = season;
        	question.category = category;
        	question.update();
        }
        
        final FacebookClient facebookClient = new FacebookClient("280137138817690", "dd0c5ccface2d815603e631addfe8f19");
        final Clients clients = new Clients("http://localhost:9000/callback", facebookClient);
        Config.setClients(clients);
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
            if(Ebean.find(Account.class).findRowCount() == 0) {
                
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
