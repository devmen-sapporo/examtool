package models.entity;

import java.util.*;

import javax.persistence.*;

import models.service.Examination.*;
import play.db.ebean.*;
import play.libs.F.Option;

/**
 * 
 * @author Hiroyuki
 *
 */
@Entity
public class QuestionSheet extends Model{
	
	public long id;
	
	/**
	 * 作成日
	 */
	public Calendar createDate;
	
	/**
	 * 問題リスト
	 */
	public List<Question> questions;
	
	/**
	 * 記号リスト
	 */
	@Transient
	public static List<String> signs = Arrays.asList("ア","イ","ウ","エ");

	public QuestionSheet(long id) {
		this.id = id;
	}
			
	/**
	 * @param createDate
	 * @param list
	 */
	public QuestionSheet(List<Question> list) {
		super();
		this.createDate = Calendar.getInstance();
		this.questions = list;
	}

	public void add(Question question) {
		this.questions.add(question);		
	}

	public static Finder<Long, QuestionSheet> find =
			new Finder<Long, QuestionSheet>(Long.class, QuestionSheet.class);

	public Option<QuestionSheet> unique()
	{
		return new QuestionSheetModelService().findById(id);
	}
}
