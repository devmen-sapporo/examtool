package models.entity;

import java.util.*;

import javax.persistence.*;

import models.service.Examination.*;
import play.db.ebean.*;
import play.libs.F.Option;

/**
 * 解答用紙クラスです。
 * 
 * @author A.Suzuki
 * 
 */
@Entity
public final class AnswerSheet extends Model {
	
	@Id
	public Long id;

	/** 実施日 */
	private Calendar operationDate = null;

	/** ユーザ */
	@ManyToOne(cascade=CascadeType.ALL)
	public User user;

	/** 解答欄のリスト **/
	@OneToMany(cascade=CascadeType.ALL)
	public ArrayList<AnswerColumn> answerColumns = new ArrayList<>();

	/** 終了済かどうかを示すリスト */
	private boolean isFinished = false;

	/** 点数 */
	private int score = 0;
	
	
	private int currentIndex = 0;

	public AnswerSheet(long id) {
		this.id = id;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param user
	 *            ユーザ
	 * @param questionSheet
	 *            解答用紙
	 */
	public AnswerSheet(User user, QuestionSheet questionSheet) {
		this.user = user;
		this.operationDate = Calendar.getInstance();
		this.makeAnswerColumns(questionSheet);
	}

	private void makeAnswerColumns(QuestionSheet questionSheet) {
		for(Question question: questionSheet.questions) {
			AnswerColumn answerColumn = new AnswerColumn(question);
			this.answerColumns.add(answerColumn);
		}
	}

	/**
	 * 実施日を取得します。
	 * 
	 * @return 実施日
	 */
	public Calendar getOperationDate() {
		return operationDate;
	}

	/**
	 * 採点します。
	 */
	public void mark() {
		this.isFinished = true; 
		
		this.score = 0;
		for(AnswerColumn answerColumn : this.answerColumns)
		{
			if (answerColumn.isAnswer()) this.score++;
		}
	}

	/**
	 * 問題数を取得します。
	 * 
	 * @return 問題数
	 */
	public int getQuestionCount() {
		return this.answerColumns.size();
	}

//	/**
//	 * 問題シートを取得します。
//	 * @return questionSheet
//	 */
//	public QuestionSheet getQuestionSheet() {
//		return questionSheet;
//	}

	/**
	 * 得点欄を取得します。
	 * @return answerColumns
	 */
	public ArrayList<AnswerColumn> getAnswerColumns() {
		return answerColumns;
	}

	/**
	 * 解答が終了したかどうかを取得します。
	 * @return isFinished 終了している場合 true を返します。
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * 得点を取得します。
	 * @return score 得点
	 */
	public int getScore() {
		if (!isFinished) throw new IllegalStateException("isFinish is false");
		return score;
	}

	public AnswerColumn getCurrentAnswerColumn() {
		return this.answerColumns.get(this.currentIndex);
	}
	
	public static Finder<Long, AnswerSheet> find =
			new Finder<Long, AnswerSheet>(Long.class, AnswerSheet.class);

	public Option<AnswerSheet> unique()
	{
		return new AnswerSheetModelService().findById(id);
	}
}
