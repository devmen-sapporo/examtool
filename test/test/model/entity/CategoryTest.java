package test.model.entity;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static play.test.Helpers.*;
import models.entity.*;

import org.junit.*;

public class CategoryTest {

	String expectedName = "テスト用カテゴリ";
	Long expectedId = 100L;

	@Test
	public void findById_正常() {
	  running(fakeApplication(inMemoryDatabase()), new Runnable() {
	    public void run() {
	    		    	
	    	Category cat = new Category(expectedId);
	    	cat.name = expectedName;
	    	cat.save();
	    	
	    	String actualName = Category.find.byId(expectedId).name;
	    	assertThat(actualName, is(expectedName));
	    }
	  });
	}

	@Test
	public void unique_正常() {
	  running(fakeApplication(inMemoryDatabase()), new Runnable() {
	    public void run() {
	    	
	    	Category expected = new Category(expectedId);
	    	expected.name = expectedName;
	    	expected.save();
	    	
	    	Category actual = new Category(100L).unique().get();
	    	assertThat(actual.id, is(expected.id));
	    }
	  });
	}

}
