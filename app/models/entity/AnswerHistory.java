package models.entity;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * 解答履歴クラスです。
 * @author Hiroyuki
 *
 */
@Entity
public class AnswerHistory extends Model {

	/**
	 * コンストラクタ
	 * @param id
	 */
	public AnswerHistory(long id) {
		this.id = id;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param answerColumn 解答
	 */
	public AnswerHistory(AnswerColumn answerColumn) {
		super();
		this.answerColumn = answerColumn;
	}
	
	@Id
	public long id;
	
	/** 問題 */
	@ManyToOne
	public AnswerColumn answerColumn;

}
