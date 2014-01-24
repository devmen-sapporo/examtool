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
        List<User> users = User.find.all();
        for (User user : users)
        {
        	Logger.info(user.toString());
        }
	}
	
	@Override
	public void onStop(Application app) {
	    Logger.info("Application shutdown...");
	}
	
	static class InitialData {
        
        public static void insert(Application app) {
            if(Ebean.find(User.class).findRowCount() == 0) {
                
                @SuppressWarnings("unchecked")
								Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");

                Ebean.save(all.get("users"));
                
            }
        }
        
    }
}