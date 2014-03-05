package models.entity;

import javax.persistence.*;

import models.service.Examination.*;
import play.db.ebean.*;
import play.libs.F.Option;

/**
 * 解答クラスです。
 * 
 * @author Hiroyuki
 * 
 */
@Entity
public class AnswerColumn extends Model{
	
	public AnswerColumn(long id) {
		this.id = id;
	}
	
	/**
	 * @param question
	 */
	public AnswerColumn(Question question) {
		super();
		this.question = question;
	}

	@Id
	public long id;

	/** 問題 */
	@ManyToOne
	public Question question;

	/** 選んだ選択肢 */
	@ManyToOne
	public OptionItem selectedOptionItem;

	/**
	 * 解答します。
	 * 
	 * @param selectedOptionItem
	 *            選んだ選択肢
	 */
	public void markAnswer(OptionItem selectedOptionItem) {
		this.selectedOptionItem = selectedOptionItem;
	}

	/**
	 * 正解かどうかを返します。
	 * 
	 * @return 正解の場合 true を返します。
	 */
	public boolean isAnswer() {
		if (this.selectedOptionItem == null)
			return false;
		return this.selectedOptionItem.isAnswer;
	}
	
	public Option<AnswerColumn> unique()
	{
		return new AnswerColumnModelService().findById(id);
	}
}
