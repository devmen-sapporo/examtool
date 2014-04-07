package test.model.entity;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static play.test.Helpers.*;
import models.entity.*;

import org.junit.*;

/*
 * よくわからないので今のところ YAML ファイルを読み込む前提でテストコード書いてしまいます。 
 */
public class AnswerColumnTest {


	@Test
	public void AnswerColumn_正常() {
	  running(fakeApplication(inMemoryDatabase()), new Runnable() {
	    public void run() {

	    	User expectedUser = new User(1L).unique().get();
	    	Question expectedQuestion = new Question(1L).unique().get();

	    	AnswerColumn answerColumn = new AnswerColumn(expectedUser, expectedQuestion);
	    	User actualUser = answerColumn.user;
	    	Question actualQuestion = answerColumn.question;

	    	assertThat(actualUser, is(expectedUser));
	    	assertThat(actualQuestion, is(expectedQuestion));	    	
	    }
	  });
	}

	@Test
	public void markAnswer_正常() {
	  running(fakeApplication(inMemoryDatabase()), new Runnable() {
	    public void run() {
	    	User expectedUser = new User(1L).unique().get();
	    	Question expectedQuestion = new Question(1L).unique().get();

	    	AnswerColumn answerColumn = new AnswerColumn(expectedUser, expectedQuestion);
	    	
	    	OptionItem expected = expectedQuestion.optionItems.get(2);
	    	answerColumn.markAnswer(expected);
	    	
	    	OptionItem actual = answerColumn.selectedOptionItem;
	    	
	    	assertThat(actual, is(expected));
	    }
	  });
	}
	
	@Test
	public void isAnswered_正常_選択済() {
	  running(fakeApplication(inMemoryDatabase()), new Runnable() {
	    public void run() {
	    	User expectedUser = new User(1L).unique().get();
	    	Question expectedQuestion = new Question(1L).unique().get();

	    	AnswerColumn answerColumn = new AnswerColumn(expectedUser, expectedQuestion);
	    	
	    	OptionItem optionItem = expectedQuestion.optionItems.get(2);
	    	answerColumn.markAnswer(optionItem);
	    	
	    	boolean expected = true;
	    	boolean actual = answerColumn.isAnswered();
	    	
	    	assertThat(actual, is(expected));
	    }
	  });
	}	

	@Test
	public void isAnswered_正常_未選択() {
	  running(fakeApplication(inMemoryDatabase()), new Runnable() {
	    public void run() {
	    	User expectedUser = new User(1L).unique().get();
	    	Question expectedQuestion = new Question(1L).unique().get();

	    	AnswerColumn answerColumn = new AnswerColumn(expectedUser, expectedQuestion);
	    	
	    	boolean expected = false;
	    	boolean actual = answerColumn.isAnswered();
	    	
	    	assertThat(actual, is(expected));
	    }
	  });
	}	

	@Test
	public void isCorrect_正常_正解() {
	  running(fakeApplication(inMemoryDatabase()), new Runnable() {
	    public void run() {
	    	
	    	User expectedUser = new User(1L).unique().get();
	    	Question expectedQuestion = new Question(1L).unique().get();

	    	AnswerColumn answerColumn = new AnswerColumn(expectedUser, expectedQuestion);
	    	
	    	OptionItem optionItem = expectedQuestion.optionItems.get(2);
	    	optionItem.isAnswer = true;
	    	answerColumn.markAnswer(optionItem);
	    	
	    	boolean expected = true;
	    	boolean actual = answerColumn.isCorrect();
	    	
	    	assertThat(actual, is(expected));
	    }
	  });
	}	

	@Test
	public void isCorrect_正常_不正解() {
	  running(fakeApplication(inMemoryDatabase()), new Runnable() {
	    public void run() {

	    	User expectedUser = new User(1L).unique().get();
	    	Question expectedQuestion = new Question(1L).unique().get();

	    	AnswerColumn answerColumn = new AnswerColumn(expectedUser, expectedQuestion);
	    	
	    	OptionItem optionItem = expectedQuestion.optionItems.get(2);
	    	optionItem.isAnswer = false;
	    	answerColumn.markAnswer(optionItem);
	    	
	    	boolean expected = false;
	    	boolean actual = answerColumn.isCorrect();
	    	
	    	assertThat(actual, is(expected));
	    }
	  });
	}	

}

