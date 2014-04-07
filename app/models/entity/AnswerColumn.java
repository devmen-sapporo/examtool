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
public class AnswerColumn extends Model {

	public AnswerColumn(long id) {
		this.id = id;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param user ユーザ情報
	 * @param question 問題
	 */
	public AnswerColumn(User user, Question question) {
		super();
		this.question = question;
		this.user = user;
	}

	@Id
	public long id;

	/** 問題 */
	@ManyToOne
	public User user;

	/** 問題 */
	@ManyToOne
	public Question question;

	@ManyToOne
	public AnswerSheet answerSheet;

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
	 * 解答済かどうかを返します。
	 * 
	 * @return 解答済の場合 true を返します。
	 */
	public boolean isAnswered() {
		return (this.selectedOptionItem != null);
	}

	/**
	 * 正解かどうかを返します。
	 * 
	 * @return 正解の場合 true を返します。
	 */
	public boolean isCorrect() {
		if (!this.isAnswered())
			return false;
		return this.selectedOptionItem.isAnswer;
	}

	public Option<AnswerColumn> unique() {
		return new AnswerColumnModelService().findById(id);
	}
}
