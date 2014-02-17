package models.entity;

import java.util.*;

import javax.persistence.*;

/**
 * 
 * @author Hiroyuki
 *
 */
public class QuestionSheet {
	
	/**
	 * 作成日
	 */
	private Calendar createDate;
	
	/**
	 * @return createDate
	 */
	public Calendar getCreateDate() {
		return createDate;
	}

	/**
	 * @return questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * 問題リスト
	 */
	private List<Question> questions;
	
	/**
	 * 記号リスト
	 */
	@Transient
	public static List<String> signs = Arrays.asList("ア","イ","ウ","エ");

	
	/**
	 * @param createDate
	 * @param list
	 */
	public QuestionSheet(List<Question> list) {
		super();
		this.createDate = Calendar.getInstance();
		this.questions = list;
	}

	/**
	 * 試験を開始します。
	 * @param user
	 * @return
	 */
	public AnswerSheet startExam(User user) {
		return new AnswerSheet(user, this);
	}

	public void add(Question question) {
		this.questions.add(question);		
	}
}
